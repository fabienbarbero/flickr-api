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

import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.PhotoStats;
import com.flickr.api.entities.PhotoStatsResponse;
import com.flickr.api.entities.TotalViews;
import com.flickr.api.entities.TotalViewsResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Fabien Barbero
 */
public class StatsService
        extends FlickrService
{

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat( "yyyy-MM-dd" );

    StatsService( OAuthHandler oauthHandler )
    {
        super( oauthHandler );
    }

    /**
     * List the photos with the most views, comments or favorites
     *
     * @param date Stats will be returned for this date
     * @param perPage Number of referrers to return per page. The maximum allowed value is 100.
     * @param page The page of results to return
     * @return The stats
     * @throws FlickrException Error getting the stats
     */
    public Paginated<PhotoStats> getPopularPhotos( Date date, int perPage, int page )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.stats.getPopularPhotos" );
        args.addParam( "per_page", perPage );
        args.addParam( "page", page );
        if ( date != null ) {
            args.addParam( "date", DATE_FORMAT.format( date ) );
        }
        return doGet( args, PhotoStatsResponse.class ).getPaginated();
    }

    /**
     * Get the overall view counts for an account
     *
     * @param date Stats will be returned for this date
     * @return The views
     * @throws FlickrException
     */
    public TotalViews getTotalViews( Date date )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.stats.getTotalViews" );
        if ( date != null ) {
            args.addParam( "date", DATE_FORMAT.format( date ) );
        }
        return doGet( args, TotalViewsResponse.class ).getViews();
    }

}
