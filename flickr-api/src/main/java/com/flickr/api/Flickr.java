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

import com.flickr.api.services.AuthenticationService;
import com.flickr.api.services.ContactsService;
import com.flickr.api.entities.UserInfos;
import com.flickr.api.services.FavoritesService;
import com.flickr.api.services.PeopleService;
import com.flickr.api.services.PhotosService;
import com.flickr.api.services.PhotosetsService;
import com.flickr.api.services.StatsService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import oauth.signpost.OAuth;
import oauth.signpost.exception.OAuthException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

/**
 * This class is the entry point of the API.
 *
 * @author Fabien Barbero
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
    private final StatsService statsService;
    private final DefaultHttpClient client;

    /**
     * Create a new Flickr instance
     *
     * @param apiKey The flickr API key
     * @param apiSecret The flickr API secret
     * @param configurationFile The configuration file used to store the OAuth
     * data. At the beginning, this file
     * <b>must</b> not exists.
     */
    public Flickr(String apiKey, String apiSecret, File configurationFile) {
        props = new FlickrProperties(configurationFile);
        oauthHandler = new OAuthHandler(props, apiKey, apiSecret);

        // HttpClient configuration
        HttpParams params = new BasicHttpParams();
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", new PlainSocketFactory(), 80));
        client = new DefaultHttpClient(new ThreadSafeClientConnManager(params, registry), params);

        contactsService = new ContactsService(oauthHandler, client);
        peoplesService = new PeopleService(oauthHandler, client);
        photosService = new PhotosService(oauthHandler, client);
        photosetsService = new PhotosetsService(oauthHandler, client);
        favoritesService = new FavoritesService(oauthHandler, client);
        authenticationService = new AuthenticationService(oauthHandler, client, peoplesService);
        statsService = new StatsService(oauthHandler, client);
    }

    /**
     * Indicates if a user is already logged
     *
     * @return true if a user is logger, false otherwise
     */
    public boolean isLogged() {
        return oauthHandler.getAccessToken() != null;
    }

    /**
     * Get the authorization URL used to allow the access to the application
     *
     * @param callbackUrl The callback URL where the user will be redirected
     * when he will grant the access of the application (see {@link Flickr#verifyToken(java.lang.String)
     * }).
     * @return The authorization URL to open in the browser
     * @throws OAuthException Error getting the URL
     */
    public String getAuthorizationUrl(String callbackUrl) throws OAuthException {
        return oauthHandler.retrieveAuthorizationUrl(callbackUrl);
    }

    /**
     * Verify the token returned in the callback URL. This URL is formed like
     * '[callbackUrl]?oauth_verifier=...&oauth_token=...'.
     *
     * @param url The callback URL with the authorization parameters to verify
     * @throws OAuthException Verification error
     */
    public void verifyToken(String url) throws OAuthException {
        URI uri = URI.create(url);

        Map<String, String> queryParams = new HashMap<String, String>();
        String[] split;
        for (String param : uri.getQuery().split("&")) {
            split = param.split("=");
            queryParams.put(split[0], split[1]);
        }
        verifyToken(queryParams.get(OAuth.OAUTH_VERIFIER), queryParams.get(OAuth.OAUTH_TOKEN));
    }

    /**
     * Verify the token returned in the callback URL
     *
     * @param verifier The verifier value to verify
     * @param token The token value to verify
     * @throws OAuthException Verification error
     */
    public void verifyToken(String verifier, String token) throws OAuthException {
        oauthHandler.retrieveAccessToken(verifier, token);
    }

    /**
     * Authenticate the user
     *
     * @return The user informations
     * @throws FlickrServiceException Authentication error
     */
    public UserInfos authenticate() throws FlickrServiceException {
        return authenticationService.authenticate();
    }

    /**
     * Get the contacts services
     *
     * @return The service
     */
    public ContactsService getContactsService() {
        return contactsService;
    }

    /**
     * Get the people services
     *
     * @return The service
     */
    public PeopleService getPeopleService() {
        return peoplesService;
    }

    /**
     * Get the photos services
     *
     * @return The service
     */
    public PhotosService getPhotosService() {
        return photosService;
    }

    /**
     * Get the photo set services
     *
     * @return The service
     */
    public PhotosetsService getPhotosetsService() {
        return photosetsService;
    }

    /**
     * Get the stats service
     *
     * @return The service
     */
    public StatsService getStatsService() {
        return statsService;
    }

    /**
     * Get the favorites services
     *
     * @return The service
     */
    public FavoritesService getFavoritesService() {
        return favoritesService;
    }

    public InputStream openStream(URL url) throws IOException {
        try {
            HttpGet request = new HttpGet(url.toURI());
            return client.execute(request).getEntity().getContent();

        } catch (URISyntaxException ex) {
            throw new IOException(ex.getMessage(), ex);
        }
    }
}
