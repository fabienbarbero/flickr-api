/*
 * (C) Copyright 2014 Fabien Barbero.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */
package com.github.fabienbarbero.flickr.api;

import com.github.fabienbarbero.flickr.api.entities.Group;
import com.github.fabienbarbero.flickr.api.entities.GroupInfo;
import com.github.fabienbarbero.flickr.api.entities.GroupInfoResponse;
import com.github.fabienbarbero.flickr.api.entities.Paginated;
import com.github.fabienbarbero.flickr.api.entities.GroupsResponse;
import com.github.fabienbarbero.flickr.api.entities.Member;
import com.github.fabienbarbero.flickr.api.entities.MembersResponse;
import com.github.fabienbarbero.flickr.api.entities.Photo;
import com.github.fabienbarbero.flickr.api.entities.PhotosResponse;
import com.github.fabienbarbero.flickr.api.entities.VoidResponse;
import java.util.Locale;

/**
 *
 * @author Fabien Barbero
 */
public class GroupsService
        extends FlickrService
{

    GroupsService( OAuthHandler oauthHandler )
    {
        super( oauthHandler );
    }

    /**
     * Returns a list of groups to which you can add photos.
     *
     * @param perPage Number of groups to return per page. The maximum allowed value is 400.
     * @param page The page of results to return
     * @return The groups
     * @throws FlickrException Error getting the groups
     */
    public Paginated<Group> getGroups( int perPage, int page )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.groups.pools.getGroups" );
        args.addParam( "page", page );
        args.addParam( "per_page", perPage );
        return doGet( args, GroupsResponse.class ).getPaginated();
    }

    /**
     * Get information about a group.
     *
     * @param group The group
     * @return The group information
     * @throws FlickrException Error getting the information
     */
    public GroupInfo getGroupInfo( Group group )
            throws FlickrException
    {
        Locale locale = Locale.getDefault();
        CommandArguments args = new CommandArguments( "flickr.groups.getInfo" );
        args.addParam( "group_id", group.getId() );
        args.addParam( "lang", locale.getCountry().toLowerCase() + "-" + locale.getLanguage() );
        return doGet( args, GroupInfoResponse.class ).getInfo();
    }

    /**
     * Returns a list of pool photos for a given group, based on the permissions of the group and the user logged in (if
     * any).
     *
     * @param group The group who's pool you which to get the photo list for
     * @param perPage Number of photos to return per page. The maximum allowed value is 500.
     * @param page The page of results to return.
     * @return The photos
     * @throws FlickrException Error getting the photos
     */
    public Paginated<Photo> getGroupPhotos( Group group, int perPage, int page )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.groups.pools.getPhotos" );
        args.addParam( "group_id", group.getId() );
        args.addParam( "page", page );
        args.addParam( "per_page", perPage );
        return doGet( args, PhotosResponse.class ).getPaginated();
    }

    /**
     * Get a list of the members of a group. The call must be signed on behalf of a Flickr member, and the ability to
     * see the group membership will be determined by the Flickr member's group privileges.
     *
     * @param group The group
     * @param perPage Number of members to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The members
     * @throws FlickrException Error getting the members
     */
    public Paginated<Member> getGroupMembers( Group group, int perPage, int page )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.groups.members.getList" );
        args.addParam( "group_id", group.getId() );
        args.addParam( "page", page );
        args.addParam( "per_page", perPage );
        return doGet( args, MembersResponse.class ).getPaginated();
    }

    /**
     * Search for groups. 18+ groups will only be returned for authenticated calls where the authenticated user is over
     * 18.
     *
     * @param search The text to search for
     * @param perPage Number of members to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The groups found
     * @throws FlickrException Error searching the groups
     */
    public Paginated<Group> searchGroup( String search, int perPage, int page )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.groups.search" );
        args.addParam( "text", search );
        args.addParam( "page", page );
        args.addParam( "per_page", perPage );
        return doGet( args, GroupsResponse.class ).getPaginated();
    }

    /**
     * Join a group
     *
     * @param group The group to join
     * @param acceptRules If the group has rules, they must be displayed to the user prior to joining. Passing a true
     * value for this argument specifies that the application has displayed the group rules to the user, and that the
     * user has agreed to them.
     * @throws FlickrException Error joining the group
     */
    public void joinGroup( Group group, boolean acceptRules )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.groups.join" );
        args.addParam( "group_id", group.getId() );
        args.addParam( "accept_rules", acceptRules );
        doPost( args, VoidResponse.class );
    }

    /**
     * Leave a group. If the user is the only administrator left, and there are other members, the oldest member will be
     * promoted to administrator. If the user is the last person in the group, the group will be deleted.
     *
     * @param group The group to leave
     * @param deleteUserPhotos true to delete the user photos, false otherwise
     * @throws FlickrException Error leaving the group
     */
    public void leaveGroup( Group group, boolean deleteUserPhotos )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.groups.leave" );
        args.addParam( "group_id", group.getId() );
        args.addParam( "delete_photos", deleteUserPhotos );
        doPost( args, VoidResponse.class );
    }

}
