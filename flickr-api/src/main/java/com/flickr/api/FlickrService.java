/*
 * Copyright (C) 2011 by Fabien Barbero Permission is hereby granted, free of
 * charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to
 * the following conditions: The above copyright notice and this permission
 * notice shall be included in all copies or substantial portions of the
 * Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */
package com.flickr.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import org.apache.http.entity.mime.MultipartEntity;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;

/**
 *
 * @author Fabien Barbero
 */
public abstract class FlickrService {

    public static final int MAX_PER_PAGE = Integer.MAX_VALUE;
    private static final String URL_PREFIX = "http://api.flickr.com/services/rest";
    private final OAuthHandler oauth;

    protected FlickrService(OAuthHandler oauth) {
        this.oauth = oauth;
    }

    protected final <T extends ServerResponse> T doGet(CommandArguments args, Class<T> clazz) throws FlickrServiceException {
        OAuthRequest request = new OAuthRequest(Verb.GET, URL_PREFIX);
        for (Map.Entry<String, Object> param : args.getParameters().entrySet()) {
            request.addQuerystringParameter(param.getKey(), String.valueOf(param.getValue()));
        }

        oauth.signRequest(request);
        Response response = request.send();
        String body = response.getBody();

        return parseBody(args, clazz, body);
    }

    protected final <T extends ServerResponse> T doPost(CommandArguments args, Class<T> clazz) throws FlickrServiceException {
        return doPost(args, clazz, URL_PREFIX);
    }

    protected final <T extends ServerResponse> T doPost(CommandArguments args, Class<T> clazz, String url) throws FlickrServiceException {
        try {
            OAuthRequest request = new OAuthRequest(Verb.POST, url);
            for (Map.Entry<String, Object> param : args.getParameters().entrySet()) {
                if (param.getValue() instanceof String) {
                    request.addQuerystringParameter(param.getKey(), (String) param.getValue());
                }
            }

            oauth.signRequest(request);

            MultipartEntity multipart = args.getBody(request.getOauthParameters());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            multipart.writeTo(baos);

            request.addPayload(baos.toByteArray());
            request.addHeader("Content-type", multipart.getContentType().getValue());

            Response response = request.send();
            String body = response.getBody();
            System.out.println(body);

            return parseBody(args, clazz, body);

        } catch (IOException ex) {
            throw new UnsupportedOperationException("Error preparing multipart request", ex);
        }
    }

    private <T extends ServerResponse> T parseBody(CommandArguments args, Class<T> clazz, String body) throws FlickrServiceException {
        try {
            if (Flickr.debug) {
                try {
                    System.out.println("Server response for method " + args.getMethod() + "\n" + body);
                } catch (Exception ignored) {
                }
            }

            T instance = clazz.newInstance();
            instance.read(body, args.getMethod());

            return instance;

        } catch (FlickrServiceException ex) {
            throw ex;
        } catch (IllegalStateException ex) {
            throw new FlickrServiceException("Server request error", ex);
        } catch (InstantiationException ex) {
            throw new FlickrServiceException("Server request error", ex);
        } catch (IllegalAccessException ex) {
            throw new FlickrServiceException("Server request error", ex);
        }
    }

}
