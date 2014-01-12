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

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class TotalViews {
    private final int total;
    private final int photos;
    private final int photosets;
    private final int photoStream;
    private final int collections;
    private final int galleries;

    TotalViews(JSONObject json) throws JSONException {
        total = json.getJSONObject("total").getInt("views");
        photos = json.getJSONObject("photos").getInt("views");
        photosets = json.getJSONObject("sets").getInt("views");
        photoStream = json.getJSONObject("photostream").getInt("views");
        collections = json.getJSONObject("collections").getInt("views");
        galleries = json.getJSONObject("galleries").getInt("views");
    }

    public int getCollectionsViews() {
        return collections;
    }

    public int getGalleriesViews() {
        return galleries;
    }

    public int getPhotoStreamViews() {
        return photoStream;
    }

    public int getPhotosViews() {
        return photos;
    }

    public int getPhotosetsViews() {
        return photosets;
    }

    public int getTotalViews() {
        return total;
    }

}
