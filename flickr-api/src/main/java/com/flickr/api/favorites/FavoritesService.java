/*
 * Copyright (C) 2013 Fabien Barbero
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
package com.flickr.api.favorites;

import com.flickr.api.FlickrServiceException;
import com.flickr.api.entities.BasicUser;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.Photo;

/**
 *
 * @author fabien
 */
public interface FavoritesService {

    /**
     * Returns a list of the user's favorite photos. Only photos which the
     * calling user has permission to see are returned.
     *
     * @param user The user to fetch the favorites list for.
     * @param perPage Number of photos to return per page.
     * @param page The page of results to return.
     * @return The favorite photos
     * @throws FlickrServiceException Error getting the photos
     */
    Paginated<Photo> getFavorites(BasicUser user, int perPage, int page) throws FlickrServiceException;

    /**
     * Returns a list of favorite public photos for the given user.
     *
     * @param user The user to fetch the favorites list for.
     * @param perPage Number of photos to return per page.
     * @param page The page of results to return.
     * @return The favorite photos
     * @throws FlickrServiceException Error getting the photos
     */
    Paginated<Photo> getPublicFavorites(BasicUser user, int perPage, int page) throws FlickrServiceException;
}
