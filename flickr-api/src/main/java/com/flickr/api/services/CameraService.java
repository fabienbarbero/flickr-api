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
package com.flickr.api.services;

import com.flickr.api.CommandArguments;
import com.flickr.api.FlickrService;
import com.flickr.api.FlickrServiceException;
import com.flickr.api.OAuthHandler;
import com.flickr.api.entities.CameraBrand;
import com.flickr.api.entities.CameraBrandModel;
import com.flickr.api.entities.CameraBrandModelsResponse;
import com.flickr.api.entities.CameraBrandsResponse;
import java.util.List;

/**
 *
 * @author Fabien Barbero
 */
public class CameraService extends FlickrService {

    public CameraService(OAuthHandler oauth) {
        super(oauth);
    }

    /**
     * Get the camera brands
     *
     * @return The brands
     * @throws FlickrServiceException Error getting the brands
     */
    public List<CameraBrand> getBrands() throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.cameras.getBrands");
        return doGet(args, CameraBrandsResponse.class).getList();
    }

    /**
     * Retrieve all the models for a given camera brand.
     *
     * @param brand The requested brand
     * @return The models
     * @throws FlickrServiceException Error getting the models
     */
    public List<CameraBrandModel> getBrandModels(CameraBrand brand) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.cameras.getBrandModels");
        args.addParam("brand", brand.getId());
        return doGet(args, CameraBrandModelsResponse.class).getList();
    }

}
