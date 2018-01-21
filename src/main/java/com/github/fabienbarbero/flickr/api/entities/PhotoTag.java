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
 * Represents a tag on a photo
 *
 * @author Fabien Barbero
 */
public class PhotoTag
        implements IdObject
{

    private final String id;
    private final String author;
    private final String raw;
    // FIXME
    private final String tag;

    PhotoTag( JSONObject json )
            throws JSONException
    {
        id = json.getString( "id" );
        author = json.getString( "author" );
        raw = json.getString( "raw" );
        tag = json.getString( "_content" );
    }

    /**
     * Get the author (user) identifier
     *
     * @return The author
     */
    public String getAuthorId()
    {
        return author;
    }

    @Override
    public String getId()
    {
        return id;
    }

    /**
     * Get the "raw" value of the tag
     *
     * @return The raw value
     */
    public String getRaw()
    {
        return raw;
    }

    /**
     * Get the readable value of the tag
     *
     * @return The tag value
     */
    public String getTag()
    {
        return tag;
    }

    @Override
    public String toString()
    {
        return raw;
    }

}
