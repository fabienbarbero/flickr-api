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
import com.flickr.api.utils.URLUtils;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class CameraBrandModel implements IdObject {

    private final String id;
    private final String name;
    private URL smallImage;
    private URL largeImage;
    private String megapixels;
    private String lcdScreenSize;
    private String memoryType;

    CameraBrandModel(JSONObject json) throws JSONException {
        id = json.getString("id");
        name = JSONUtils.getContent(json, "name");

        if (json.has("images")) {
            JSONObject imageObj = json.getJSONObject("images");
            smallImage = URLUtils.fromString(JSONUtils.getContent(imageObj, "small"));
            largeImage = URLUtils.fromString(JSONUtils.getContent(imageObj, "large"));
        }

        if (json.has("details")) {
            Object details = json.get("details");
            if (details instanceof JSONObject) {
                JSONObject detailsObj = (JSONObject) details;
                megapixels = detailsObj.optString("megapixels", null);
                lcdScreenSize = detailsObj.optString("lcd_screen_size", null);
                memoryType = detailsObj.optString("memory_type", null);
            }
        }
    }

    /**
     * Get the model name
     *
     * @return The model name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the LCD screen size (in inch)
     *
     * @return The screen size
     */
    public String getLCDScreenSize() {
        return lcdScreenSize;
    }

    /**
     * Get the memory type
     *
     * @return The memoru type
     */
    public String getMemoryType() {
        return memoryType;
    }

    /**
     * Get the sensor resolution
     *
     * @return The resolution
     */
    public String getMegapixels() {
        return megapixels;
    }

    /**
     * Get the small image of the model
     *
     * @return The image
     */
    public URL getSmallImage() {
        return smallImage;
    }

    /**
     * Get the large image of the model
     *
     * @return The image
     */
    public URL getLargeImage() {
        return largeImage;
    }

    @Override
    public String getId() {
        return id;
    }

}
