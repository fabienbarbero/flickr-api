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

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

/**
 * Class used to build a REST request with the valid arguments. Each command must provide the valid method defined in
 * the Flickr API web page.
 *
 * @author Fabien Barbero
 */
final class CommandArguments {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private final Map<String, Object> params = new TreeMap<String, Object>();
    private final String method;

    /**
     * Create a new command
     *
     * @param method The method name
     */
    public CommandArguments(String method) {
        this.method = method;
        if (method != null) {
            addParam("method", method);
        }
        addParam("format", "json");
        addParam("nojsoncallback", "1");
    }

    public CommandArguments() {
        this(null);
    }

    public String getMethod() {
        return method;
    }

    public void addParam(String key, Object value) {
        params.put(key, value);
    }

    public void addParam(String key, boolean value) {
        if (value) {
            params.put(key, "1");
        } else {
            params.put(key, "0");
        }
    }

    Map<String, Object> getParameters() {
        return params;
    }

    public MultipartEntity getBody(Map<String,String> additionalParameters) {
        try {
            MultipartEntity entity = new MultipartEntity(HttpMultipartMode.STRICT);

            Object value;
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                value = entry.getValue();
                if (value instanceof File) {
                    entity.addPart(entry.getKey(), new FileBody((File) value));
                }
            }
            for(Map.Entry<String, String> entry : additionalParameters.entrySet()) {
                entity.addPart(entry.getKey(), new StringBody(entry.getValue(), UTF8));
            }
            
            return entity;

        } catch (UnsupportedEncodingException ex) {
            throw new UnsupportedOperationException(ex.getMessage(), ex);
        }
    }
}
