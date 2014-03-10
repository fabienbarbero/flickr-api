/*
 * Copyright (C) 2014 Fabien Barbero
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
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class Gallery implements IdObject {

    private final String id;
    private final String url;
    private final Date creationDate;
    private final Date updateDate;
    private final Image primaryImage;
    private final int photos;
    private final int videos;
    private final String title;
    private String description;

    Gallery(JSONObject json) throws JSONException {
        id = json.getString("id");
        url = json.getString("url");
        creationDate = JSONUtils.dateFromString(json.getString("date_create"));
        updateDate = JSONUtils.dateFromString(json.getString("date_update"));
        photos = json.getInt("count_photos");
        videos = json.getInt("count_videos");
        title = JSONUtils.getContent(json, "title");
        if (json.has("description")) {
            description = JSONUtils.getContent(json, "description");
        }
        primaryImage = new Image(
                json.getString("primary_photo_farm"),
                json.getString("primary_photo_server"),
                json.getString("primary_photo_id"),
                json.getString("primary_photo_secret"));
    }

    /**
     * Get the gallery URL
     *
     * @return The URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Get the last gallery update date
     *
     * @return The last update date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Get the primary image
     *
     * @return The image
     */
    public Image getPrimaryImage() {
        return primaryImage;
    }

    /**
     * Get the gallery creation date
     *
     * @return The creation date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Get the videos count
     *
     * @return The videos count
     */
    public int getVideos() {
        return videos;
    }

    /**
     * Get the gallery title
     *
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the photos count
     *
     * @return The photos count
     */
    public int getPhotos() {
        return photos;
    }

    /**
     * Get the gallery description
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String getId() {
        return id;
    }

}
