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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class GroupInfos implements IdObject {

    private final String name;
    private final String description;
    private final String rules;
    private final int members;
    private final String blast;
    private final String id;

    GroupInfos(JSONObject json) throws JSONException {
        id = json.getString("id");
        name = JSONUtils.getContent(json, "name");
        description = JSONUtils.getContent(json, "description");
        rules = JSONUtils.getContent(json, "rules");
        members = JSONUtils.getIntegerContent(json, "members");
        blast = JSONUtils.getContent(json, "blast");
        // FIXME: add throttle
    }

    /**
     * Get the group rules
     *
     * @return The rules
     */
    public String getRules() {
        return rules;
    }

    /**
     * Get the name of the group
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the members count
     *
     * @return The members count
     */
    public int getMembers() {
        return members;
    }

    /**
     * Get the group description
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    public String getBlast() {
        return blast;
    }

    @Override
    public String getId() {
        return id;
    }

}
