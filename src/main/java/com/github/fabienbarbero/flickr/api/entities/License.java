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
 *
 * @author Fabien Barbero
 */
public class License
        implements IdObject
{

    private final String id;
    private final String name;
    private final String url;

    License( JSONObject json )
            throws JSONException
    {
        id = json.getString( "id" );
        name = json.getString( "name" );
        url = json.getString( "url" );
    }

    /**
     * Get the license URL
     *
     * @return The URL
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * Get the license name
     *
     * @return The license name
     */
    public String getName()
    {
        return name;
    }

    @Override
    public String getId()
    {
        return id;
    }

}
