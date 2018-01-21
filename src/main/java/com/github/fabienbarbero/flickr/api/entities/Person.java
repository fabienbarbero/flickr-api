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

import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import com.github.fabienbarbero.flickr.api.utils.JSONUtils;

/**
 *
 * @author Fabien Barbero
 */
public class Person
        implements BaseUser
{

    private final String id;
    private final String username;
    private final Date favedate;

    Person( JSONObject json )
            throws JSONException
    {
        id = json.getString( "nsid" );
        username = json.getString( "username" );
        favedate = JSONUtils.dateFromString( json.getString( "favedate" ) );
    }

    /**
     * Get the date the person add the favorite.
     *
     * @return The date.
     */
    public Date getFaveDate()
    {
        return favedate;
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
    public String toString()
    {
        return username;
    }

}
