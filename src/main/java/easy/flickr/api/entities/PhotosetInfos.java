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

import easy.flickr.api.utils.JSONUtils;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents the informations of a photoset
 *
 * @author Fabien Barbero
 */
public class PhotosetInfos
        implements IdObject
{

    private final String id;
    private final boolean canComment;
    private final int commentsCount;
    private final int viewsCount;
    private final Date createDate;
    private final Date updateDate;
    private final String description;
    private final String owner;

    PhotosetInfos( JSONObject json )
            throws JSONException
    {
        id = json.getString( "id" );
        canComment = json.getInt( "can_comment" ) == 1;
        commentsCount = json.getInt( "count_comments" );
        viewsCount = json.getInt( "count_views" );
        createDate = JSONUtils.dateFromString( json.getString( "date_create" ) );
        updateDate = JSONUtils.dateFromString( json.getString( "date_update" ) );
        description = JSONUtils.getContent( json, "description" );
        owner = json.getString( "owner" );
    }

    /**
     * Get the last update date of the set
     *
     * @return The last update
     */
    public Date getUpdateDate()
    {
        return updateDate;
    }

    /**
     * Get the views count of the ser
     *
     * @return The views count
     */
    public int getViewsCount()
    {
        return viewsCount;
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
     * Get the owner (user) identifier
     *
     * @return The owner
     */
    public String getOwnerId()
    {
        return owner;
    }

    /**
     * Get the set description
     *
     * @return The description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Get the set creation date
     *
     * @return The creation date
     */
    public Date getCreateDate()
    {
        return createDate;
    }

    /**
     * Get the comments count
     *
     * @return The comments count
     */
    public int getCommentsCount()
    {
        return commentsCount;
    }

    @Override
    public String getId()
    {
        return id;
    }

}
