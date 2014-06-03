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
package com.flickr.api.entities;

import com.flickr.api.utils.JSONUtils;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Contains the location informations of a photo
 *
 * @author Fabien Barbero
 */
public class PhotoLocation
        implements Serializable
{

    private String locality;
    private String county;
    private String neighbourhood;
    private String region;
    private String country;
    private final double latitude;
    private final double longitude;
    private final int accuracy;
    private final int context;

    PhotoLocation( JSONObject json )
            throws JSONException
    {
        if ( json.has( "locality" ) ) {
            locality = JSONUtils.getContent( json, "locality" );
        }
        if ( json.has( "county" ) ) {
            county = JSONUtils.getContent( json, "county" );
        }
        if ( json.has( "region" ) ) {
            region = JSONUtils.getContent( json, "region" );
        }
        if ( json.has( "country" ) ) {
            country = JSONUtils.getContent( json, "country" );
        }
        if ( json.has( "neighbourhood" ) ) {
            neighbourhood = JSONUtils.getContent( json, "neighbourhood" );
        }
        latitude = json.getDouble( "latitude" );
        longitude = json.getDouble( "longitude" );
        accuracy = json.getInt( "accuracy" );
        context = json.getInt( "context" );
    }

    /**
     * Get the country where the photo has been taken
     *
     * @return The country
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * Get the county where the photo has been taken
     *
     * @return The county
     */
    public String getCounty()
    {
        return county;
    }

    /**
     * Get the city where the photo has been taken
     *
     * @return The city
     */
    public String getLocality()
    {
        return locality;
    }

    /**
     * Get the region where the photo has been taken
     *
     * @return The region
     */
    public String getRegion()
    {
        return region;
    }

    /**
     * Get GPS latitude
     *
     * @return The latitude
     */
    public double getLatitude()
    {
        return latitude;
    }

    /**
     * Get the GPS longitude
     *
     * @return The longitude
     */
    public double getLongitude()
    {
        return longitude;
    }

    public String getNeighbourhood()
    {
        return neighbourhood;
    }

}
