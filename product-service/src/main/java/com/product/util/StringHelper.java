package com.product.util;
import java.util.UUID;

public class StringHelper {
    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
