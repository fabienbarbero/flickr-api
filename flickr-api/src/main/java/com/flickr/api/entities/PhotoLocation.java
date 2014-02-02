/*
 * Copyright (C) 2011 by Fabien Barbero
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
public class PhotoLocation implements Serializable {

    private String locality;
    private String county;
    private String neighbourhood;
    private String region;
    private String country;
    private final double latitude;
    private final double longitude;
    private final int accuracy;
    private final int context;

    PhotoLocation(JSONObject json) throws JSONException {
        if (json.has("locality")) {
            locality = JSONUtils.getContent(json, "locality");
        }
        if (json.has("county")) {
            county = JSONUtils.getContent(json, "county");
        }
        if (json.has("region")) {
            region = JSONUtils.getContent(json, "region");
        }
        if (json.has("country")) {
            country = JSONUtils.getContent(json, "country");
        }
        if (json.has("neighbourhood")) {
            neighbourhood = JSONUtils.getContent(json, "neighbourhood");
        }
        latitude = json.getDouble("latitude");
        longitude = json.getDouble("longitude");
        accuracy = json.getInt("accuracy");
        context = json.getInt("context");
    }

    /**
     * Get the country where the photo has been taken
     *
     * @return The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Get the county where the photo has been taken
     *
     * @return The county
     */
    public String getCounty() {
        return county;
    }

    /**
     * Get the city where the photo has been taken
     *
     * @return The city
     */
    public String getLocality() {
        return locality;
    }

    /**
     * Get the region where the photo has been taken
     *
     * @return The region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Get GPS latitude
     *
     * @return The latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Get the GPS longitude
     *
     * @return The longitude
     */
    public double getLongitude() {
        return longitude;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }
}
