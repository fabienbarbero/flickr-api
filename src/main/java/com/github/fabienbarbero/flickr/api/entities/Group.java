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

import com.github.fabienbarbero.flickr.api.utils.URLUtils;
import java.net.URL;
import java.text.MessageFormat;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class Group
        implements IdObject
{

    private final String id;
    private final String name;
    private final int photos;
    private final URL cover;

    Group( JSONObject json )
            throws JSONException
    {
        id = json.getString( "nsid" );
        name = json.getString( "name" );
        photos = json.optInt( "photos", -1 );

        cover = URLUtils.fromString( MessageFormat.format( "https://farm{0}.staticflickr.com/{1}/coverphoto/{2}_s.jpg",
                                                           json.getString( "iconfarm" ), json.getString( "iconserver" ), id ) );
    }

    /**
     * Get the group primary image
     *
     * @return The group image
     */
    public URL getCover()
    {
        return cover;
    }

    /**
     * Get the photos count in the group
     *
     * @return The photos count
     */
    public int getPhotos()
    {
        return photos;
    }

    /**
     * Get the group name
     *
     * @return the group name
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
