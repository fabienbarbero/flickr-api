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
 * Represents a tag on a photo
 *
 * @author Fabien Barbero
 */
public class PhotoTag implements IdObject {

    private final String id;
    private final String author;
    private final String raw;
    // FIXME
    private final String tag;

    PhotoTag(JSONObject json) throws JSONException {
        id = json.getString("id");
        author = json.getString("author");
        raw = json.getString("raw");
        tag = json.getString("_content");
    }

    /**
     * Get the author (user) identifier
     *
     * @return The author
     */
    public String getAuthorId() {
        return author;
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * Get the "raw" value of the tag
     *
     * @return The raw value
     */
    public String getRaw() {
        return raw;
    }

    /**
     * Get the readable value of the tag
     *
     * @return The tag value
     */
    public String getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return raw;
    }
}
