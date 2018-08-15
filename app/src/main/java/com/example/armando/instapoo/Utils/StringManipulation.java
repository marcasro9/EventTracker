package com.example.armando.instapoo.Utils;

/**
 * Created by Armando on 12/09/2017.
 */

public class StringManipulation {

    public static String expandUsername(String username){
        return username.replace(".", "");
    }
    public static String condenseUsername(String username){
        return username.replace(" ",".");
    }
}
