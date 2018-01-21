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

import com.github.fabienbarbero.flickr.api.entities.BaseUser;
import com.github.fabienbarbero.flickr.api.entities.Group;
import com.github.fabienbarbero.flickr.api.entities.GroupsResponse;
import com.github.fabienbarbero.flickr.api.entities.Paginated;
import com.github.fabienbarbero.flickr.api.entities.PhotosResponse;
import com.github.fabienbarbero.flickr.api.entities.Photo;
import com.github.fabienbarbero.flickr.api.entities.User;
import com.github.fabienbarbero.flickr.api.entities.UserInfos;
import com.github.fabienbarbero.flickr.api.entities.UserInfoResponse;
import com.github.fabienbarbero.flickr.api.entities.UserResponse;
import java.util.List;

/**
 *
 * @author Fabien Barbero
 */
public class PeopleService
        extends FlickrService
{

    PeopleService( OAuthHandler oauthHandler )
    {
        super( oauthHandler );
    }

    /**
     * Find a user by its email address
     *
     * @param email The email address of the user to find (may be primary or secondary).
     * @return The user found or null
     * @throws FlickrException Eror finding the user
     */
    public User findByEmail( String email )
            throws FlickrException
    {
        try {
            CommandArguments args = new CommandArguments( "flickr.people.findByEmail" );
            args.addParam( "find_email", email );
            return doGet( args, UserResponse.class ).getUser();
        } catch ( FlickrException ex ) {
            if ( ex.getErrorCode() == FlickrErrorCode.not_found ) {
                return null;
            } else {
                throw ex;
            }
        }
    }

    /**
     * Find a user by its username
     *
     * @param userName The username to search
     * @return The user found, or null
     * @throws FlickrException Error getting the user
     */
    public User findByUserName( String userName )
            throws FlickrException
    {
        try {
            CommandArguments args = new CommandArguments( "flickr.people.findByUsername" );
            args.addParam( "username", userName );
            return doGet( args, UserResponse.class ).getUser();
        } catch ( FlickrException ex ) {
            if ( ex.getErrorCode() == FlickrErrorCode.not_found ) {
                return null;
            } else {
                throw ex;
            }
        }
    }

    /**
     * Get the user informations
     *
     * @param user The user
     * @return The user informations
     * @throws FlickrException Error getting the informations
     */
    public UserInfos getUserInfo( BaseUser user )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.people.getInfo" );
        args.addParam( "user_id", user.getId() );
        return doGet( args, UserInfoResponse.class ).getUserInfo();
    }

    public UserInfos getUserInfo( String userId )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.people.getInfo" );
        args.addParam( "user_id", userId );
        return doGet( args, UserInfoResponse.class ).getUserInfo();
    }

    /**
     * Return photos from the given user's photostream. Only photos visible to the calling user will be returned.
     *
     * @param user The user
     * @param perPage Number of photos to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The photos
     * @throws FlickrException Error getting the photos
     */
    public Paginated<Photo> getUserPhotos( BaseUser user, int perPage, int page )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.people.getPhotos" );
        args.addParam( "user_id", user.getId() );
        args.addParam( "per_page", perPage );
        args.addParam( "page", page );
        return doGet( args, PhotosResponse.class ).getPaginated();
    }

    /**
     * Get a list of public photos for the given user.
     *
     * @param user The user
     * @param perPage Number of photos to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The photos
     * @throws FlickrException Error getting the photos
     */
    public Paginated<Photo> getUserPublicPhotos( BaseUser user, int perPage, int page )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.people.getPublicPhotos" );
        args.addParam( "user_id", user.getId() );
        args.addParam( "per_page", perPage );
        args.addParam( "page", page );
        return doGet( args, PhotosResponse.class ).getPaginated();
    }

    /**
     * Returns a list of photos containing a particular Flickr member.
     *
     * @param user The user you want to find photos of
     * @param owner A Flickr member. This will restrict the list of photos to those taken by that member.
     * @param perPage Number of photos to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The photos
     * @throws FlickrException Error getting the photos
     */
    public Paginated<Photo> getUserPhotosOf( BaseUser user, BaseUser owner, int perPage, int page )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.people.getPhotosOf" );
        args.addParam( "user_id", user.getId() );
        args.addParam( "owner_id", owner.getId() );
        args.addParam( "per_page", perPage );
        args.addParam( "page", page );
        return doGet( args, PhotosResponse.class ).getPaginated();
    }

    /**
     * Returns the list of groups a user is a member of.
     *
     * @param user The user to fetch groups for
     * @return The groups
     * @throws FlickrException Error getting the groups
     */
    public List<Group> getUserGroups( BaseUser user )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.people.getPhotosOf" );
        args.addParam( "user_id", user.getId() );
        return doGet( args, GroupsResponse.class ).getPaginated().asList();
    }

    /**
     * Returns the list of public groups a user is a member of.
     *
     * @param user The user to fetch groups for
     * @return The public groups
     * @throws FlickrException Error getting the groups
     */
    public List<Group> getUserPublicGroups( BaseUser user )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.people.getPublicGroups" );
        args.addParam( "user_id", user.getId() );
        return doGet( args, GroupsResponse.class ).getPaginated().asList();
    }

}
