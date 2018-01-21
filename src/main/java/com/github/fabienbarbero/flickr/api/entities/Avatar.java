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
import java.io.Serializable;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class Avatar
        implements Serializable
{

    /**
     * The default avatar URL (must be used if a user did not set any avatar).
     */
    public static final URL DEFAULT_AVATAR = URLUtils.fromString( "https://www.flickr.com/images/buddyicon.gif" );

    /**
     * Small square (48x48)
     */
    public static final String SMALL_SQUARE = "s";
    /**
     * Medium square (100x100)
     */
    public static final String MEDIUM_SQUARE = "m";
    /**
     * Original image
     */
    public static final String ORIGINAL = "r";
    //
    private final int iconServer;
    private final String iconFarm;
    private final String userId;

    Avatar( JSONObject json, String userId )
    {
        this.iconServer = json.optInt( "iconserver", 0 );
        this.iconFarm = json.optString( "iconfarm", null );
        this.userId = userId;
    }

    /**
     * Get a download URL for an avatar size
     *
     * @param size The size to choose
     * @return The URL
     */
    public URL getUrl( String size )
    {
        if ( iconServer > 0 && iconFarm != null ) {
            if ( size.equals( SMALL_SQUARE ) ) {
                return URLUtils.fromString( "https://farm" + iconFarm + ".staticflickr.com/" + iconServer + "/buddyicons/" + userId + ".jpg" );
            } else {
                return URLUtils.fromString( "https://farm" + iconFarm + ".staticflickr.com/" + iconServer + "/buddyicons/" + userId + "_" + size + ".jpg" );
            }
        } else {
            return DEFAULT_AVATAR;
        }
    }

}
