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
 *
 * @author Fabien Barbero
 */
public class Comment
        implements IdObject
{

    private final String id;
    private final Date creationDate;
    private final String value;
    private final Commentator commentator;

    Comment( JSONObject json )
            throws JSONException
    {
        id = json.getString( "id" );
        creationDate = JSONUtils.dateFromString( json.getString( "datecreate" ) );
        commentator = new Commentator( json );
        value = json.getString( "_content" );
    }

    /**
     * Get the user who set the comment
     *
     * @return The commentator
     */
    public Commentator getCommentator()
    {
        return commentator;
    }

    /**
     * Get the comment creation date
     *
     * @return The creation date
     */
    public Date getCreationDate()
    {
        return creationDate;
    }

    /**
     * Get the comment text
     *
     * @return The text
     */
    public String getValue()
    {
        return value;
    }

    @Override
    public String getId()
    {
        return id;
    }

}
