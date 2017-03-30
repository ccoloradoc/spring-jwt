package com.colorado.jwt.api.service;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by colorado on 29/03/17.
 */
public abstract class ApiService {

    private String endpoint;
    private String key;

    private RestTemplate restTemplate = new RestTemplate();

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEndpoint(HashMap<String, String> params) {
        String url = endpoint + "&key=" + key;

        Set set = params.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            url += "&" + entry.getKey() + "=" + entry.getValue();
        }

        return url;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}