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
 * Represents a user contact
 *
 * @author Fabien Barbero
 */
public class Contact
        implements BaseUser
{

    private final String id;
    private final String username;
    private final String location;
    private final int friend;
    private final int family;
    private final int ignored;
    private final Avatar avatar;
    private final String realname;

    Contact( JSONObject json )
            throws JSONException
    {
        id = json.getString( "nsid" );
        username = json.getString( "username" );
        realname = json.optString( "realname", null );
        location = json.optString( "location", null );
        friend = json.optInt( "friend", 0 );
        family = json.optInt( "family", 0 );
        ignored = json.optInt( "ignored", 0 );
        avatar = new Avatar( json, id );
    }

    /**
     * Get the contact avatar
     *
     * @return The avatar
     */
    public Avatar getAvatar()
    {
        return avatar;
    }

    /**
     * Indicates if the contact is a member of the family.
     *
     * @return true if the contact is a member of the family.
     */
    public boolean isFamily()
    {
        return family == 1;
    }

    /**
     * Indicates if the contact is a friend.
     *
     * @return true if the contact is a friend.
     */
    public boolean isFriend()
    {
        return friend == 1;
    }

    /**
     * Indicates if the contact is ignored.
     *
     * @return true if the contact is ignored.
     */
    public boolean isIgnored()
    {
        return ignored == 1;
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

    /**
     * Get the location of the contact
     *
     * @return The location or null
     */
    public String getLocation()
    {
        return location;
    }

    @Override
    public String getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        return username;
    }

}
