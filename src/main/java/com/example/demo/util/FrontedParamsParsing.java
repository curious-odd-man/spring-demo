package com.example.demo.util;

import java.util.Map;

public class FrontedParamsParsing {
    public static <K> int getIntParam(Map<K, String> params, K param) {
        return Integer.parseInt(params.get(param));
    }

    public static <K> boolean getBooleanParam(Map<K, String> params, K param) {
        return "on".equals(params.get(param));
    }
}
