package com.learn.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.security.web.savedrequest.Enumerator;

import java.util.*;

public class CustomHttpServletWrapper extends HttpServletRequestWrapper {

    private final Map<String, String> customHeaders = new HashMap<>();

    public CustomHttpServletWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        Enumeration<String> enumeration = super.getHeaderNames();
        Map<String, String> all = new HashMap<>();
        while(enumeration.hasMoreElements()){
            String s = enumeration.nextElement();
            all.put(s, super.getHeader(s));
        }
        all.putAll(customHeaders);
        return Collections.enumeration(all.keySet());
    }

    @Override
    public String getHeader(String name) {
        String value = customHeaders.get(name);
        if(Objects.nonNull(value)) return  value;
        return super.getHeader(name);
    }

    public void addHeader(String name, String value){
         customHeaders.put(name, value) ;
    }
}
