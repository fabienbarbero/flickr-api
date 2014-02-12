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
import com.flickr.api.entities.Member;
import com.flickr.api.entities.MembersResponse;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.PhotosResponse;
import com.flickr.api.entities.VoidResponse;
import java.util.Locale;

/**
 *
 * @author Fabien Barbero
 */
public class GroupsService extends FlickrService {

    public GroupsService(OAuthHandler oauthHandler) {
        super(oauthHandler);
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
        CommandArguments args = new CommandArguments("flickr.groups.pools.getGroups");
        args.addParam("page", page);
        args.addParam("per_page", perPage);
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
        CommandArguments args = new CommandArguments("flickr.groups.getInfo");
        args.addParam("group_id", group.getId());
        args.addParam("lang", locale.getCountry().toLowerCase() + "-" + locale.getLanguage());
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
        CommandArguments args = new CommandArguments("flickr.groups.pools.getPhotos");
        args.addParam("group_id", group.getId());
        args.addParam("page", page);
        args.addParam("per_page", perPage);
        return doGet(args, PhotosResponse.class).getPaginated();
    }

    /**
     * Get a list of the members of a group. The call must be signed on behalf of a Flickr member, and the ability to
     * see the group membership will be determined by the Flickr member's group privileges.
     *
     * @param group The group
     * @param perPage Number of members to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The members
     * @throws FlickrServiceException Error getting the members
     */
    public Paginated<Member> getGroupMembers(Group group, int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.groups.members.getList");
        args.addParam("group_id", group.getId());
        args.addParam("page", page);
        args.addParam("per_page", perPage);
        return doGet(args, MembersResponse.class).getPaginated();
    }

    /**
     * Search for groups. 18+ groups will only be returned for authenticated calls where the authenticated user is over
     * 18.
     *
     * @param search The text to search for
     * @param perPage Number of members to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The groups found
     * @throws FlickrServiceException Error searching the groups
     */
    public Paginated<Group> searchGroup(String search, int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.groups.search");
        args.addParam("text", search);
        args.addParam("page", page);
        args.addParam("per_page", perPage);
        return doGet(args, GroupsResponse.class).getPaginated();
    }

    /**
     * Join a group
     *
     * @param group The group to join
     * @param acceptRules If the group has rules, they must be displayed to the user prior to joining. Passing a true
     * value for this argument specifies that the application has displayed the group rules to the user, and that the
     * user has agreed to them.
     * @throws FlickrServiceException Error joining the group
     */
    public void joinGroup(Group group, boolean acceptRules) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.groups.join");
        args.addParam("group_id", group.getId());
        args.addParam("accept_rules", acceptRules);
        doPost(args, VoidResponse.class);
    }

    /**
     * Leave a group. If the user is the only administrator left, and there are other members, the oldest member will be
     * promoted to administrator. If the user is the last person in the group, the group will be deleted.
     *
     * @param group The group to leave
     * @param deleteUserPhotos true to delete the user photos, false otherwise
     * @throws FlickrServiceException Error leaving the group
     */
    public void leaveGroup(Group group, boolean deleteUserPhotos) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.groups.leave");
        args.addParam("group_id", group.getId());
        args.addParam("delete_photos", deleteUserPhotos);
        doPost(args, VoidResponse.class);
    }

}
