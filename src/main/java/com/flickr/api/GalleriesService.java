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

import com.flickr.api.entities.BaseUser;
import com.flickr.api.entities.GalleriesResponse;
import com.flickr.api.entities.Gallery;
import com.flickr.api.entities.GalleryResponse;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.PhotosResponse;
import com.flickr.api.entities.VoidResponse;

/**
 *
 * @author Fabien Barbero
 */
public class GalleriesService extends FlickrService {

    GalleriesService(OAuthHandler oauthHandler) {
        super(oauthHandler);
    }

    /**
     * Return the list of galleries created by a user. Sorted from newest to oldest.
     *
     * @param user The user to get a galleries list for
     * @param perPage Number of galleries to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The galleries
     * @throws FlickrException Error getting the galleries
     */
    public Paginated<Gallery> getGalleries(BaseUser user, int perPage, int page) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.galleries.getList");
        args.addParam("per_page", perPage);
        args.addParam("page", page);
        args.addParam("user_id", user.getId());

        return doGet(args, GalleriesResponse.class).getPaginated();
    }

    /**
     * Return the list of photos for a gallery
     *
     * @param gallery The gallery of photos to return
     * @param perPage Number of galleries to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The photos
     * @throws FlickrException Error getting the photos
     */
    public Paginated<Photo> getGalleryPhotos(Gallery gallery, int perPage, int page) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.galleries.getPhotos");
        args.addParam("per_page", perPage);
        args.addParam("page", page);
        args.addParam("gallery_id", gallery.getId());

        return doGet(args, PhotosResponse.class).getPaginated();
    }

    /**
     * Create a new gallery
     *
     * @param title The gallery title
     * @param description The gallery description
     * @param primaryPhoto The primary photo (optional)
     * @return The created gallery
     * @throws FlickrException Error creating the gallery
     */
    public Gallery createGallery(String title, String description, Photo primaryPhoto) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.galleries.create");
        args.addParam("title", title);
        args.addParam("description", description);
        if (primaryPhoto != null) {
            args.addParam("primary_photo_id", primaryPhoto.getId());
        }

        return doPost(args, GalleryResponse.class).getGallery();
    }

    /**
     * Add a photo to a gallery
     *
     * @param gallery The gallery
     * @param photo The photo to add
     * @param comment A comment (optional)
     * @throws FlickrException Error adding the photo
     */
    public void addPhotoToGallery(Gallery gallery, Photo photo, String comment) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.galleries.addPhoto");
        args.addParam("gallery_id", gallery.getId());
        args.addParam("photo_id", photo.getId());
        if (comment != null) {
            args.addParam("comment", comment);
        }

        doPost(args, VoidResponse.class);
    }

}
