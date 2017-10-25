/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitmapdemo;

/**
 *
 * @author SHAMIM
 */
public class BitmapDemo {

    public BitmapDemo() {
        Bitmap bitmap =new Bitmap("fly.bmp");
        bitmap.read();
//        bitmap.setToBlack(0, 0,(int) (bitmap.getHeight()/2) - 1,(int) bitmap.getWidth() - 1);
//        bitmap.setToGray(0, 0,(int) (bitmap.getHeight()/2) - 1,(int) bitmap.getWidth() - 1);
//        bitmap.setToSingleColor(0, 0,(int) (bitmap.getHeight()/2) - 1,(int) bitmap.getWidth() - 1);
        bitmap.partIntoThreeSingleColor(0, 0,(int) bitmap.getHeight() - 1,(int) bitmap.getWidth() - 1);
//        bitmap.AddColor(30);
//        bitmap.scaleColor(.5);
        bitmap.write("copy.bmp");
        bitmap.grayscaleHistogram();
        int p = 0;
        for (int i : bitmap.grayscaleHistogram()) {
            System.out.println("GrayscaleHistoram " + p + " = " + i);
            p++;
        }
    }

    
    public static void main(String[] args) {
        new BitmapDemo();
    }
    
}
