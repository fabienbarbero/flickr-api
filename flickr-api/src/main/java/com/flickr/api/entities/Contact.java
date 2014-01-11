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

import com.flickr.api.utils.URLUtils;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class Contact implements BaseUser {

    private final String id;
    private final String username;
    private final String location;
    private final int friend;
    private final int family;
    private final int ignored;
    private final URL avatar;

    Contact(JSONObject json) throws JSONException {
        id = json.getString("nsid");
        username = json.getString("username");
        location = json.optString("location", null);
        friend = json.getInt("friend");
        family = json.getInt("family");
        ignored = json.getInt("ignored");
        
        if (json.has("iconfarm") && json.has("iconserver")) {
            avatar = URLUtils.fromString("http://farm" + json.getString("iconfarm") + ".staticflickr.com/" + json.getString("iconserver") + "/buddyicons/" + id + ".jpg");
        } else {
            avatar = URLUtils.fromString("http://www.flickr.com/images/buddyicon.gif");
        }
    }

    /**
     * Get the contact avatar
     * @return The avatar
     */
    public URL getAvatar() {
        return avatar;
    }

    /**
     * Indicates if the contact is a member of the family.
     *
     * @return true if the contact is a member of the family.
     */
    public boolean isFamily() {
        return family == 1;
    }

    /**
     * Indicates if the contact is a friend.
     *
     * @return true if the contact is a friend.
     */
    public boolean isFriend() {
        return friend == 1;
    }

    /**
     * Indicates if the contact is ignored.
     *
     * @return true if the contact is ignored.
     */
    public boolean isIgnored() {
        return ignored == 1;
    }

    @Override
    public String getRealName() {
        return username;
    }

    @Override
    public String getUserName() {
        return username;
    }

    /**
     * Get the location of the contact
     * @return The location or null
     */
    public String getLocation() {
        return location;
    }
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return username;
    }
}
