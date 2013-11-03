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

import com.flickr.api.FlickrService;
import java.util.List;
import com.flickr.api.CommandArguments;
import com.flickr.api.FlickrServiceException;
import com.flickr.api.OAuthHandler;
import com.flickr.api.entities.BasicUser;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.PaginatedPhotosResponse;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.PhotoAccess;
import com.flickr.api.entities.PhotoInfos;
import com.flickr.api.entities.PhotoInfosResponse;
import com.flickr.api.entities.PhotoSize;
import com.flickr.api.entities.PhotoSizesResponse;

/**
 *
 * @author Fabien Barbero
 */
public class PhotosServiceImpl extends FlickrService implements PhotosService {


    public PhotosServiceImpl(OAuthHandler oauthHandler) {
        super(oauthHandler);
    }

    @Override
    public Paginated<Photo> getContactsPhotos() throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getContactsPhotos");
        return doGet(args, PaginatedPhotosResponse.class).getPhotos();
    }

    @Override
    public Paginated<Photo> getContactsPhotos(int count, boolean justFriends, boolean singlePhoto, boolean includeSelf) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getContactsPhotos");
        args.put("count", count);
        args.put("just_friends", justFriends);
        args.put("single_photo", singlePhoto);
        args.put("include_self", includeSelf);
        return doGet(args, PaginatedPhotosResponse.class).getPhotos();
    }

    @Override
    public Paginated<Photo> getContactsPublicPhotos(BasicUser user) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getContactsPublicPhotos");
        args.put("user_id", user.getId());
        return doGet(args, PaginatedPhotosResponse.class).getPhotos();
    }

    @Override
    public Paginated<Photo> getContactsPublicPhotos(BasicUser user, int count, boolean justFriends, boolean singlePhoto, boolean includeSelf) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getContactsPublicPhotos");
        args.put("user_id", user.getId());
        args.put("count", count);
        args.put("just_friends", justFriends);
        args.put("single_photo", singlePhoto);
        args.put("include_self", includeSelf);
        return doGet(args, PaginatedPhotosResponse.class).getPhotos();
    }

    @Override
    public PhotoInfos getInfos(Photo photo) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getInfo");
        args.put("photo_id", photo.getId());
        return doGet(args, PhotoInfosResponse.class).getInfos();
    }

    @Override
    public PhotoAccess getPermission(Photo photo) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getPerms");
        return doGet(args, PhotoAccess.class);
    }

    @Override
    public Paginated<Photo> getRecent(int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getRecent");
        args.put("per_page", perPage);
        args.put("page", page);
        Paginated<Photo> photos = doGet(args, PaginatedPhotosResponse.class).getPhotos();
        return photos;
    }

    @Override
    public List<PhotoSize> getSizes(Photo photo) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.getSizes");
        args.put("photo_id", photo.getId());
        List<PhotoSize> sizes = doGet(args, PhotoSizesResponse.class).getSizes();
        return sizes;
    }
    
    @Override
    public Paginated<Photo> getRecentlyUpdated(int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photos.recentlyUpdated");
        args.put("per_page", perPage);
        args.put("page", page);
        args.put("extras", "date_upload");
        args.put("min_date", "10000");
        Paginated<Photo> photos = doGet(args, PaginatedPhotosResponse.class).getPhotos();
        return photos;
    }

}
