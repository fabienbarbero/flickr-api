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
package com.flickr.api.people;

import com.flickr.api.FlickrServiceException;
import com.flickr.api.entities.BasicUser;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.User;
import com.flickr.api.entities.UserInfo;

/**
 *
 * @author Fabien Barbero
 */
public interface PeopleService {
    
    /**
     * Return a user's NSID, given their email address
     * @param email                     The email to search.
     * @return                          The user or null.
     * @throws FlickrServiceException   Error getting the user.
     */
    User findByEmail(String email) throws FlickrServiceException;
    
    /**
     * Return a user's NSID, given their username.
     * @param userName                  The user name to search.
     * @return                          The user or null.
     * @throws FlickrServiceException   Error getting the user.
     */
    User findByUserName(String userName) throws FlickrServiceException;
    
    /**
     * Get information about a user.
     * @param user                      The user.
     * @return                          The informations.
     * @throws FlickrServiceException 
     */
    UserInfo getUserInfo(BasicUser user) throws FlickrServiceException;
    
    /**
     * Return photos from the given user's photostream.
     * Only photos visible to the calling user will be returned.
     * @param user                      The user.
     * @param perPage                   The number of photos per page.
     * @param page                      The page index.
     * @return                          The photos.
     * @throws FlickrServiceException   Error getting the photos.
     */
    Paginated<Photo> getUserPhotos(BasicUser user, int perPage, int page) throws FlickrServiceException;
    
    /**
     * Get a list of public photos for the given user.
     * @param user                      The user.
     * @param perPage                   The number of photos per page.
     * @param page                      The page index.
     * @return                          The photos.
     * @throws FlickrServiceException   Error getting the photos.
     */
    Paginated<Photo> getUserPublicPhotos(BasicUser user, int perPage, int page) throws FlickrServiceException;
    
    /**
     * Returns a list of photos containing a particular Flickr member.
     * @param user                      The user you want to find photos of.
     * @param owner                     A Flickr member.
     * @param perPage                   The number of photos per page.
     * @param page                      The page index.
     * @return                          The photos.
     * @throws FlickrServiceException   Error getting the photos.
     */
    Paginated<Photo> getUserPhotosOf(BasicUser user, BasicUser owner, int perPage, int page) throws FlickrServiceException;
    
}
