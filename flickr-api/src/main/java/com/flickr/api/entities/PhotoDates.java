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

import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import com.flickr.api.utils.JSONUtils;

/**
 *
 * @author Fabien Barbero
 */
public class PhotoDates {

    private Date posted;
    private String taken;
    private int takengranularity;
    private Date lastupdate;

    PhotoDates(JSONObject json) throws JSONException {
        posted = JSONUtils.dateFromString(json.getString("posted"));
        taken = json.getString("taken");
        takengranularity = json.getInt("takengranularity");
        lastupdate = JSONUtils.dateFromString(json.getString("lastupdate"));
    }

    /**
     * Get the date of the last update of the photo.
     *
     * @return The date of the last update.
     */
    public Date getLastUpdateDate() {
        return lastupdate;
    }

    public Date getPostedDate() {
        return posted;
    }

    public String getTakenDate() {
        return taken;
    }
}
