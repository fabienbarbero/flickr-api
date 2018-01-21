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

import com.github.fabienbarbero.flickr.api.utils.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class GroupInfos
        implements IdObject
{

    private final String name;
    private final String description;
    private final String rules;
    private final int members;
    private final String blast;
    private final String id;

    GroupInfos( JSONObject json )
            throws JSONException
    {
        id = json.getString( "id" );
        name = JSONUtils.getContent( json, "name" );
        description = JSONUtils.getContent( json, "description" );
        rules = JSONUtils.getContent( json, "rules" );
        members = JSONUtils.getIntegerContent( json, "members" );
        blast = JSONUtils.getContent( json, "blast" );
        // FIXME: add throttle
    }

    /**
     * Get the group rules
     *
     * @return The rules
     */
    public String getRules()
    {
        return rules;
    }

    /**
     * Get the name of the group
     *
     * @return The name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the members count
     *
     * @return The members count
     */
    public int getMembers()
    {
        return members;
    }

    /**
     * Get the group description
     *
     * @return The description
     */
    public String getDescription()
    {
        return description;
    }

    public String getBlast()
    {
        return blast;
    }

    @Override
    public String getId()
    {
        return id;
    }

}
