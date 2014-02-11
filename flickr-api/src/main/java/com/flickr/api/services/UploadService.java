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
import com.flickr.api.entities.UploadedPhotoResponse;
import java.io.File;

/**
 *
 * @author Fabien Barbero
 */
public class UploadService extends FlickrService {

    public UploadService(OAuthHandler oauthHandler) {
        super(oauthHandler);
    }

    public String uploadPhoto(File file, String title, String description) throws FlickrServiceException {
        CommandArguments args = new CommandArguments();
        args.addParam("photo", file);
        args.addParam("content_type", 1);
        if (title != null) {
            args.addParam("title", title);
        }
        if (description != null) {
            args.addParam("description", description);
        }

        return doPost(args, UploadedPhotoResponse.class, "http://up.flickr.com/services/upload").getPhotoId();
    }

}
