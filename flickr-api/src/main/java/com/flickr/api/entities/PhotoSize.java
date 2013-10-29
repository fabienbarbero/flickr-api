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

import java.io.IOException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import com.flickr.api.utils.JSONUtils;

/**
 *
 * @author Fabien Barbero
 */
public class PhotoSize {

    private String label;
    private int width;
    private int height;
    private URL source;

    PhotoSize(JSONObject json) throws JSONException {
        label = json.getString("label");
        width = json.getInt("width");
        height = json.getInt("height");
        source = JSONUtils.urlFromString(json.getString("source"));
    }

    /**
     * Get the height of the image.
     *
     * @return The height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the label of the image.
     *
     * @return The label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Get the width of the image.
     *
     * @return The width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the image with the correct size.
     *
     * @return The image URL
     * @throws IOException
     */
    public URL getSource() {
        return source;
    }
    
    public boolean isLandscape() {
        return width > height;
    }
    
    public boolean isPortrait() {
        return width < height;
    }
    
    public boolean isSquare() {
        return width == height;
    }

    @Override
    public String toString() {
        return label + "( " + width + "x" + height + ")";
    }
}
