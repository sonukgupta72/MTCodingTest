package com.sonukgupta.mtcodingtest.network;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sonu on 21/7/18.
 */

public class ApiRequestData {

    private Map<String, String> headers = new HashMap<>();

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }


}
