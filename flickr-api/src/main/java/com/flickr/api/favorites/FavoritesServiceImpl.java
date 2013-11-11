/*
 * Copyright (C) 2013 Fabien Barbero
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
package com.flickr.api.favorites;

import com.flickr.api.CommandArguments;
import com.flickr.api.FlickrService;
import com.flickr.api.FlickrServiceException;
import com.flickr.api.OAuthHandler;
import com.flickr.api.entities.BaseUser;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.PaginatedPhotosResponse;
import com.flickr.api.entities.Photo;
import org.apache.http.client.HttpClient;

/**
 *
 * @author fabien
 */
public class FavoritesServiceImpl extends FlickrService implements FavoritesService {
    
    public FavoritesServiceImpl(OAuthHandler oauthHandler, HttpClient client) {
        super(oauthHandler, client);
    }

    @Override
    public Paginated<Photo> getFavorites(BaseUser user, int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.favorites.getList");
        args.put("per_page", perPage);
        args.put("page", page);
        args.put("user_id", user.getId());
        
        return doGet(args, PaginatedPhotosResponse.class).getPhotos();
    }

    @Override
    public Paginated<Photo> getPublicFavorites(BaseUser user, int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.favorites.getPublicList");
        args.put("per_page", perPage);
        args.put("page", page);
        args.put("user_id", user.getId());
        
        return doGet(args, PaginatedPhotosResponse.class).getPhotos();
    }
    
}
