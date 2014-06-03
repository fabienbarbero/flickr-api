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
package com.flickr.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Proxy;
import java.util.Map;
import org.apache.http.entity.mime.MultipartEntity;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;

/**
 *
 * @author Fabien Barbero
 */
public abstract class FlickrService
{

    public static final int MAX_PER_PAGE = Integer.MAX_VALUE;
    private static final String URL_PREFIX = "https://api.flickr.com/services/rest";
    private final OAuthHandler oauth;

    private Proxy proxy = null;

    FlickrService( OAuthHandler oauth )
    {
        this.oauth = oauth;
    }

    void setProxy( Proxy proxy )
    {
        this.proxy = proxy;
    }

    final <T extends ServerResponse> T doGet( CommandArguments args, Class<T> clazz )
            throws FlickrException
    {
        OAuthRequest request = new OAuthRequest( Verb.GET, URL_PREFIX );

        // check for proxy, use if available
        if ( proxy != null ) {
//            request.setProxy(proxy);
        }

        for ( Map.Entry<String, Object> param : args.getParameters().entrySet() ) {
            request.addQuerystringParameter( param.getKey(), String.valueOf( param.getValue() ) );
        }

        oauth.signRequest( request );
        Response response = request.send();
        String body = response.getBody();

        return parseBody( args, clazz, body );
    }

    final <T extends ServerResponse> T doPost( CommandArguments args, Class<T> clazz )
            throws FlickrException
    {
        return doPost( args, clazz, URL_PREFIX );
    }

    final <T extends ServerResponse> T doPost( CommandArguments args, Class<T> clazz, String url )
            throws FlickrException
    {
        try {
            OAuthRequest request = new OAuthRequest( Verb.POST, url );

            // check for proxy, use if available
            if ( proxy != null ) {
//                request.setProxy(proxy);
            }

            for ( Map.Entry<String, Object> param : args.getParameters().entrySet() ) {
                if ( param.getValue() instanceof String ) {
                    request.addQuerystringParameter( param.getKey(), ( String ) param.getValue() );
                }
            }

            oauth.signRequest( request );

            MultipartEntity multipart = args.getBody( request.getOauthParameters() );
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            multipart.writeTo( baos );

            request.addPayload( baos.toByteArray() );
            request.addHeader( "Content-type", multipart.getContentType().getValue() );

            Response response = request.send();
            String body = response.getBody();

            return parseBody( args, clazz, body );

        } catch ( IOException ex ) {
            throw new UnsupportedOperationException( "Error preparing multipart request", ex );
        }
    }

    private <T extends ServerResponse> T parseBody( CommandArguments args, Class<T> clazz, String body )
            throws FlickrException
    {
        try {
            if ( Flickr.debug ) {
                try {
                    System.out.println( "Server response for method " + args.getMethod() + "\n" + body );
                } catch ( Exception ignored ) {
                }
            }

            T instance = clazz.newInstance();
            instance.read( body, args.getMethod() );

            return instance;

        } catch ( FlickrException ex ) {
            throw ex;
        } catch ( IllegalStateException ex ) {
            throw new FlickrException( "Server request error", ex );
        } catch ( InstantiationException ex ) {
            throw new FlickrException( "Server request error", ex );
        } catch ( IllegalAccessException ex ) {
            throw new FlickrException( "Server request error", ex );
        }
    }

}
