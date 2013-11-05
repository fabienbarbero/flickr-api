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
package com.flickr.api.photosets;

import com.flickr.api.FlickrServiceException;
import com.flickr.api.entities.BaseUser;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.Photoset;

/**
 *
 * @author Fabien Barbero
 */
public interface PhotosetsService {
    
    /**
     * Gets information about a photoset.
     * @param id                        The identifier of the set.
     * @return                          The set informations.
     * @throws FlickrServiceException   Error getting the informations.
     */
    Photoset getPhotosetById(String id) throws FlickrServiceException;
    
    /**
     * Returns the photosets belonging to the specified user.
     * @param user                      The user.
     * @return                          The list of photosets.
     * @throws FlickrServiceException   Error getting the sets.
     */
    Paginated<Photoset> getPhotosets(BaseUser user, int perPage, int page) throws FlickrServiceException;
    
    /**
     * Get the list of photos in a set.
     * @param photoset                  The photo set.
     * @param perPage                   The number of photos per page.
     * @param page                      The page index.
     * @return                          The photos.
     * @throws FlickrServiceException   Error getting the photos.
     */
    Paginated<Photo> getPhotos(Photoset photoset, int perPage, int page) throws FlickrServiceException;
    
}
