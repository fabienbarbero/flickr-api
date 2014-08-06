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
package org.ez.flickr.api;

import org.ez.flickr.api.entities.BaseUser;
import org.ez.flickr.api.entities.GalleriesResponse;
import org.ez.flickr.api.entities.Gallery;
import org.ez.flickr.api.entities.GalleryResponse;
import org.ez.flickr.api.entities.Paginated;
import org.ez.flickr.api.entities.Photo;
import org.ez.flickr.api.entities.PhotosResponse;
import org.ez.flickr.api.entities.VoidResponse;

/**
 *
 * @author Fabien Barbero
 */
public class GalleriesService
        extends FlickrService
{

    GalleriesService( OAuthHandler oauthHandler )
    {
        super( oauthHandler );
    }

    /**
     * Return the list of galleries created by a user. Sorted from newest to oldest.
     *
     * @param user The user to get a galleries list for
     * @param perPage Number of galleries to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The galleries
     * @throws FlickrException Error getting the galleries
     */
    public Paginated<Gallery> getGalleries( BaseUser user, int perPage, int page )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.galleries.getList" );
        args.addParam( "per_page", perPage );
        args.addParam( "page", page );
        args.addParam( "user_id", user.getId() );

        return doGet( args, GalleriesResponse.class ).getPaginated();
    }

    /**
     * Return the list of photos for a gallery
     *
     * @param gallery The gallery of photos to return
     * @param perPage Number of galleries to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The photos
     * @throws FlickrException Error getting the photos
     */
    public Paginated<Photo> getGalleryPhotos( Gallery gallery, int perPage, int page )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.galleries.getPhotos" );
        args.addParam( "per_page", perPage );
        args.addParam( "page", page );
        args.addParam( "gallery_id", gallery.getId() );

        return doGet( args, PhotosResponse.class ).getPaginated();
    }

    /**
     * Create a new gallery
     *
     * @param title The gallery title
     * @param description The gallery description
     * @param primaryPhoto The primary photo (optional)
     * @return The created gallery
     * @throws FlickrException Error creating the gallery
     */
    public Gallery createGallery( String title, String description, Photo primaryPhoto )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.galleries.create" );
        args.addParam( "title", title );
        args.addParam( "description", description );
        if ( primaryPhoto != null ) {
            args.addParam( "primary_photo_id", primaryPhoto.getId() );
        }

        return doPost( args, GalleryResponse.class ).getGallery();
    }

    /**
     * Add a photo to a gallery
     *
     * @param gallery The gallery
     * @param photo The photo to add
     * @param comment A comment (optional)
     * @throws FlickrException Error adding the photo
     */
    public void addPhotoToGallery( Gallery gallery, Photo photo, String comment )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.galleries.addPhoto" );
        args.addParam( "gallery_id", gallery.getId() );
        args.addParam( "photo_id", photo.getId() );
        if ( comment != null ) {
            args.addParam( "comment", comment );
        }

        doPost( args, VoidResponse.class );
    }

}
