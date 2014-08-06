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

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class User
        implements BaseUser
{

    private static final long serialVersionUID = -1178719861430017441L;

    private final String id;
    private final String username;

    User( JSONObject json )
            throws JSONException
    {
        id = json.getString( "nsid" );
        username = json.getString( "username" );
    }

    @Override
    public String getRealName()
    {
        return username;
    }

    @Override
    public String getUserName()
    {
        return username;
    }

    @Override
    public String getId()
    {
        return id;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj == null ) {
            return false;
        }
        if ( obj instanceof User ) {
            User user = ( User ) obj;
            return id.equals( user.id );
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 19 * hash + ( this.id != null ? this.id.hashCode() : 0 );
        return hash;
    }

}
