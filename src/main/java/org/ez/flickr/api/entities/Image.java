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
package org.ez.flickr.api.entities;

import java.io.Serializable;
import java.net.URL;
import java.text.MessageFormat;
import org.json.JSONException;
import org.json.JSONObject;
import org.ez.flickr.api.utils.URLUtils;

/**
 * This class represents an URL of an image as described in the Flickr API. see
 * http://www.flickr.com/services/api/misc.urls.html
 */
public class Image
        implements Serializable
{

    private static final long serialVersionUID = 6546344763489L;
    /**
     * Small square (75x75)
     */
    public static final String SMALL_SQUARE = "s";
    /**
     * Large square (150x150)
     */
    public static final String LARGE_SQUARE = "q";
    /**
     * Thumbnail, 100 on largest side
     */
    public static final String THUMBNAIL_100 = "t";
    /**
     * Small, 240 on largest side
     */
    public static final String SMALL_240 = "m";
    /**
     * Small, 320 on longest side
     */
    public static final String SMALL_320 = "n";
    /**
     * Medium, 640 on longest side
     */
    public static final String MEDIUM_640 = "z";
    /**
     * Medium, 800 on the longest side
     */
    public static final String MEDIUM_800 = "c";
    /**
     * Large, 1024 on the longest side
     */
    public static final String MEDIUM_1024 = "b";
    /**
     * The original size
     */
    public static final String ORIGINAL = "0";
    private final String prefix;

    Image( String prefix )
    {
        this.prefix = prefix;
    }

    Image( String farm, String server, String primary, String secret )
    {
        prefix = MessageFormat.format( "https://farm{0}.staticflickr.com/{1}/{2}_{3}_", farm, server, primary, secret );
    }

    Image( JSONObject json )
            throws JSONException
    {
        String id;
        if ( json.has( "id" ) ) {
            id = json.getString( "id" );
        } else {
            id = json.getString( "primary" );
        }

        prefix = MessageFormat.format( "https://farm{0}.static.flickr.com/{1}/{2}_{3}_",
                                       json.getString( "farm" ), json.getString( "server" ),
                                       id,
                                       json.getString( "secret" ) );
    }

    /**
     * Get the image URL with the given size
     *
     * @param size The size
     * @return The resulting URL
     */
    public URL getURL( String size )
    {
        return URLUtils.fromString( prefix + size + ".jpg" );
    }

}
