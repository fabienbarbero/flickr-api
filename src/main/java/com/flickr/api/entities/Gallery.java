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

import com.flickr.api.utils.JSONUtils;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class Gallery
        implements IdObject
{

    private final String id;
    private final String url;
    private final Date creationDate;
    private final Date updateDate;
    private final Image primaryImage;
    private final int photos;
    private final int videos;
    private final String title;
    private String description;

    Gallery( JSONObject json )
            throws JSONException
    {
        id = json.getString( "id" );
        url = json.getString( "url" );
        creationDate = JSONUtils.dateFromString( json.getString( "date_create" ) );
        updateDate = JSONUtils.dateFromString( json.getString( "date_update" ) );
        photos = json.getInt( "count_photos" );
        videos = json.getInt( "count_videos" );
        title = JSONUtils.getContent( json, "title" );
        if ( json.has( "description" ) ) {
            description = JSONUtils.getContent( json, "description" );
        }
        primaryImage = new Image(
                json.getString( "primary_photo_farm" ),
                json.getString( "primary_photo_server" ),
                json.getString( "primary_photo_id" ),
                json.getString( "primary_photo_secret" ) );
    }

    /**
     * Get the gallery URL
     *
     * @return The URL
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * Get the last gallery update date
     *
     * @return The last update date
     */
    public Date getUpdateDate()
    {
        return updateDate;
    }

    /**
     * Get the primary image
     *
     * @return The image
     */
    public Image getPrimaryImage()
    {
        return primaryImage;
    }

    /**
     * Get the gallery creation date
     *
     * @return The creation date
     */
    public Date getCreationDate()
    {
        return creationDate;
    }

    /**
     * Get the videos count
     *
     * @return The videos count
     */
    public int getVideos()
    {
        return videos;
    }

    /**
     * Get the gallery title
     *
     * @return The title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Get the photos count
     *
     * @return The photos count
     */
    public int getPhotos()
    {
        return photos;
    }

    /**
     * Get the gallery description
     *
     * @return The description
     */
    public String getDescription()
    {
        return description;
    }

    @Override
    public String getId()
    {
        return id;
    }

}
