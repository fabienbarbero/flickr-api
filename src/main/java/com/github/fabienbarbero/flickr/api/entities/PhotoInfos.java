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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class PhotoInfos
        implements Serializable
{

    private final Owner owner;
    private final String title;
    private final String description;
    private final PhotoVisibility visibility;
    private final PhotoDates dates;
    private final PhotoUsage usage;
    private final Date uploadedDate;
    private final int comments;
    private final int views;
    private final List<PhotoTag> tags;
    private final PhotoEditability editability;
    private final PhotoEditability publicEditability;
    private final boolean isFavorite;
    private final String license;
    private final PhotoLocation location;

    PhotoInfos( JSONObject json )
            throws JSONException
    {
        owner = new Owner( json.getJSONObject( "owner" ) );
        title = JSONUtils.getContent( json, "title" );
        description = JSONUtils.getContent( json, "description" );
        visibility = new PhotoVisibility( json.getJSONObject( "visibility" ) );
        dates = new PhotoDates( json.getJSONObject( "dates" ) );
        usage = new PhotoUsage( json.getJSONObject( "usage" ) );
        comments = JSONUtils.getIntegerContent( json, "comments" );
        uploadedDate = JSONUtils.dateFromString( json.getString( "dateuploaded" ) );
        editability = new PhotoEditability( json.getJSONObject( "editability" ) );
        publicEditability = new PhotoEditability( json.getJSONObject( "publiceditability" ) );
        isFavorite = json.getInt( "isfavorite" ) == 1;
        license = json.getString( "license" );
        if ( json.has( "location" ) ) {
            location = new PhotoLocation( json.getJSONObject( "location" ) );
        } else {
            location = null;
        }
        views = json.getInt( "views" );

        tags = new ArrayList<PhotoTag>();
        JSONArray array = json.getJSONObject( "tags" ).getJSONArray( "tag" );
        for ( int i = 0; i < array.length(); i++ ) {
            tags.add( new PhotoTag( array.getJSONObject( i ) ) );
        }
    }

    /**
     * Get the number of comments.
     *
     * @return The number of comments.
     */
    public int getCommentsCount()
    {
        return comments;
    }

    /**
     * Get the dates of the photo.
     *
     * @return The dates of the photo.
     */
    public PhotoDates getDates()
    {
        return dates;
    }

    /**
     * Get the description of the photo.
     *
     * @return The description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Get the owner of the photo.
     *
     * @return The owner.
     */
    public Owner getOwner()
    {
        return owner;
    }

    /**
     * Get the photo tags
     *
     * @return The tags
     */
    public List<PhotoTag> getTags()
    {
        return tags;
    }

    /**
     * Get the editability informations of the photo
     *
     * @return The informations
     */
    public PhotoEditability getEditability()
    {
        return editability;
    }

    /**
     * Get the license identifier
     *
     * @return The license
     */
    public String getLicense()
    {
        return license;
    }

    /**
     * Get the photo location
     *
     * @return The location or null if not present
     */
    public PhotoLocation getLocation()
    {
        return location;
    }

    /**
     * Get the editability informations of the photo
     *
     * @return The informations
     */
    public PhotoEditability getPublicEditability()
    {
        return publicEditability;
    }

    /**
     * Get the date when the photo has been uploaded
     *
     * @return The upload date
     */
    public Date getUploadedDate()
    {
        return uploadedDate;
    }

    /**
     * Get the views count
     *
     * @return The views count
     */
    public int getViews()
    {
        return views;
    }

    /**
     * Indicates if the photo is a favorite
     *
     * @return true if the photo is a favorite, false otherwise
     */
    public boolean isFavorite()
    {
        return isFavorite;
    }

    /**
     * Get the title of the photo.
     *
     * @return The title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Get the photo usages
     *
     * @return the usages
     */
    public PhotoUsage getUsage()
    {
        return usage;
    }

    /**
     * Get the photo visibility
     *
     * @return The visibility
     */
    public PhotoVisibility getVisibility()
    {
        return visibility;
    }

    @Override
    public String toString()
    {
        return title + " - " + description;
    }

}
