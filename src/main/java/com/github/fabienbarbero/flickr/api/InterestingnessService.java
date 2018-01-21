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

import com.github.fabienbarbero.flickr.api.entities.Paginated;
import com.github.fabienbarbero.flickr.api.entities.PhotosResponse;
import com.github.fabienbarbero.flickr.api.entities.Photo;

/**
 *
 * @author Fabien Barbero
 */
public class InterestingnessService
        extends FlickrService
{

    InterestingnessService( OAuthHandler oauthHandler )
    {
        super( oauthHandler );
    }

    /**
     * Returns the list of interesting photos for the most recent day or a user-specified date.
     *
     * @param perPage Number of photos to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The photos
     * @throws FlickrException Error getting the photos
     */
    public Paginated<Photo> getInterestingPhotos( int perPage, int page )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.interestingness.getList" );
        args.addParam( "page", page );
        args.addParam( "per_page", perPage );
        return doGet( args, PhotosResponse.class ).getPaginated();
    }

}
