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

import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import com.github.fabienbarbero.flickr.api.utils.JSONUtils;

/**
 *
 * @author Fabien Barbero
 */
public class UserInfo
        implements BaseUser
{

    private static final long serialVersionUID = -5126309551528400272L;
    //
    private final String id;
    private final int isPro;
    private final String description;
    private final String userName;
    private String location;
    private String realName;
    private final URL photosUrl;
    private final URL profileUrl;
    private final UserPhotosInfo photosInfo;
    private final Avatar avatar;

    UserInfo( JSONObject json )
            throws JSONException
    {
        id = json.getString( "nsid" );
        isPro = json.getInt( "ispro" );
        description = JSONUtils.getContent( json, "description" );
        userName = JSONUtils.getContent( json, "username" );
        if ( json.has( "realname" ) ) {
            realName = JSONUtils.getContent( json, "realname" );
        }
        if ( json.has( "location" ) ) {
            location = JSONUtils.getContent( json, "location" );
        }
        photosUrl = JSONUtils.urlFromString( JSONUtils.getContent( json, "photosurl" ) );
        profileUrl = JSONUtils.urlFromString( JSONUtils.getContent( json, "profileurl" ) );
        photosInfo = new UserPhotosInfo( json.getJSONObject( "photos" ) );
        avatar = new Avatar( json, id );
    }

    @Override
    public String getId()
    {
        return id;
    }

    /**
     * Get the user avatar
     *
     * @return The avatar
     */
    public Avatar getAvatar()
    {
        return avatar;
    }

    /**
     * Get the user location
     *
     * @return The location or null
     */
    public String getLocation()
    {
        return location;
    }

    /**
     * Indicates if the user owns a "pro" account.
     *
     * @return true if the user owns a "pro" account.
     */
    public boolean isPro()
    {
        return isPro == 1;
    }

    /**
     * Get the user description
     *
     * @return The description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Get the information about the user's photos.
     *
     * @return The photos information.
     */
    public UserPhotosInfo getPhotosInfo()
    {
        return photosInfo;
    }

    /**
     * Get the URL of the user's photos.
     *
     * @return The URL.
     */
    public URL getPhotosUrl()
    {
        return photosUrl;
    }

    /**
     * Get the URL of the user's profile.
     *
     * @return The URL.
     */
    public URL getProfileUrl()
    {
        return profileUrl;
    }

    @Override
    public String getRealName()
    {
        return realName;
    }

    @Override
    public String getUserName()
    {
        return userName;
    }

    @Override
    public String toString()
    {
        return userName;
    }

}
