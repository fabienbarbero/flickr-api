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

import com.flickr.api.entities.UploadedPhoto;
import com.flickr.api.entities.UploadedPhotoResponse;
import java.io.File;

/**
 *
 * @author Fabien Barbero
 */
public class UploadService
        extends FlickrService
{

    UploadService( OAuthHandler oauthHandler )
    {
        super( oauthHandler );
    }

    /**
     * Upload a new photo on Flickr
     *
     * @param file The photo file
     * @param title The photo title (optional)
     * @param description The photo description (optional)
     * @return The new photo identifier
     * @throws FlickrException Upload error
     */
    public UploadedPhoto uploadPhoto( File file, String title, String description )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments();
        args.addParam( "photo", file );
        args.addParam( "content_type", 1 );
        if ( title != null ) {
            args.addParam( "title", title );
        }
        if ( description != null ) {
            args.addParam( "description", description );
        }

        return doPost( args, UploadedPhotoResponse.class, "https://up.flickr.com/services/upload" ).getPhoto();
    }

}
