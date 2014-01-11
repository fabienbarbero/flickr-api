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

import java.io.Serializable;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import com.flickr.api.utils.JSONUtils;

/**
 *
 * @author Fabien Barbero
 */
public class UserPhotosInfo implements Serializable {

    private static final long serialVersionUID = 5602104420678151276L;
    private Date firstDate;
    private Date firstDateTaken;
    private int count;

    UserPhotosInfo(JSONObject json) throws JSONException {
        firstDate = JSONUtils.dateFromString(JSONUtils.getContent(json, "firstdate"));
        firstDateTaken = JSONUtils.dateFromString(JSONUtils.getContent(json, "firstdatetaken"));
        count = JSONUtils.getIntegerContent(json, "count");
    }

    /**
     * Get the number of photos of the user.
     *
     * @return The number of photos.
     */
    public int getCount() {
        return count;
    }

    /**
     * Get the date of the first photo publication.
     *
     * @return The first date of publication.
     */
    public Date getFirstDate() {
        return firstDate;
    }

    public Date getFirstDateTaken() {
        return firstDateTaken;
    }
}
