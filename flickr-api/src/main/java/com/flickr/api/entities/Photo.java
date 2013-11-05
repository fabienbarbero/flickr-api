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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class Photo implements IdObject, Serializable {
    private static final long serialVersionUID = 5438438431425L;

    private String id;
    private String title;
    private Image url;
    
    public Photo(JSONObject json) throws JSONException {
        id = json.getString("id");
        title = json.getString("title");
        url = new Image(json);
    }

    public Photo() {
    }

    public static Photo create(String id, String server, String farm, String secret) {
        Photo photo = new Photo();
        photo.id = id;
        photo.url = new Image(farm, server, id, secret);
        return photo;
    }

    public static Photo create(String id) {
        Photo photo = new Photo();
        photo.id = id;
        return photo;
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
