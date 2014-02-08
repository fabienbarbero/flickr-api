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

import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONObject;
import com.flickr.api.utils.IOUtils;

/**
 *
 * @author Fabien Barbero
 */
public abstract class FlickrService {

    public static final int MAX_PER_PAGE = Integer.MAX_VALUE;
    private static final String URL_PREFIX = "http://api.flickr.com/services/rest/";
    private final HttpClient client;
    private final OAuthHandler oauth;

    protected FlickrService(OAuthHandler oauth, HttpClient client) {
        this.oauth = oauth;
        this.client = client;
    }

    protected final <T extends ServerResponse> T doGet(CommandArguments args, Class<T> clazz) throws FlickrServiceException {
        HttpGet request = new HttpGet(args.toURI(URL_PREFIX, oauth.getConsumer().getConsumerKey()));
        return doRequest(args, clazz, request);
    }

//    protected final <T extends ServerResponse> T doPost(CommandArguments args, Class<T> clazz) throws FlickrServiceException {
//        HttpPost request = new HttpPost(URL_PREFIX);
//        request.setEntity(args.getPostEntity(oauth.getConsumer().getConsumerKey()));
//        return doRequest(args, clazz, request);
//    }

    private <T extends ServerResponse> T doRequest(CommandArguments args, Class<T> clazz, HttpUriRequest request) throws FlickrServiceException {
        InputStream is = null;
        try {
            oauth.getConsumer().sign(request);
            HttpResponse response = client.execute(request);

            is = response.getEntity().getContent();
            String result = IOUtils.toString(is, "UTF-8");
            JSONObject json = new JSONObject(result);

            if (Flickr.debug) {
                try {
                    System.out.println("Server response for method " + args.getMethod() + " (" + request.getURI() + ")\n" + json.toString(2));
                } catch (Exception ignored) {
                }
            }

            T instance = clazz.newInstance();
            instance.read(json, args.getMethod());

            return instance;

        } catch (FlickrServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new FlickrServiceException("Server request error", ex);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

}
