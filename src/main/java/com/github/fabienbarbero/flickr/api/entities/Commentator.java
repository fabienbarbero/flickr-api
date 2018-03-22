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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents a user who write a comment.
 *
 * @author Fabien Barbero
 */
public class Commentator
        implements BaseUser
{

    private final String id;
    private final String name;
    private final String realname;
    private final Avatar avatar;

    Commentator( JSONObject json )
            throws JSONException
    {
        id = json.getString( "author" );
        name = json.getString( "authorname" );
        realname = json.getString( "realname" );
        avatar = new Avatar( json, id );
    }

    /**
     * Get the commentator avatar
     *
     * @return The avatar
     */
    public Avatar getAvatar()
    {
        return avatar;
    }

    @Override
    public String getRealName()
    {
        return realname;
    }

    @Override
    public String getUserName()
    {
        return name;
    }

    @Override
    public String getName()
    {
        if ( realname != null && !realname.isEmpty() ) {
            return realname;
        } else {
            return name;
        }
    }

    @Override
    public String getId()
    {
        return id;
    }

}
