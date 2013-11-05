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
package com.flickr.api.photos;

import com.flickr.api.FlickrServiceException;
import com.flickr.api.entities.BaseUser;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.PhotoAccess;
import com.flickr.api.entities.PhotoInfos;
import com.flickr.api.entities.PhotoSize;
import java.util.List;

/**
 *
 * @author Fabien Barbero
 */
public interface PhotosService {
    
    /**
     * Fetch a list of recent photos from the calling users' contacts.
     * @return                          The photos of the contacts.
     * @throws FlickrServiceException   Error getting the photos.
     */
    Paginated<Photo> getContactsPhotos() throws FlickrServiceException;
    
    /**
     * Fetch a list of recent photos from the calling users' contacts.
     * @param count                     Number of photos to return. This is only used if single_photo is not passed.
     * @param justFriends               Only show photos from friends and family (excluding regular contacts).
     * @param singlePhoto               Only fetch one photo (the latest) per contact, instead of all photos in chronological order.
     * @param includeSelf               Include photos from the calling user.
     * @return                          The photos of the contacts.
     * @throws FlickrServiceException   Error getting the photos.
     */
    Paginated<Photo> getContactsPhotos(int count, boolean justFriends, boolean singlePhoto, boolean includeSelf) throws FlickrServiceException;
    
    /**
     * Fetch a list of recent public photos from a users' contacts.
     * @param user                      The user.
     * @return                          The photos of the contacts
     * @throws FlickrServiceException   Error getting the photos.
     */
    Paginated<Photo> getContactsPublicPhotos(BaseUser user) throws FlickrServiceException;
    
    /**
     * Fetch a list of recent public photos from a users' contacts.
     * @param user                      The user.
     * @param count                     Number of photos to return. This is only used if single_photo is not passed.
     * @param justFriends               Only show photos from friends and family (excluding regular contacts).
     * @param singlePhoto               Only fetch one photo (the latest) per contact, instead of all photos in chronological order.
     * @param includeSelf               Include photos from the calling user.
     * @return                          The photos of the contacts.
     * @throws FlickrServiceException   Error getting the photos.
     */
    Paginated<Photo> getContactsPublicPhotos(BaseUser user, int count, boolean justFriends, boolean singlePhoto, boolean includeSelf) throws FlickrServiceException;
    
    /**
     * Get information about a photo.
     * @param photo                     The photo.   
     * @return                          The informations of the photo.
     * @throws FlickrServiceException   Error getting the informations.
     */
    PhotoInfos getInfos(Photo photo) throws FlickrServiceException;
    
    /**
     * Get permissions for a photo.
     * @param photo                     The photo.
     * @return                          The permission of the photo.
     * @throws FlickrServiceException   Error getting permission.
     */
    PhotoAccess getPermission(Photo photo) throws FlickrServiceException;
    
    /**
     * Returns a list of the latest public photos uploaded to flickr.
     * @param perPage                   The number of photos per page.
     * @param page                      The index of the page to return.
     * @return                          The list of photos.
     * @throws FlickrServiceException   Error getting recent photos.
     */
    Paginated<Photo> getRecent(int perPage, int page) throws FlickrServiceException;
    
    /**
     * Returns the available sizes for a photo.
     * @param photo                     The photo.
     * @return                          The sizes of the photo.
     * @throws FlickrServiceException   Error getting the sizes of the photo.
     */
    List<PhotoSize> getSizes(Photo photo) throws FlickrServiceException;
    
    /**
     * Return a list of your photos that have been recently created or which have been recently modified.
     * Recently modified may mean that the photo's metadata (title, description, tags) may have been
     * changed or a comment has been added (or just modified somehow :-)
     * @param perPage                   The number of photos per page.
     * @param page                      The index of the page to return.
     * @return                          The list of photos.
     * @throws FlickrServiceException   Error getting photos.
     */
    Paginated<Photo> getRecentlyUpdated(int perPage, int page) throws FlickrServiceException;
    
}
