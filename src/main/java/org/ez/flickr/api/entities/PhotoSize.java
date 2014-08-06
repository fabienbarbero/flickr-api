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

import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import org.ez.flickr.api.utils.JSONUtils;
import java.io.Serializable;

/**
 *
 * @author Fabien Barbero
 */
public class PhotoSize
        implements Serializable
{

    private final String label;
    private final int width;
    private final int height;
    private final URL source;

    PhotoSize( JSONObject json )
            throws JSONException
    {
        label = json.getString( "label" );
        width = json.getInt( "width" );
        height = json.getInt( "height" );
        source = JSONUtils.urlFromString( json.getString( "source" ) );
    }

    /**
     * Get the height of the image.
     *
     * @return The height.
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Get the label of the image.
     *
     * @return The label.
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * Get the width of the image.
     *
     * @return The width.
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Get the image with the correct size.
     *
     * @return The image URL
     */
    public URL getSource()
    {
        return source;
    }

    public boolean isLandscape()
    {
        return width > height;
    }

    public boolean isPortrait()
    {
        return width < height;
    }

    public boolean isSquare()
    {
        return width == height;
    }

    @Override
    public String toString()
    {
        return label + "( " + width + "x" + height + ")";
    }

}
