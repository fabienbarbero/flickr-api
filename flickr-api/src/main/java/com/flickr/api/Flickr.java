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
package com.flickr.api;

import com.flickr.api.auth.AuthenticationService;
import com.flickr.api.auth.AuthenticationServiceImpl;
import com.flickr.api.contacts.ContactsService;
import com.flickr.api.contacts.ContactsServiceImpl;
import com.flickr.api.entities.UserInfo;
import com.flickr.api.favorites.FavoritesService;
import com.flickr.api.favorites.FavoritesServiceImpl;
import com.flickr.api.people.PeopleService;
import com.flickr.api.people.PeopleServiceImpl;
import com.flickr.api.photos.PhotosService;
import com.flickr.api.photos.PhotosServiceImpl;
import com.flickr.api.photosets.PhotosetsService;
import com.flickr.api.photosets.PhotosetsServiceImpl;
import java.io.File;
import oauth.signpost.exception.OAuthException;

/**
 *
 * @author fabien
 */
public final class Flickr {

    public static final boolean debug = Boolean.parseBoolean(System.getProperty("flickr.api.debug", "false"));
    //
    private final OAuthHandler oauthHandler;
    private final FlickrProperties props;
    //
    private final ContactsService contactsService;
    private final PeopleService peoplesService;
    private final PhotosService photosService;
    private final PhotosetsService photosetsService;
    private final AuthenticationService authenticationService;
    private final FavoritesService favoritesService;

    public Flickr(String apiKey, String apiSecret, File configurationFile) {
        props = new FlickrProperties(configurationFile);
        oauthHandler = new OAuthHandler(props, apiKey, apiSecret);

        contactsService = new ContactsServiceImpl(oauthHandler);
        peoplesService = new PeopleServiceImpl(oauthHandler);
        photosService = new PhotosServiceImpl(oauthHandler);
        photosetsService = new PhotosetsServiceImpl(oauthHandler);
        favoritesService = new FavoritesServiceImpl(oauthHandler);
        authenticationService = new AuthenticationServiceImpl(oauthHandler, peoplesService);
    }

    public boolean isLogged() {
        return oauthHandler.getAccessToken() != null;
    }

    public String getAuthorizationUrl(String callbackUrl) throws OAuthException {
        return oauthHandler.retrieveAuthorizationUrl(callbackUrl);
    }
    
    public void verifyToken(String verifier, String token) throws OAuthException {
        oauthHandler.retrieveAccessToken(verifier, token);
    }
    
    public UserInfo authenticate() throws FlickrServiceException {
        return authenticationService.authenticate();
    }

    public ContactsService getContactsService() {
        return contactsService;
    }

    public PeopleService getPeopleService() {
        return peoplesService;
    }

    public PhotosService getPhotosService() {
        return photosService;
    }

    public PhotosetsService getPhotosetsService() {
        return photosetsService;
    }

    public FavoritesService getFavoritesService() {
        return favoritesService;
    }

}
