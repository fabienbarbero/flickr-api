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
package easy.flickr.api.entities;

import org.json.JSONException;
import org.json.JSONObject;
import easy.flickr.api.utils.JSONUtils;
import java.util.Date;

/**
 *
 * @author Fabien Barbero
 */
public class Photoset
        implements IdObject
{

    private static final long serialVersionUID = 545748673399L;

    private final String id;
    private final int photos;
    private final String owner;
    private final String title;
    private final String description;
    private final int countViews;
    private final Image primaryPhoto;
    private final boolean canComment;
    private final int commentCount;
    private final Date creationDate;
    private final Date updateDate;

    Photoset( JSONObject json )
            throws JSONException
    {
        id = json.getString( "id" );
        photos = json.getInt( "photos" );
        owner = json.optString( "owner" );
        title = JSONUtils.getContent( json, "title" );
        description = JSONUtils.getContent( json, "description" );
        countViews = json.getInt( "count_views" );
        primaryPhoto = new Image( json.getString( "farm" ), json.getString( "server" ), json.getString( "primary" ), json.getString( "secret" ) );
        canComment = json.getInt( "can_comment" ) == 1;
        commentCount = json.getInt( "count_comments" );
        creationDate = JSONUtils.dateFromString( json.getString( "date_create" ) );
        updateDate = JSONUtils.dateFromString( json.getString( "date_update" ) );
    }

    @Override
    public String getId()
    {
        return id;
    }

    /**
     * Indicates if comments can be added to the set
     *
     * @return true if comments can be added, false otherwise
     */
    public boolean canComment()
    {
        return canComment;
    }

    /**
     * Get the comments count
     *
     * @return The comments count
     */
    public int getCommentCount()
    {
        return commentCount;
    }

    /**
     * Get the set creation date
     *
     * @return The creation date
     */
    public Date getCreationDate()
    {
        return creationDate;
    }

    /**
     * Get the last set update date
     *
     * @return The last update date
     */
    public Date getUpdateDate()
    {
        return updateDate;
    }

    /**
     * Get the number of photos in the set.
     *
     * @return The number of photos.
     */
    public int getPhotosCount()
    {
        return photos;
    }

    /**
     * Get the title of the set.
     *
     * @return The title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Get the description of the set.
     *
     * @return The description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Get the number of views of the set.
     *
     * @return The number of views.
     */
    public int getCountViews()
    {
        return countViews;
    }

    /**
     * Get the primary photo.
     *
     * @return The primary photo.
     */
    public Image getPrimaryPhoto()
    {
        return primaryPhoto;
    }

    @Override
    public String toString()
    {
        return title;
    }

}
