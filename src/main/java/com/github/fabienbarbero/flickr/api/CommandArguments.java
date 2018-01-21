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

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class used to build a REST request with the valid arguments. Each command must provide the valid method defined in
 * the Flickr API web page.
 *
 * @author Fabien Barbero
 */
final class CommandArguments
{

    private static final ContentType CT_TEXT = ContentType.create( "text/plain", "UTF-8" );
    private final List<Parameter> params = new ArrayList<>();
    private final String method;

    /**
     * Create a new command
     *
     * @param method The method name
     */
    public CommandArguments( String method )
    {
        this.method = method;
        if ( method != null ) {
            params.add( new Parameter( true, "method", method ) );
        }
        params.add( new Parameter( true, "format", "json" ) );
        params.add( new Parameter( true, "nojsoncallback", "1" ) );
    }

    public CommandArguments()
    {
        this( null );
    }

    public String getMethod()
    {
        return method;
    }

    public void addParam( String key, Object value )
    {
        params.add( new Parameter( false, key, value ) );
    }

    public void addParam( String key, boolean value )
    {
        if ( value ) {
            params.add( new Parameter( false, key, "1" ) );
        } else {
            params.add( new Parameter( false, key, "0" ) );
        }
    }

    Map<String, Object> getParameters()
    {
        Map<String, Object> map = new HashMap<>();
        for ( Parameter param : params ) {
            map.put( param.key, param.value );
        }
        return map;
    }

    public HttpEntity getBody( Map<String, String> additionalParameters )
    {
        MultipartEntityBuilder entity = MultipartEntityBuilder.create();

        for ( Parameter param : params ) {
            if ( !param.internal ) {
                if ( param.value instanceof File ) {
                    entity.addBinaryBody( param.key, ( File ) param.value );
                } else if ( param.value instanceof String ) {
                    entity.addTextBody( param.key, ( String ) param.value, CT_TEXT );
                }
            }
        }
        for ( Map.Entry<String, String> entry : additionalParameters.entrySet() ) {
            entity.addTextBody( entry.getKey(), entry.getValue(), CT_TEXT );
        }

        return entity.build();
    }

    private class Parameter
            implements Comparable<Parameter>
    {

        private final boolean internal;
        private final String key;
        private final Object value;

        private Parameter( boolean internal, String key, Object value )
        {
            this.internal = internal;
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo( Parameter another )
        {
            return key.compareTo( another.key );
        }

    }

}
