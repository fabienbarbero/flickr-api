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
public class PhotosetInfos implements IdObject {

    private final String id;
    private final boolean canComment;
    private final int commentsCount;
    private final int viewsCount;
    private final Date createDate;
    private final Date updateDate;
    private final String description;
    private final String owner;

    PhotosetInfos(JSONObject json) throws JSONException {
        id = json.getString("id");
        canComment = json.getInt("can_comment") == 1;
        commentsCount = json.getInt("count_comments");
        viewsCount = json.getInt("count_views");
        createDate = JSONUtils.dateFromString(json.getString("date_create"));
        updateDate = JSONUtils.dateFromString(json.getString("date_update"));
        description = JSONUtils.getContent(json, "description");
        owner = json.getString("owner");
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public boolean canComment() {
        return canComment;
    }

    public String getOwnerId() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    @Override
    public String getId() {
        return id;
    }
}
