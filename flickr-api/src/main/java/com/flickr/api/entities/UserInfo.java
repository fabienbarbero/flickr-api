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
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import com.flickr.api.utils.JSONUtils;
import com.flickr.api.utils.URLUtils;

/**
 *
 * @author Fabien Barbero
 */
public class UserInfo implements BaseUser, Serializable {

    private static final long serialVersionUID = -5126309551528400272L;
    private String id;
    private int isPro;
    private String description;
    private String userName;
    private String realName;
    private URL photosUrl;
    private URL profileUrl;
    private UserPhotosInfo photosInfo;
    private URL avatar;

    UserInfo(JSONObject json) throws JSONException {
        id = json.getString("nsid");
        isPro = json.getInt("ispro");
        description = JSONUtils.getContent(json, "description");
        userName = JSONUtils.getContent(json, "username");
        realName = JSONUtils.getContent(json, "realname");
        photosUrl = JSONUtils.urlFromString(JSONUtils.getContent(json, "photosurl"));
        profileUrl = JSONUtils.urlFromString(JSONUtils.getContent(json, "profileurl"));
        photosInfo = new UserPhotosInfo(json.getJSONObject("photos"));

        if (json.has("iconfarm") && json.has("iconserver")) {
            avatar = URLUtils.fromString("http://farm" + json.getString("iconfarm") + ".staticflickr.com/" + json.getString("iconserver") + "/buddyicons/" + id + ".jpg");
        } else {
            avatar = URLUtils.fromString("http://www.flickr.com/images/buddyicon.gif");
        }
    }

    @Override
    public String getId() {
        return id;
    }

    public URL getAvatar() {
        return avatar;
    }

    /**
     * Indicates if the user owns a "pro" account.
     *
     * @return true if the user owns a "pro" account.
     */
    public boolean isPro() {
        return isPro == 1;
    }

    /**
     * Get the user description
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the informations about the user's photos.
     *
     * @return The photos informations.
     */
    public UserPhotosInfo getPhotosInfo() {
        return photosInfo;
    }

    /**
     * Get the URL of the user's photos.
     *
     * @return The URL.
     */
    public URL getPhotosUrl() {
        return photosUrl;
    }

    /**
     * Get the URL of the user's profile.
     *
     * @return The URL.
     */
    public URL getProfileUrl() {
        return profileUrl;
    }

    @Override
    public String getRealName() {
        return realName;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return userName;
    }
}
