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
import java.io.Serializable;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class Avatar implements Serializable {

    /**
     * The default avatar URL (must be used if a user did not set any avatar).
     */
    public static final URL DEFAULT_AVATAR = URLUtils.fromString("https://www.flickr.com/images/buddyicon.gif");

    /**
     * Small square (48x48)
     */
    public static final String SMALL_SQUARE = "s";
    /**
     * Medium square (100x100)
     */
    public static final String MEDIUM_SQUARE = "m";
    /**
     * Original image
     */
    public static final String ORIGINAL = "r";
    //
    private final int iconServer;
    private final String iconFarm;
    private final String userId;

    Avatar(JSONObject json, String userId) {
        this.iconServer = json.optInt("iconserver", 0);
        this.iconFarm = json.optString("iconfarm", null);
        this.userId = userId;
    }

    /**
     * Get a download URL for an avatar size
     *
     * @param size The size to choose
     * @return The URL
     */
    public URL getUrl(String size) {
        if (iconServer > 0 && iconFarm != null) {
            if (size.equals(SMALL_SQUARE)) {
                return URLUtils.fromString("https://farm" + iconFarm + ".staticflickr.com/" + iconServer + "/buddyicons/" + userId + ".jpg");
            } else {
                return URLUtils.fromString("https://farm" + iconFarm + ".staticflickr.com/" + iconServer + "/buddyicons/" + userId + "_" + size + ".jpg");
            }
        } else {
            return DEFAULT_AVATAR;
        }
    }

}
