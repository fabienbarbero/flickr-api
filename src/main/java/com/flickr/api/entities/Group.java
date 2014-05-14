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

import com.flickr.api.utils.URLUtils;
import java.net.URL;
import java.text.MessageFormat;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class Group implements IdObject {

    private final String id;
    private final String name;
    private final int photos;
    private final URL cover;

    Group(JSONObject json) throws JSONException {
        id = json.getString("nsid");
        name = json.getString("name");
        photos = json.optInt("photos", -1);

        cover = URLUtils.fromString(MessageFormat.format("https://farm{0}.staticflickr.com/{1}/coverphoto/{2}_s.jpg",
                json.getString("iconfarm"), json.getString("iconserver"), id));
    }

    /**
     * Get the group primary image
     *
     * @return The group image
     */
    public URL getCover() {
        return cover;
    }

    /**
     * Get the photos count in the group
     *
     * @return The photos count
     */
    public int getPhotos() {
        return photos;
    }

    /**
     * Get the group name
     *
     * @return the group name
     */
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }

}
