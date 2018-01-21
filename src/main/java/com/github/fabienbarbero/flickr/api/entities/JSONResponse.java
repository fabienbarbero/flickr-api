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
package com.github.fabienbarbero.flickr.api.entities;

import com.github.fabienbarbero.flickr.api.FlickrErrorCode;
import com.github.fabienbarbero.flickr.api.FlickrException;
import com.github.fabienbarbero.flickr.api.ServerResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public abstract class JSONResponse
        implements ServerResponse
{

    @Override
    public final void read( String data, String method )
            throws FlickrException
    {
        try {
            JSONObject json = new JSONObject( data );

            ResponseStatus status = ResponseStatus.valueOf( json.getString( "stat" ) );

            if ( status == ResponseStatus.fail ) {
                FlickrErrorCode code = FlickrErrorCode.fromCode( json.optInt( "code" ) );
                String message = json.optString( "message" );
                throw new FlickrException( "Error calling method '" + method + "' (" + message + ")", code );
            }

            readObject( json );

        } catch ( JSONException ex ) {
            throw new FlickrException( "Error parsing JSON response: " + data, ex );
        }
    }

    protected abstract void readObject( JSONObject json )
            throws JSONException;

}
