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
package com.flickr.api;

import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.PhotosResponse;

/**
 *
 * @author Fabien Barbero
 */
public class InterestingnessService extends FlickrService {

    InterestingnessService(OAuthHandler oauthHandler) {
        super(oauthHandler);
    }

    /**
     * Returns the list of interesting photos for the most recent day or a user-specified date.
     *
     * @param perPage Number of photos to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The photos
     * @throws FlickrException Error getting the photos
     */
    public Paginated<Photo> getInterestingPhotos(int perPage, int page) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.interestingness.getList");
        args.addParam("page", page);
        args.addParam("per_page", perPage);
        return doGet(args, PhotosResponse.class).getPaginated();
    }

}
