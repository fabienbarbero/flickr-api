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

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class TotalViews
        implements Serializable
{

    private final int total;
    private final int photos;
    private final int photosets;
    private final int photoStream;
    private final int collections;
    private final int galleries;

    TotalViews( JSONObject json )
            throws JSONException
    {
        total = json.getJSONObject( "total" ).getInt( "views" );
        photos = json.getJSONObject( "photos" ).getInt( "views" );
        photosets = json.getJSONObject( "sets" ).getInt( "views" );
        photoStream = json.getJSONObject( "photostream" ).getInt( "views" );
        collections = json.getJSONObject( "collections" ).getInt( "views" );
        galleries = json.getJSONObject( "galleries" ).getInt( "views" );
    }

    public int getCollectionsViews()
    {
        return collections;
    }

    public int getGalleriesViews()
    {
        return galleries;
    }

    public int getPhotoStreamViews()
    {
        return photoStream;
    }

    /**
     * Get the photos views count
     *
     * @return The count
     */
    public int getPhotosViews()
    {
        return photos;
    }

    /**
     * Get the photoset views count
     *
     * @return The count
     */
    public int getPhotosetsViews()
    {
        return photosets;
    }

    /**
     * Get the total views count
     *
     * @return The count
     */
    public int getTotalViews()
    {
        return total;
    }

}
