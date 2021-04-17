package com.blogs.utils;

import java.util.UUID;

public class StringUtil {
    public StringUtil() {
    }

    public static boolean isNull(String str) {
        return str == null || str.equals("");
    }

    public static boolean isNotNull(String str) {
        return str != null && !str.equals("");
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

