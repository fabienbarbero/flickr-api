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
package com.flickr.api;

import com.flickr.api.entities.BaseUser;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.PhotosResponse;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.VoidResponse;

/**
 *
 * @author Fabien Barbero
 */
public class FavoritesService
        extends FlickrService
{

    FavoritesService( OAuthHandler oauthHandler )
    {
        super( oauthHandler );
    }

    /**
     * Returns a list of the user's favorite photos. Only photos which the calling user has permission to see are
     * returned.
     *
     * @param user The user to fetch the favorites list for
     * @param perPage Number of photos to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The favorites photos
     * @throws FlickrException Error getting the favorites
     */
    public Paginated<Photo> getFavorites( BaseUser user, int perPage, int page )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.favorites.getList" );
        args.addParam( "per_page", perPage );
        args.addParam( "page", page );
        args.addParam( "user_id", user.getId() );

        return doGet( args, PhotosResponse.class ).getPaginated();
    }

    /**
     * Returns a list of favorite public photos for the given user.
     *
     * @param user The user to fetch the favorites list for
     * @param perPage Number of photos to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The favorites photos
     * @throws FlickrException Error getting the favorites
     */
    public Paginated<Photo> getPublicFavorites( BaseUser user, int perPage, int page )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.favorites.getPublicList" );
        args.addParam( "per_page", perPage );
        args.addParam( "page", page );
        args.addParam( "user_id", user.getId() );

        return doGet( args, PhotosResponse.class ).getPaginated();
    }

    /**
     * Add a photo as favorite
     *
     * @param photo The photo to set favorite
     * @throws FlickrException Error setting the photo as favorite
     */
    public void addFavorite( Photo photo )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.favorites.add" );
        args.addParam( "photo_id", photo.getId() );

        doPost( args, VoidResponse.class );
    }

    /**
     * Remove a photo from favorite
     *
     * @param photo The photo to remove from favorites
     * @throws FlickrException Error removing the photo from favorite
     */
    public void removeFavorite( Photo photo )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.favorites.remove" );
        args.addParam( "photo_id", photo.getId() );

        doPost( args, VoidResponse.class );
    }

}
