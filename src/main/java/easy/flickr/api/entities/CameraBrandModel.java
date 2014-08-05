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
import easy.flickr.api.utils.URLUtils;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class CameraBrandModel
        implements IdObject
{

    private final String id;
    private final String name;
    private URL smallImage;
    private URL largeImage;
    private String megapixels;
    private String lcdScreenSize;
    private String memoryType;

    CameraBrandModel( JSONObject json )
            throws JSONException
    {
        id = json.getString( "id" );
        name = JSONUtils.getContent( json, "name" );

        if ( json.has( "images" ) ) {
            JSONObject imageObj = json.getJSONObject( "images" );
            smallImage = URLUtils.fromString( JSONUtils.getContent( imageObj, "small" ) );
            largeImage = URLUtils.fromString( JSONUtils.getContent( imageObj, "large" ) );
        }

        if ( json.has( "details" ) ) {
            Object details = json.get( "details" );
            if ( details instanceof JSONObject ) {
                JSONObject detailsObj = ( JSONObject ) details;
                megapixels = detailsObj.optString( "megapixels", null );
                lcdScreenSize = detailsObj.optString( "lcd_screen_size", null );
                memoryType = detailsObj.optString( "memory_type", null );
            }
        }
    }

    /**
     * Get the model name
     *
     * @return The model name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the LCD screen size (in inch)
     *
     * @return The screen size
     */
    public String getLCDScreenSize()
    {
        return lcdScreenSize;
    }

    /**
     * Get the memory type
     *
     * @return The memoru type
     */
    public String getMemoryType()
    {
        return memoryType;
    }

    /**
     * Get the sensor resolution
     *
     * @return The resolution
     */
    public String getMegapixels()
    {
        return megapixels;
    }

    /**
     * Get the small image of the model
     *
     * @return The image
     */
    public URL getSmallImage()
    {
        return smallImage;
    }

    /**
     * Get the large image of the model
     *
     * @return The image
     */
    public URL getLargeImage()
    {
        return largeImage;
    }

    @Override
    public String getId()
    {
        return id;
    }

}
