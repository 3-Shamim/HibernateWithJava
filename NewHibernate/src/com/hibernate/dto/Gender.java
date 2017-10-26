package com.hibernate.dto;

import java.io.Serializable;

/**
 *
 * @author SHAMIM
 */
public class Gender implements Serializable{
    private double age;
    private boolean isMale;

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public boolean isIsMale() {
        return isMale;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }
    
}
