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

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class PhotoInfos {

    private Owner owner;
    private String title;
    private String description;
    private PhotoVisibility visibility;
    private PhotoDates dates;
    private PhotoUsage usage;
    private int comments;
    private List<PhotoTag> tags;
    private PhotoLocation location;

    PhotoInfos(JSONObject json) throws JSONException {
        owner = new Owner(json.getJSONObject("owner"));
        title = json.getString("title");
        description = json.getString("description");
        visibility = new PhotoVisibility(json.getJSONObject("visibility"));
        dates = new PhotoDates(json.getJSONObject("dates"));
        usage = new PhotoUsage(json.getJSONObject("usage"));
        comments = json.getInt("comments");
        location = new PhotoLocation(json.getJSONObject("location"));

        tags = new ArrayList<PhotoTag>();
        JSONArray array = json.getJSONArray("tags");
        for (int i = 0; i < array.length(); i++) {
            tags.add(new PhotoTag(array.getJSONObject(i)));
        }
    }

    /**
     * Get the number of comments.
     *
     * @return The number of comments.
     */
    public int getComments() {
        return comments;
    }

    /**
     * Get the dates of the photo.
     *
     * @return The dates of the photo.
     */
    public PhotoDates getDates() {
        return dates;
    }

    /**
     * Get the description of the photo.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the location of the photo.
     *
     * @return The location.
     */
    public PhotoLocation getLocation() {
        return location;
    }

    /**
     * Get the owner of the photo.
     *
     * @return The owner.
     */
    public Owner getOwner() {
        return owner;
    }

    public List<PhotoTag> getTags() {
        return tags;
    }

    /**
     * Get the title of the photo.
     *
     * @return The title.
     */
    public String getTitle() {
        return title;
    }

    public PhotoUsage getUsage() {
        return usage;
    }

    public PhotoVisibility getVisibility() {
        return visibility;
    }

    @Override
    public String toString() {
        return title + " - " + description;
    }
}
