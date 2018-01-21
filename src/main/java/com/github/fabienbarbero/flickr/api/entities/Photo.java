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
public class Photo
        implements BasePhoto
{

    private static final long serialVersionUID = 5438438431425L;
    //
    private final String id;
    private final String title;
    private final Image url;
    private final boolean isFamily;
    private final boolean isFriend;
    private final boolean isPublic;
    private final boolean isPrimary;
    private final String owner;

    public Photo( JSONObject json )
            throws JSONException
    {
        id = json.getString( "id" );
        title = json.getString( "title" );
        url = new Image( json );
        isFamily = json.optInt( "isfamily", 0 ) == 1;
        isFriend = json.optInt( "isfriend", 0 ) == 1;
        isPublic = json.optInt( "ispublic", 0 ) == 1;
        isPrimary = json.optInt( "isprimary", 0 ) == 1;
        owner = json.optString( "owner" );
    }

    /**
     * Get the identifier of the photo.
     *
     * @return The identifier.
     */
    @Override
    public String getId()
    {
        return id;
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
     * Get the photo image URL
     *
     * @return The image URL
     */
    public Image getImage()
    {
        return url;
    }

    /**
     * Indicates if this photo has been taken by a member of the user family
     *
     * @return true if this photo has been taken by a member of the user family, false otherwise
     */
    public boolean isFamily()
    {
        return isFamily;
    }

    /**
     * Indicates if this photo has been taken by a friend of the user
     *
     * @return true if this photo has been taken by a friend of the user, false otherwise
     */
    public boolean isFriend()
    {
        return isFriend;
    }

    /**
     * Indicates if this photo is public
     *
     * @return true if this photo is public, false otherwise
     */
    public boolean isPublic()
    {
        return isPublic;
    }

    /**
     * Get the owner identifier
     *
     * @return The owner
     */
    public String getOwner()
    {
        return owner;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Photo other = ( Photo ) obj;
        if ( ( this.id == null ) ? ( other.id != null ) : !this.id.equals( other.id ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return title;
    }

}
