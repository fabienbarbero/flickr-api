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

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class Photo implements BasePhoto {

    private static final long serialVersionUID = 5438438431425L;
    //
    private final String id;
    private final String title;
    private final Image url;
    private final boolean isFamily;
    private final boolean isFriend;
    private final boolean isPublic;
    private final boolean isPrimary;
    private final String owner;

    public Photo(JSONObject json) throws JSONException {
        id = json.getString("id");
        title = json.getString("title");
        url = new Image(json);
        isFamily = json.optInt("isfamily", 0) == 1;
        isFriend = json.optInt("isfriend", 0) == 1;
        isPublic = json.optInt("ispublic", 0) == 1;
        isPrimary = json.optInt("isprimary", 0) == 1;
        owner = json.optString("owner");
    }

    /**
     * Get the identifier of the photo.
     *
     * @return The identifier.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Get the title of the photo.
     *
     * @return The title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the photo image URL
     *
     * @return The image URL
     */
    public Image getImage() {
        return url;
    }

    /**
     * Indicates if this photo has been taken by a member of the user family
     *
     * @return true if this photo has been taken by a member of the user family, false otherwise
     */
    public boolean isFamily() {
        return isFamily;
    }

    /**
     * Indicates if this photo has been taken by a friend of the user
     *
     * @return true if this photo has been taken by a friend of the user, false otherwise
     */
    public boolean isFriend() {
        return isFriend;
    }

    /**
     * Indicates if this photo is public
     *
     * @return true if this photo is public, false otherwise
     */
    public boolean isPublic() {
        return isPublic;
    }

    /**
     * Get the owner identifier
     *
     * @return The owner
     */
    public String getOwner() {
        return owner;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Photo other = (Photo) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return title;
    }
}
