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
package org.ez.flickr.api.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public abstract class PaginatedResponse<T>
        extends JSONResponse
{

    private Paginated<T> value;

    @Override
    protected final void readObject( JSONObject json )
            throws JSONException
    {
        List<T> values = new ArrayList<T>();

        JSONObject parent = find( json, JSONObject.class );
        JSONArray array = find( parent, JSONArray.class );
        if ( array != null ) {
            for ( int i = 0; i < array.length(); i++ ) {
                values.add( unmarshall( array.getJSONObject( i ) ) );
            }
        }

        value = new Paginated<T>( parent, values );
    }

    private <T> T find( JSONObject json, Class<T> clazz )
            throws JSONException
    {
        Iterator<String> it = json.keys();
        while ( it.hasNext() ) {
            Object obj = json.get( it.next() );
            if ( clazz.isInstance( obj ) ) {
                return ( T ) obj;
            }
        }
        return null;
    }

    protected abstract T unmarshall( JSONObject json )
            throws JSONException;

    public final Paginated<T> getPaginated()
    {
        return value;
    }

}
