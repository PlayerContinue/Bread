package com.example.davidgreenberg.breadapplication;

import java.lang.reflect.Field;

/**
 * Created by David Greenberg on 6/7/2017.
 */

public class Utility {

    /**
     *
     * @param resName The name of the resource as a string
     * @param c The class containing the desired resource
     * @return
     */
    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Find the class desired from the name of the class
     * @param name - The name of the class seperated by . for multiple internal classes
     * @return
     */
    public static Class<?> getClassByName(String name) throws ClassNotFoundException{
        return Class.forName(name);
    }

}
