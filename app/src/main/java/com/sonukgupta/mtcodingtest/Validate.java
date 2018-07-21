package com.sonukgupta.mtcodingtest;

/**
 * Created by sonu on 21/7/18.
 */

public class Validate {

    public static boolean isEmptyString(String string){
        if(string == null || string.trim().equals("")) return true;

        return false;
    }
}
