/*
 * (C) Copyright 2014 Fabien Barbero.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */
package com.github.fabienbarbero.flickr.api;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @author Fabien Barbero
 */
class OAuthHandler
        implements Closeable
{

    private static final String PROPERTY_REQUEST_TOKEN = "oauth.request.token";
    private static final String PROPERTY_REQUEST_SECRET = "oauth.request.secret";
    private static final String PROPERTY_ACCESS_TOKEN = "oauth.access.token";
    private static final String PROPERTY_ACCESS_SECRET = "oauth.access.secret";
    private static final String PROPERTY_TOKEN = "oauth.token";

    private final FlickrProperties props;
    private final OAuth10aService service;

    private OAuth1RequestToken requestToken;
    private OAuth1AccessToken accessToken;
    private String token;

    OAuthHandler( FlickrProperties props,
                  String apiKey,
                  String apiSecret,
                  String callbackUrl,
                  String perms )
    {
        this.props = props;
        service = new ServiceBuilder( apiKey )
                .apiSecret( apiSecret )
                .callback( callbackUrl )
                .build( new FlickrPermsApi( perms ) );
        load();
    }

    private void load()
    {
        if ( props.contains( PROPERTY_REQUEST_TOKEN ) && props.contains( PROPERTY_REQUEST_SECRET ) ) {
            requestToken = new OAuth1RequestToken( props.getString( PROPERTY_REQUEST_TOKEN, null ),
                                                   props.getString( PROPERTY_REQUEST_SECRET, null ) );

        } else if ( props.contains( PROPERTY_ACCESS_TOKEN ) && props.contains( PROPERTY_ACCESS_SECRET ) ) {
            accessToken = new OAuth1AccessToken( props.getString( PROPERTY_ACCESS_TOKEN, null ),
                                                 props.getString( PROPERTY_ACCESS_SECRET, null ) );
        }

        token = props.getString( PROPERTY_TOKEN, null );
    }

    public String getOAuthToken()
    {
        return token;
    }

    private void save()
    {
        if ( requestToken != null ) {
            props.putString( PROPERTY_REQUEST_TOKEN, requestToken.getToken() );
            props.putString( PROPERTY_REQUEST_SECRET, requestToken.getTokenSecret() );
        } else {
            props.remove( PROPERTY_REQUEST_TOKEN );
            props.remove( PROPERTY_REQUEST_SECRET );
        }

        if ( accessToken != null ) {
            props.putString( PROPERTY_ACCESS_TOKEN, accessToken.getToken() );
            props.putString( PROPERTY_ACCESS_SECRET, accessToken.getTokenSecret() );
        } else {
            props.remove( PROPERTY_ACCESS_TOKEN );
            props.remove( PROPERTY_ACCESS_SECRET );
        }

        if ( token != null ) {
            props.putString( PROPERTY_TOKEN, token );
        } else {
            props.remove( PROPERTY_TOKEN );
        }

        props.commit();
    }

    public Token getAccessToken()
    {
        return accessToken;
    }

    public Token getRequestToken()
    {
        return requestToken;
    }

    void signRequest( OAuthRequest request )
    {
        service.signRequest( accessToken, request );
    }

    Response execute( OAuthRequest request )
            throws IOException
    {
        try {
            return service.execute( request );
        } catch ( InterruptedException | ExecutionException ex ) {
            throw new IOException( "Error executing request", ex );
        }
    }

    String retrieveAuthorizationUrl()
            throws IOException
    {
        try {
            requestToken = service.getRequestToken();
            String authorizationUrl = service.getAuthorizationUrl( requestToken );

            save();
            return authorizationUrl;

        } catch ( InterruptedException | ExecutionException ex ) {
            throw new IOException( "Error getting authorization URL", ex );
        }
    }

    void retrieveAccessToken( String verifier, String token )
            throws IOException
    {
        try {
            accessToken = service.getAccessToken( requestToken, verifier );

            this.token = token;
            requestToken = null; // Invalidate
            save();

        } catch ( InterruptedException | ExecutionException ex ) {
            throw new IOException( "Error getting access token", ex );
        }
    }

    public void clear()
    {
        requestToken = null;
        accessToken = null;
        token = null;
        save();
    }

    @Override
    public void close()
            throws IOException
    {
        service.close();
    }

}
