package com.nexign.resttest;

public class TestUtils {
    public static String extractId(String response) {
        String resultStringWithoutBracers = response.replace("[", "")
                .replace("]", "")
                .replace("{", "")
                .replace("}", "");
        String[] values = resultStringWithoutBracers.split(",");
        String[] idAndValue = values[0].split("=");
        String id = idAndValue[1];
        return id;
    }
}
