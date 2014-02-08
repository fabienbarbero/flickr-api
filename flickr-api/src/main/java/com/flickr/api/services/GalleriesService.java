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
import com.flickr.api.entities.BaseUser;
import com.flickr.api.entities.GalleriesResponse;
import com.flickr.api.entities.Gallery;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.PhotosResponse;
import org.apache.http.client.HttpClient;

/**
 *
 * @author Fabien Barbero
 */
public class GalleriesService extends FlickrService {

    public GalleriesService(OAuthHandler oauthHandler, HttpClient client) {
        super(oauthHandler, client);
    }

    /**
     * Return the list of galleries created by a user. Sorted from newest to oldest.
     *
     * @param user The user to get a galleries list for
     * @param perPage Number of galleries to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The galleries
     * @throws FlickrServiceException Error getting the galleries
     */
    public Paginated<Gallery> getGalleries(BaseUser user, int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.galleries.getList");
        args.put("per_page", perPage);
        args.put("page", page);
        args.put("user_id", user.getId());

        return doGet(args, GalleriesResponse.class).getPaginated();
    }

    /**
     * Return the list of photos for a gallery
     *
     * @param gallery The gallery of photos to return
     * @param perPage Number of galleries to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The photos
     * @throws FlickrServiceException Error getting the photos
     */
    public Paginated<Photo> getGalleryPhotos(Gallery gallery, int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.galleries.getPhotos");
        args.put("per_page", perPage);
        args.put("page", page);
        args.put("gallery_id", gallery.getId());

        return doGet(args, PhotosResponse.class).getPaginated();
    }

}
