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
import com.flickr.api.entities.PaginatedPhotosResponse;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.PhotoPermissions;
import com.flickr.api.entities.PhotoInfos;
import com.flickr.api.entities.PhotoInfosResponse;
import com.flickr.api.entities.PhotoSize;
import com.flickr.api.entities.PhotoSizesResponse;
import org.apache.http.client.HttpClient;

/**
 *
 * @author Fabien Barbero
 */
public class PhotosService extends FlickrService {


    public PhotosService(OAuthHandler oauthHandler, HttpClient client) {
        super(oauthHandler, client);
    }

    public Paginated<Photo> getContactsPhotos() throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getContactsPhotos", true);
        return doGet(args, PaginatedPhotosResponse.class).getPhotos();
    }

    public Paginated<Photo> getContactsPhotos(int count, boolean justFriends, boolean singlePhoto, boolean includeSelf) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getContactsPhotos", true);
        args.put("count", count);
        args.put("just_friends", justFriends);
        args.put("single_photo", singlePhoto);
        args.put("include_self", includeSelf);
        return doGet(args, PaginatedPhotosResponse.class).getPhotos();
    }

    public Paginated<Photo> getContactsPublicPhotos(BaseUser user) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getContactsPublicPhotos", false);
        args.put("user_id", user.getId());
        return doGet(args, PaginatedPhotosResponse.class).getPhotos();
    }

    public Paginated<Photo> getContactsPublicPhotos(BaseUser user, int count, boolean justFriends, boolean singlePhoto, boolean includeSelf) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getContactsPublicPhotos", false);
        args.put("user_id", user.getId());
        args.put("count", count);
        args.put("just_friends", justFriends);
        args.put("single_photo", singlePhoto);
        args.put("include_self", includeSelf);
        return doGet(args, PaginatedPhotosResponse.class).getPhotos();
    }

    public PhotoInfos getInfos(Photo photo) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getInfo", false);
        args.put("photo_id", photo.getId());
        return doGet(args, PhotoInfosResponse.class).getInfos();
    }

    public PhotoPermissions getPermission(Photo photo) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getPerms", true);
        args.put("photo_id", photo.getId());
        return doGet(args, PhotoPermissions.class);
    }

    public Paginated<Photo> getRecent(int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getRecent", false);
        args.put("per_page", perPage);
        args.put("page", page);
        Paginated<Photo> photos = doGet(args, PaginatedPhotosResponse.class).getPhotos();
        return photos;
    }

    public List<PhotoSize> getSizes(Photo photo) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getSizes", false);
        args.put("photo_id", photo.getId());
        List<PhotoSize> sizes = doGet(args, PhotoSizesResponse.class).getSizes();
        return sizes;
    }
    
    public Paginated<Photo> getRecentlyUpdated(int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.recentlyUpdated", true);
        args.put("per_page", perPage);
        args.put("page", page);
        args.put("extras", "date_upload");
        args.put("min_date", "10000");
        Paginated<Photo> photos = doGet(args, PaginatedPhotosResponse.class).getPhotos();
        return photos;
    }
    
    public ExifInfos getExif(Photo photo) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getExif", false);
        args.put("photo_id", photo.getId());
        return doGet(args, ExifInfosResponse.class).getExifInfos();
    }
    
    public List<License> getLicenses() throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.licenses.getInfo", false);
        return doGet(args, LicensesResponse.class).getLicenses();
    }
    
    public List<Comment> getComments(Photo photo) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.comments.getList", false);
        args.put("photo_id", photo.getId());
        return doGet(args, CommentsResponse.class).getComments();
    }

}
