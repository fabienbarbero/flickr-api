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
public class PhotoVisibility implements Serializable {

    private final int isPublic;
    private final int isFriend;
    private final int isFamily;

    PhotoVisibility(JSONObject json) throws JSONException {
        isPublic = json.getInt("ispublic");
        isFriend = json.getInt("isfriend");
        isFamily = json.getInt("isfamily");
    }

    /**
     * Indicates if the photo has been taken by a member of the family of th user.
     *
     * @return true if the photo has been taken by a member of the family of th user.
     */
    public boolean isFamily() {
        return isFamily == 1;
    }

    /**
     * Indicates if the photo has been taken by a friend.
     *
     * @return true if the photo has been taken by a friend.
     */
    public boolean isFriend() {
        return isFriend == 1;
    }

    /**
     * Indicates if the photo is public.
     *
     * @return true if the photo is public.
     */
    public boolean isPublic() {
        return isPublic == 1;
    }
}
