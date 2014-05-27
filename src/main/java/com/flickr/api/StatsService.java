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
