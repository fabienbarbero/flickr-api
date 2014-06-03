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
package com.flickr.api.entities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class Owner
        implements BaseUser
{

    private final String id;
    private final String username;
    private final String realname;
    private final String location;
    private final Avatar avatar;

    Owner( JSONObject json )
            throws JSONException
    {
        id = json.getString( "nsid" );
        username = json.getString( "username" );
        realname = json.getString( "realname" );
        location = json.getString( "location" );
        avatar = new Avatar( json, id );
    }

    /**
     * Get the user avatar
     *
     * @return The avatar
     */
    public Avatar getAvatar()
    {
        return avatar;
    }

    /**
     * Get the user location
     *
     * @return The location
     */
    public String getLocation()
    {
        return location;
    }

    @Override
    public String getRealName()
    {
        return realname;
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
    public String toString()
    {
        return realname;
    }

}
