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

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthException;

/**
 *
 * @author Fabien Barbero
 */
public class OAuthHandler {

    private static final String PROPERTY_REQUEST_TOKEN = "oauth.request.token";
    private static final String PROPERTY_REQUEST_SECRET = "oauth.request.secret";
    private static final String PROPERTY_ACCESS_TOKEN = "oauth.access.token";
    private static final String PROPERTY_ACCESS_SECRET = "oauth.access.secret";
    private static final String PROPERTY_TOKEN = "oauth.token";
    //
    private final FlickrProperties props;
    private final OAuthConsumer consumer;
    private final OAuthProvider provider;
    //
    private OAuthToken requestToken;
    private OAuthToken accessToken;
    private String token;

    OAuthHandler(FlickrProperties props, String apiKey, String apiSecret, String perms) {
        this.props = props;
        consumer = new CommonsHttpOAuthConsumer(apiKey, apiSecret);
        provider = new CommonsHttpOAuthProvider(
                "http://www.flickr.com/services/oauth/request_token",
                "http://www.flickr.com/services/oauth/access_token",
                "http://www.flickr.com/services/oauth/authorize?perms=" + perms);
        provider.setOAuth10a(true);

        load();
    }

    private void load() {
        if (props.contains(PROPERTY_REQUEST_TOKEN) && props.contains(PROPERTY_REQUEST_SECRET)) {
            requestToken = new OAuthToken(props.getString(PROPERTY_REQUEST_TOKEN, null), props.getString(PROPERTY_REQUEST_SECRET, null));
            setToken(requestToken);

        } else if (props.contains(PROPERTY_ACCESS_TOKEN) && props.contains(PROPERTY_ACCESS_SECRET)) {
            accessToken = new OAuthToken(props.getString(PROPERTY_ACCESS_TOKEN, null), props.getString(PROPERTY_ACCESS_SECRET, null));
            setToken(accessToken);
        }

        token = props.getString(PROPERTY_TOKEN, null);
    }

    public String getOAuthToken() {
        return token;
    }

    private void setToken(OAuthToken token) {
        consumer.setTokenWithSecret(token.getKey(), token.getSecret());
    }

    private void save() {
        if (requestToken != null) {
            props.putString(PROPERTY_REQUEST_TOKEN, requestToken.getKey());
            props.putString(PROPERTY_REQUEST_SECRET, requestToken.getSecret());
        } else {
            props.remove(PROPERTY_REQUEST_TOKEN);
            props.remove(PROPERTY_REQUEST_SECRET);
        }

        if (accessToken != null) {
            props.putString(PROPERTY_ACCESS_TOKEN, accessToken.getKey());
            props.putString(PROPERTY_ACCESS_SECRET, accessToken.getSecret());
        } else {
            props.remove(PROPERTY_ACCESS_TOKEN);
            props.remove(PROPERTY_ACCESS_SECRET);
        }

        if (token != null) {
            props.putString(PROPERTY_TOKEN, token);
        } else {
            props.remove(PROPERTY_TOKEN);
        }

        props.commit();
    }

    public OAuthToken getAccessToken() {
        return accessToken;
    }

    public OAuthToken getRequestToken() {
        return requestToken;
    }

    OAuthConsumer getConsumer() {
        return consumer;
    }

    String retrieveAuthorizationUrl(String callbackUrl) throws OAuthException {
        String authorizationUrl = provider.retrieveRequestToken(consumer, callbackUrl);
        requestToken = new OAuthToken(consumer.getToken(), consumer.getTokenSecret());
        save();
        return authorizationUrl;
    }

    void retrieveAccessToken(String verifier, String token) throws OAuthException {
        provider.retrieveAccessToken(consumer, verifier);
        accessToken = new OAuthToken(consumer.getToken(), consumer.getTokenSecret());
        this.token = token;
        requestToken = null; // Invalidate
        save();
    }

    public void clear() {
        requestToken = null;
        accessToken = null;
        token = null;
        save();
    }
}
