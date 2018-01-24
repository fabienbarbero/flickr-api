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
import com.github.fabienbarbero.flickr.api.entities.UserInfos;
import com.github.scribejava.core.model.OAuthConstants;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is the entry point of the API.
 *
 * @author Fabien Barbero
 */
public final class Flickr
{

    public static final boolean debug = Boolean.parseBoolean( System.getProperty( "flickr.api.debug", "false" ) );
    private static final String PROP_USER_ID = "user.id";

    private final OAuthHandler oauthHandler;
    private final FlickrProperties props;

    private final ContactsService contactsService;
    private final PeopleService peoplesService;
    private final PhotosService photosService;
    private final PhotosetsService photosetsService;
    private final AuthenticationService authenticationService;
    private final FavoritesService favoritesService;
    private final StatsService statsService;
    private final GroupsService groupsService;
    private final CameraService cameraService;
    private final InterestingnessService interestingnessService;
    private final GalleriesService galleriesService;
    private final UploadService uploadService;

    /**
     * Create a new Flickr instance
     *
     * @param apiKey      The flickr API key
     * @param apiSecret   The flickr API secret
     * @param callbackUrl The callback URL where the user will be redirected when he will grant the access of the
     *                    application (see {@link Flickr#verifyToken(java.lang.String)}).
     * @param configFile  The file where the API will store the internal data (it will be written in clear)s
     * @param permission  The permission to use (read, write or delete) data. At the beginning, this file
     *                    <b>must</b> not exists.
     */
    public Flickr( String apiKey, String apiSecret, String callbackUrl, String permission, File configFile )
    {
        this( apiKey, apiSecret, callbackUrl, permission, new FlickrPropertiesFile( configFile ) );
    }

    /**
     * Create a new Flickr instance
     *
     * @param apiKey      The flickr API key
     * @param apiSecret   The flickr API secret
     * @param callbackUrl The callback URL where the user will be redirected when he will grant the access of the
     *                    application (see {@link Flickr#verifyToken(java.lang.String)}).
     * @param props       The configuration used to store the Flickr information
     * @param permission  The permission to use (read, write or delete) data. At the beginning, this file
     *                    <b>must</b> not exists.
     */
    public Flickr( String apiKey, String apiSecret, String callbackUrl, String permission, FlickrProperties props )
    {
        props.load();
        this.props = props;
        oauthHandler = new OAuthHandler( props, apiKey, apiSecret, callbackUrl, permission );

        contactsService = new ContactsService( oauthHandler );
        peoplesService = new PeopleService( oauthHandler );
        photosService = new PhotosService( oauthHandler );
        photosetsService = new PhotosetsService( oauthHandler );
        favoritesService = new FavoritesService( oauthHandler );
        authenticationService = new AuthenticationService( oauthHandler );
        statsService = new StatsService( oauthHandler );
        groupsService = new GroupsService( oauthHandler );
        cameraService = new CameraService( oauthHandler );
        interestingnessService = new InterestingnessService( oauthHandler );
        galleriesService = new GalleriesService( oauthHandler );
        uploadService = new UploadService( oauthHandler );
    }

    /**
     * Indicates if this is the first start of the API
     *
     * @return true if it is the first start, false otherwise
     */
    public boolean isFirstStart()
    {
        return oauthHandler.getAccessToken() == null;
    }

    /**
     * Get the authorization URL used to allow the access to the application
     *
     * @return The authorization URL to open in the browser
     * @throws FlickrException If the authorization URL retrieve fails
     */
    public String getAuthorizationUrl()
            throws FlickrException
    {
        try {
            return oauthHandler.retrieveAuthorizationUrl();
        } catch ( IOException ex ) {
            throw new FlickrException( "Error getting authorization URL", ex );
        }
    }

    /**
     * Verify the token returned in the callback URL. This URL is formed like
     * <pre>
     *     [callbackUrl]?oauth_verifier=...{@literal &}oauth_token=
     * </pre>
     *
     * @param url The callback URL with the authorization parameters to verify
     * @throws FlickrException Error getting the user information
     */
    public void verifyToken( String url )
            throws FlickrException
    {
        URI uri = URI.create( url );

        Map<String, String> queryParams = new HashMap<>();
        String[] split;
        for ( String param : uri.getQuery().split( "&" ) ) {
            split = param.split( "=" );
            queryParams.put( split[ 0 ], split[ 1 ] );
        }
        verifyToken( queryParams.get( OAuthConstants.VERIFIER ), queryParams.get( OAuthConstants.TOKEN ) );
    }

    /**
     * Verify the token returned in the callback URL. This method ask the server the user information and store them.
     *
     * @param verifier The verifier value to verify
     * @param token    The token value to verify
     * @throws FlickrException Error getting the user information
     */
    public void verifyToken( String verifier, String token )
            throws FlickrException
    {
        try {
            oauthHandler.retrieveAccessToken( verifier, token );
            BaseUser user = authenticationService.authenticate();
            props.putString( PROP_USER_ID, user.getId() );
            props.commit();

        } catch ( IOException ex ) {
            throw new FlickrException( "Error while verifying token", ex );
        }
    }

    /**
     * Get the user identifier.
     *
     * @return The user identifier
     * @throws FlickrException Error getting the user info
     */
    public UserInfos getUser()
            throws FlickrException
    {
        String userId = props.getString( PROP_USER_ID, null );
        if ( userId == null ) {
            return null;
        }
        return peoplesService.getUserInfo( userId );
    }

    /**
     * Get the contacts services
     *
     * @return The service
     */
    public ContactsService getContactsService()
    {
        return contactsService;
    }

    /**
     * Get the people services
     *
     * @return The service
     */
    public PeopleService getPeopleService()
    {
        return peoplesService;
    }

    /**
     * Get the photos services
     *
     * @return The service
     */
    public PhotosService getPhotosService()
    {
        return photosService;
    }

    /**
     * Get the photo set services
     *
     * @return The service
     */
    public PhotosetsService getPhotosetsService()
    {
        return photosetsService;
    }

    /**
     * Get the stats service
     *
     * @return The service
     */
    public StatsService getStatsService()
    {
        return statsService;
    }

    /**
     * Get the groups service
     *
     * @return The service
     */
    public GroupsService getGroupsService()
    {
        return groupsService;
    }

    /**
     * Get the favorites services
     *
     * @return The service
     */
    public FavoritesService getFavoritesService()
    {
        return favoritesService;
    }

    /**
     * Get the cameras service
     *
     * @return The camera service
     */
    public CameraService getCameraService()
    {
        return cameraService;
    }

    /**
     * Get the interestingness service
     *
     * @return The interestingness service
     */
    public InterestingnessService getInterestingnessService()
    {
        return interestingnessService;
    }

    /**
     * Get the galleries service
     *
     * @return The galleries service
     */
    public GalleriesService getGalleriesService()
    {
        return galleriesService;
    }

    public UploadService getUploadService()
    {
        return uploadService;
    }

}
