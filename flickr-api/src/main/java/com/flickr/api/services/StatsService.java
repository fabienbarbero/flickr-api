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
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.PhotoStats;
import com.flickr.api.entities.PhotoStatsResponse;
import com.flickr.api.entities.TotalViews;
import com.flickr.api.entities.TotalViewsResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.client.HttpClient;

/**
 *
 * @author Fabien Barbero
 */
public class StatsService extends FlickrService {
    
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public StatsService(OAuthHandler oauthHandler, HttpClient client) {
        super(oauthHandler, client);
    }

    public Paginated<PhotoStats> getPopularPhotos(Date startDate, int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.stats.getPopularPhotos", false);
        args.put("per_page", perPage);
        args.put("page", page);
        if(startDate != null) {
            args.put("date", DATE_FORMAT.format(startDate));
        }
        return doGet(args, PhotoStatsResponse.class).getPhotoStats();
    }
    
    public TotalViews getTotalViews(Date startDate) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.stats.getTotalViews", false);
        if(startDate != null) {
            args.put("date", DATE_FORMAT.format(startDate));
        }
        return doGet(args, TotalViewsResponse.class).getViews();
    }

}
