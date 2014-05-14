/*
 * Copyright (C) 2012 Fabien Barbero
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
import java.net.URL;
import java.text.MessageFormat;
import org.json.JSONException;
import org.json.JSONObject;
import com.flickr.api.utils.URLUtils;

/**
 * This class represents an URL of an image as described in the Flickr API. see
 * http://www.flickr.com/services/api/misc.urls.html
 */
public class Image implements Serializable {

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

    Image(String prefix) {
        this.prefix = prefix;
    }

    Image(String farm, String server, String primary, String secret) {
        prefix = MessageFormat.format("https://farm{0}.staticflickr.com/{1}/{2}_{3}_", farm, server, primary, secret);
    }

    Image(JSONObject json) throws JSONException {
        String id;
        if (json.has("id")) {
            id = json.getString("id");
        } else {
            id = json.getString("primary");
        }

        prefix = MessageFormat.format("https://farm{0}.static.flickr.com/{1}/{2}_{3}_",
                json.getString("farm"), json.getString("server"),
                id,
                json.getString("secret"));
    }

    /**
     * Get the image URL with the given size
     *
     * @param size The size
     * @return The resulting URL
     */
    public URL getURL(String size) {
        return URLUtils.fromString(prefix + size + ".jpg");
    }

}
