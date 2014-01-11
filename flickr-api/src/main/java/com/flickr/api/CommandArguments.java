/*
 * Copyright (C) 2011 by Fabien Barbero
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.flickr.api;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;

/**
 * Class used to build a REST request with the valid arguments. Each command must provide the valid method defined in
 * the Flickr API web page.
 *
 * @author Fabien Barbero
 */
public final class CommandArguments {

    private final Map<String, String> args;
    private final boolean sign;

    /**
     * Create a new command
     *
     * @param method The method name
     */
    public CommandArguments(String method, boolean sign) {

        this.sign = sign;
        args = new TreeMap<String, String>();
        if (method != null) {
            put("method", method);
        }
        put("format", "json");
        put("nojsoncallback", "1");
    }


    public boolean isSign() {
        return sign;
    }
    
    public String getMethod() {
        return args.get("method");
    }

    public void put(String key, String value) {
        args.put(key, value);
    }

    public void put(String key, int value) {
        args.put(key, String.valueOf(value));
    }

    public void put(String key, long value) {
        args.put(key, String.valueOf(value));
    }

    public void put(String key, boolean value) {
        if (value) {
            args.put(key, "1");
        } else {
            args.put(key, "0");
        }
    }

    public String toURI(String baseURI, String apiKey) {
        put("api_key", apiKey);

        StringBuilder builder = new StringBuilder();
        List<Map.Entry<String, String>> entries = new ArrayList<Map.Entry<String, String>>(args.entrySet());
        for (int i = 0; i < entries.size(); i++) {
            builder.append(entries.get(i).getKey()).append("=").append(entries.get(i).getValue());
            if (i < entries.size() - 1) {
                builder.append("&");
            }
        }
        return baseURI + "?" + builder.toString();
    }

    public MultipartEntity getPostEntity(String apiKey) {
        put("api_key", apiKey);

        MultipartEntity entity = new MultipartEntity();
        for (Map.Entry<String, String> entry : args.entrySet()) {
            try {
                entity.addPart(entry.getKey(), new StringBody(entry.getValue()));
            } catch (UnsupportedEncodingException ex) {
                throw new UnsupportedOperationException(ex.getMessage(), ex);
            }
        }
        return entity;
    }
}
