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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents the exif informations of a photo
 * 
 * @author Fabien Barbero
 */
public class ExifInfos implements Serializable {

    private final String camera;
    private final List<ExifEntry> entries = new ArrayList<ExifEntry>();

    ExifInfos(JSONObject json) throws JSONException {
        camera = json.getString("camera");

        JSONArray array = json.getJSONArray("exif");
        for (int i = 0; i < array.length(); i++) {
            entries.add(new ExifEntry(array.getJSONObject(i)));
        }
    }

    /**
     * Get the camera name
     *
     * @return The camera name
     */
    public String getCamera() {
        return camera;
    }

    /**
     * Get the exif entries
     *
     * @return The entries
     */
    public List<ExifEntry> getEntries() {
        return entries;
    }

    /**
     * Get a exif entry
     *
     * @param tag The exif tag (see ExifEntry.TAG_*)
     * @return The entry or null if not present
     */
    public ExifEntry getEntry(String tag) {
        for (ExifEntry entry : entries) {
            if (entry.getTag().equals(tag)) {
                return entry;
            }
        }
        return null;
    }
}
