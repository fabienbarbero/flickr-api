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
package com.flickr.api.services;

import com.flickr.api.FlickrService;
import java.util.List;
import com.flickr.api.CommandArguments;
import com.flickr.api.FlickrServiceException;
import com.flickr.api.OAuthHandler;
import com.flickr.api.entities.BaseUser;
import com.flickr.api.entities.Comment;
import com.flickr.api.entities.CommentsResponse;
import com.flickr.api.entities.ExifInfos;
import com.flickr.api.entities.ExifInfosResponse;
import com.flickr.api.entities.License;
import com.flickr.api.entities.LicensesResponse;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.PhotosResponse;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.PhotoPermissions;
import com.flickr.api.entities.PhotoInfos;
import com.flickr.api.entities.PhotoInfosResponse;
import com.flickr.api.entities.PhotoSize;
import com.flickr.api.entities.PhotoSizesResponse;
import org.apache.http.client.HttpClient;

/**
 * Service used to access the photos.
 *
 * @author Fabien Barbero
 */
public class PhotosService extends FlickrService {

    public PhotosService(OAuthHandler oauthHandler, HttpClient client) {
        super(oauthHandler, client);
    }

    /**
     * Fetch a list of recent photos from the calling users' contacts.
     *
     * @return The photos
     * @throws FlickrServiceException Error getting the photos
     */
    public Paginated<Photo> getContactsPhotos() throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getContactsPhotos");
        return doGet(args, PhotosResponse.class).getPaginated();
    }

    /**
     * Fetch a list of recent photos from the calling users' contacts.
     *
     * @param count Number of photos to return
     * @param justFriends To only show photos from friends and family (excluding regular contacts).
     * @param singlePhoto Only fetch one photo (the latest) per contact, instead of all photos in chronological order.
     * @param includeSelf To include photos from the user specified by user_id.
     * @return The photos
     * @throws FlickrServiceException Error getting the photos
     */
    public Paginated<Photo> getContactsPhotos(int count, boolean justFriends, boolean singlePhoto, boolean includeSelf) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getContactsPhotos");
        args.put("count", count);
        args.put("just_friends", justFriends);
        args.put("single_photo", singlePhoto);
        args.put("include_self", includeSelf);
        return doGet(args, PhotosResponse.class).getPaginated();
    }

    /**
     * Fetch a list of recent public photos from a users' contacts.
     *
     * @param user The user to fetch photos for
     * @return The photos
     * @throws FlickrServiceException Error getting the photos
     */
    public Paginated<Photo> getContactsPublicPhotos(BaseUser user) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getContactsPublicPhotos");
        args.put("user_id", user.getId());
        return doGet(args, PhotosResponse.class).getPaginated();
    }

    /**
     * Fetch a list of recent public photos from a users' contacts.
     *
     * @param user The user to fetch photos for
     * @param count Number of photos to return
     * @param justFriends To only show photos from friends and family (excluding regular contacts).
     * @param singlePhoto Only fetch one photo (the latest) per contact, instead of all photos in chronological order.
     * @param includeSelf To include photos from the user specified by user_id.
     * @return The photos
     * @throws FlickrServiceException Error getting the photos
     */
    public Paginated<Photo> getContactsPublicPhotos(BaseUser user, int count, boolean justFriends, boolean singlePhoto, boolean includeSelf) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getContactsPublicPhotos");
        args.put("user_id", user.getId());
        args.put("count", count);
        args.put("just_friends", justFriends);
        args.put("single_photo", singlePhoto);
        args.put("include_self", includeSelf);
        return doGet(args, PhotosResponse.class).getPaginated();
    }

    /**
     * Get information about a photo. The calling user must have permission to view the photo.
     *
     * @param photo The photo
     * @return The photo informations
     * @throws FlickrServiceException Error getting the informations
     */
    public PhotoInfos getInfos(Photo photo) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getInfo");
        args.put("photo_id", photo.getId());
        return doGet(args, PhotoInfosResponse.class).getInfos();
    }

    /**
     * Get permissions for a photo.
     *
     * @param photo The photo
     * @return The photo permissions
     * @throws FlickrServiceException Error getting the permissions
     */
    public PhotoPermissions getPermissions(Photo photo) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getPerms");
        args.put("photo_id", photo.getId());
        return doGet(args, PhotoPermissions.class);
    }

    /**
     * Returns a list of the latest public photos uploaded to flickr.
     *
     * @param perPage Number of photos to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The recent photos
     * @throws FlickrServiceException Error getting the photos
     */
    public Paginated<Photo> getRecent(int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getRecent");
        args.put("per_page", perPage);
        args.put("page", page);
        Paginated<Photo> photos = doGet(args, PhotosResponse.class).getPaginated();
        return photos;
    }

    /**
     * Returns the available sizes for a photo. The calling user must have permission to view the photo.
     *
     * @param photo The photo
     * @return The photo sizes
     * @throws FlickrServiceException Error getting the sizes
     */
    public List<PhotoSize> getSizes(Photo photo) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getSizes");
        args.put("photo_id", photo.getId());
        List<PhotoSize> sizes = doGet(args, PhotoSizesResponse.class).getList();
        return sizes;
    }

    /**
     * Return a list of your photos that have been recently created or which have been recently modified. Recently
     * modified may mean that the photo's metadata (title, description, tags) may have been changed or a comment has
     * been added (or just modified somehow :-)
     *
     * @param perPage Number of photos to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The photos
     * @throws FlickrServiceException Error getting the photos
     */
    public Paginated<Photo> getRecentlyUpdated(int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.recentlyUpdated");
        args.put("per_page", perPage);
        args.put("page", page);
        args.put("extras", "date_upload");
        args.put("min_date", "10000");
        Paginated<Photo> photos = doGet(args, PhotosResponse.class).getPaginated();
        return photos;
    }

    /**
     * Retrieves a list of EXIF/TIFF/GPS tags for a given photo. The calling user must have permission to view the
     * photo.
     *
     * @param photo The photo
     * @return The exif informations
     * @throws FlickrServiceException Error getting the exif informations
     */
    public ExifInfos getExif(Photo photo) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getExif");
        args.put("photo_id", photo.getId());
        return doGet(args, ExifInfosResponse.class).getExifInfos();
    }

    /**
     * Fetches a list of available photo licenses for Flickr.
     *
     * @return The licenses
     * @throws FlickrServiceException Error getting the licenses
     */
    public List<License> getLicenses() throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.licenses.getInfo");
        return doGet(args, LicensesResponse.class).getList();
    }

    /**
     * Returns the comments for a photo
     *
     * @param photo The photo
     * @return The comments
     * @throws FlickrServiceException Error getting the comments
     */
    public List<Comment> getComments(Photo photo) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.comments.getList");
        args.put("photo_id", photo.getId());
        return doGet(args, CommentsResponse.class).getList();
    }

}
