package bitmapdemo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bitmap {
    private final String fileName;
    private long fileSize;
    private byte buffer[];
    private long imageDataOffset;
    private long width;
    private long height;

    public Bitmap(String fileName) {
        this.fileName = fileName;
    }
    
    public void read(){
        try {
            //Copy this file
            try (RandomAccessFile input = new RandomAccessFile(fileName,"r")) {
                //Copy this file
                input.seek(0);
                buffer = new byte[ (int) input.length()];
                input.read(buffer);
                
                
                byte[] byteArray;
                byteArray = new byte[2];
                //Signature
                input.seek(0);
                System.out.printf("Byte %d \n",input.read(byteArray));
                System.out.printf("Contents  %s \n",Arrays.toString(byteArray));
                System.out.printf("Contents in Value  %s \n",new String(byteArray));
                //FileSize
                input.seek(2);
                byteArray = new byte[4];
                System.out.printf("Byte %d \n",input.read(byteArray));
                System.out.printf("Contents  %s \n",Arrays.toString(byteArray));
                fileSize = byteArrayToLong(byteArray);
                System.out.println(fileSize);
                //Width
                input.seek(18);
                byteArray = new byte[2];
                System.out.printf("Byte %d \n",input.read(byteArray));
                System.out.printf("Contents %s \n",Arrays.toString(byteArray));
                width = byteArrayToLong(byteArray);
                System.out.println("Width " + width);
                //Height
                input.seek(22);
                byteArray = new byte[2];
                System.out.printf("Byte %d \n",input.read(byteArray));
                System.out.printf("Contents %s \n",Arrays.toString(byteArray));
                height = byteArrayToLong(byteArray);
                System.out.println("Height " + height);
                //Image data ofset
                input.seek(10);
                byteArray = new byte[4];
                System.out.printf("Byte %d \n",input.read(byteArray));
                System.out.printf("Contents %s \n",Arrays.toString(byteArray));
                imageDataOffset = byteArrayToLong(byteArray);
                System.out.println("Image Data Offset " + imageDataOffset);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Bitmap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Bitmap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void write(String fileName)
    {
        try {
            RandomAccessFile output = new RandomAccessFile(fileName,"rw");
            output.setLength(0);
            output.write(buffer);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Bitmap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Bitmap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setToBlack(int startRow, int startCol, int endRow, int endCol)
    {
        for(int row = startRow; row <= endRow; row++)
        {
            for (int col = startCol; col <= endCol; col++) {
                int index = (int) (imageDataOffset + (row * width + col) *3);
//                System.out.println(index);
//                System.out.println((row * width + col) *3);
//                System.out.println(width);
//                int b = buffer[index + 0];
//                int g = buffer[index + 1];
//                int r = buffer[index + 2];
                buffer[ index + 0] = 0;
                buffer[ index + 1] = 0;
                buffer[ index + 2] = 0;
            }
        }
    }
    public void setToGray(int startRow, int startCol, int endRow, int endCol)
    {
        for(int row = startRow; row <= endRow; row++)
        {
            for (int col = startCol; col <= endCol; col++) {
                int index = (int) (imageDataOffset + (row * width + col) *3);
//                System.out.println(index);
//                System.out.println((row * width + col) *3);
//                System.out.println(width);
                int b = buffer[index + 0] & 0xFF;
                int g = buffer[index + 1] & 0xFF;
                int r = buffer[index + 2] & 0xFF;
                int gray = (b+g+r)/3;
                buffer[index + 0] = (byte) gray;
                buffer[index + 1] = (byte) gray;
                buffer[index + 2] = (byte) gray;
            }
        }
    }
    public void partIntoThreeSingleColor(int startRow, int startCol, int endRow, int endCol)
    {
        for(int row = startRow; row <= endRow; row++)
        {
            for (int col = startCol; col <= endCol; col++) {
                int index = (int) (imageDataOffset + (row * width + col) *3);
                int b = buffer[index + 0] & 0xFF;
                int g = buffer[index + 1] & 0xFF;
                int r = buffer[index + 2] & 0xFF;
                int gray = (b+g+r)/3;
                buffer[index + 0] = (byte) b;
                buffer[index + 1] = (byte) g;
                buffer[index + 2] = (byte) r;
            }
        }
    }
    public void setToSingleColor(int startRow, int startCol, int endRow, int endCol)
    {
        for(int row = startRow; row <= endRow; row++)
        {
            for (int col = startCol; col <= endCol; col++) {
                int index = (int) (imageDataOffset + (row * width + col) *3);
                int b = buffer[index + 0] & 0xFF;
                int g = buffer[index + 1] & 0xFF;
                int r = buffer[index + 2] & 0xFF;
//                int gray = (b+g+r)/3;
                buffer[index + 0] = 0;
                buffer[index + 1] = (byte) g;
                buffer[index + 2] = (byte) r;
            }
        }
    }
    public void AddColor(int amount)
    {
        for(int row = 0; row < height; row++)
        {
            for (int col = 0; col < width; col++) {
                int index = (int) (imageDataOffset + (row * width + col) *3);
//                int b = buffer[index + 0] & 0xFF;
//                int g = buffer[index + 1] & 0xFF;
//                int r = buffer[index + 2] & 0xFF;
                for (int i = 0; i < 3; i++) {
                    int color = buffer[index + i] & 0xFF;
                    int newColor = color + amount;
                    if(newColor < 0)
                    {
                        newColor = 0;
                    }
                    if(newColor > 255)
                    {
                        newColor = 255;
                    }
                    buffer[index + i] = (byte) newColor;
                }
            }
        }
    }
    public void scaleColor(double amount)
    {
        for(int row = 0; row < height; row++)
        {
            for (int col = 0; col < width; col++) {
                int index = (int) (imageDataOffset + (row * width + col) *3);
//                int b = buffer[index + 0] & 0xFF;
//                int g = buffer[index + 1] & 0xFF;
//                int r = buffer[index + 2] & 0xFF;
                for (int i = 0; i < 3; i++) {
                    int color = buffer[index + i] & 0xFF;
                    int newColor =  (int) (color * amount);
                    if(newColor < 0)
                    {
                        newColor = 0;
                    }
                    if(newColor > 255)
                    {
                        newColor = 255;
                    }
                    buffer[index + i] = (byte) newColor;
                }
            }
        }
    }

    private long byteArrayToLong(byte[] byteArray) {
        long value = 0;
        for(int i = byteArray.length - 1; i >= 0; i--){
            int x = byteArray[i] & 0xFF;
            System.out.println(x);
            value = value << 8;
            value = value | x;
        }
        return value;
    }

    public long getFileSize() {
        return fileSize;
    }

    public long getImageDataOffset() {
        return imageDataOffset;
    }

    public long getWidth() {
        return width;
    }

    public long getHeight() {
        return height;
    }
     public int[] grayscaleHistogram() {
        int counter[] = new int[256];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int index = (int) (imageDataOffset + (row * width + col) * 3);
                int b = buffer[index + 0] & 0xFF;
                int g = buffer[index + 1] & 0xFF;
                int r = buffer[index + 2] & 0xFF;
                //int gray = (r + g + b) / 3;
                int gray = (int) (0.21 * r + 0.72 * g + 0.07 * b); // luminosity method
//                System.out.println("Gary = " + gray);
                counter[gray]++;
            }
        }
        return counter;
    }
    
}
