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

import com.flickr.api.FlickrService;
import com.flickr.api.CommandArguments;
import com.flickr.api.FlickrErrorCode;
import com.flickr.api.FlickrServiceException;
import com.flickr.api.OAuthHandler;
import com.flickr.api.entities.BaseUser;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.PaginatedPhotosResponse;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.User;
import com.flickr.api.entities.UserInfo;
import com.flickr.api.entities.UserInfoResponse;
import com.flickr.api.entities.UserResponse;
import org.apache.http.client.HttpClient;

/**
 *
 * @author Fabien Barbero
 */
public class PeopleServiceImpl extends FlickrService implements PeopleService {

    public PeopleServiceImpl(OAuthHandler oauthHandler, HttpClient client) {
        super(oauthHandler, client);
    }

    @Override
    public User findByEmail(String email) throws FlickrServiceException {
        try {
            CommandArguments args = new CommandArguments("flickr.people.findByEmail", false);
            args.put("find_email", email);
            return doGet(args, UserResponse.class).getUser();
        } catch (FlickrServiceException ex) {
            if (ex.getErrorCode() == FlickrErrorCode.not_found) {
                return null;
            } else {
                throw ex;
            }
        }
    }

    @Override
    public User findByUserName(String userName) throws FlickrServiceException {
        try {
            CommandArguments args = new CommandArguments("flickr.people.findByUsername", false);
            args.put("username", userName);
            return doGet(args, UserResponse.class).getUser();
        } catch (FlickrServiceException ex) {
            if (ex.getErrorCode() == FlickrErrorCode.not_found) {
                return null;
            } else {
                throw ex;
            }
        }
    }

    @Override
    public UserInfo getUserInfo(BaseUser user) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.people.getInfo", false);
        args.put("user_id", user.getId());
        return doGet(args, UserInfoResponse.class).getUserInfo();
    }

    @Override
    public Paginated<Photo> getUserPhotos(BaseUser user, int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.people.getPhotos", false);
        args.put("user_id", user.getId());
        args.put("per_page", perPage);
        args.put("page", page);
        return doGet(args, PaginatedPhotosResponse.class).getPhotos();
    }

    @Override
    public Paginated<Photo> getUserPublicPhotos(BaseUser user, int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.people.getPublicPhotos", false);
        args.put("user_id", user.getId());
        args.put("per_page", perPage);
        args.put("page", page);
        return doGet(args, PaginatedPhotosResponse.class).getPhotos();
    }

    @Override
    public Paginated<Photo> getUserPhotosOf(BaseUser user, BaseUser owner, int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.people.getPhotosOf", false);
        args.put("user_id", user.getId());
        args.put("owner_id", owner.getId());
        args.put("per_page", perPage);
        args.put("page", page);
        return doGet(args, PaginatedPhotosResponse.class).getPhotos();
    }

}
