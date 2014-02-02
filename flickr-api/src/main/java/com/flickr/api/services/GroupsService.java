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
import com.flickr.api.entities.Group;
import com.flickr.api.entities.GroupInfos;
import com.flickr.api.entities.GroupInfosResponse;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.GroupsResponse;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.PhotosResponse;
import java.util.Locale;
import org.apache.http.client.HttpClient;

/**
 *
 * @author Fabien Barbero
 */
public class GroupsService extends FlickrService {

    public GroupsService(OAuthHandler oauthHandler, HttpClient client) {
        super(oauthHandler, client);
    }

    /**
     * Returns a list of groups to which you can add photos.
     *
     * @param perPage Number of groups to return per page. The maximum allowed value is 400.
     * @param page The page of results to return
     * @return The groups
     * @throws FlickrServiceException Error getting the groups
     */
    public Paginated<Group> getGroups(int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.groups.pools.getGroups", true);
        args.put("page", page);
        args.put("per_page", perPage);
        return doGet(args, GroupsResponse.class).getPaginated();
    }

    /**
     * Get information about a group.
     *
     * @param group The group
     * @return The group informations
     * @throws FlickrServiceException Error getting the informations
     */
    public GroupInfos getGroupInfos(Group group) throws FlickrServiceException {
        Locale locale = Locale.getDefault();
        CommandArguments args = new CommandArguments("flickr.groups.getInfo", true);
        args.put("group_id", group.getId());
        args.put("lang", locale.getCountry().toLowerCase() + "-" + locale.getLanguage());
        return doGet(args, GroupInfosResponse.class).getInfos();
    }

    /**
     * Returns a list of pool photos for a given group, based on the permissions of the group and the user logged in (if
     * any).
     *
     * @param group The group who's pool you which to get the photo list for
     * @param perPage Number of photos to return per page. The maximum allowed value is 500.
     * @param page The page of results to return.
     * @return The photos
     * @throws FlickrServiceException Error getting the photos
     */
    public Paginated<Photo> getGroupPhotos(Group group, int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.groups.pools.getPhotos", true);
        args.put("group_id", group.getId());
        args.put("page", page);
        args.put("per_page", perPage);
        return doGet(args, PhotosResponse.class).getPaginated();
    }

}
