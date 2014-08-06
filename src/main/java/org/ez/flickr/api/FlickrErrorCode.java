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

/**
 *
 * @author Fabien Barbero
 */
public enum FlickrErrorCode
{

    not_found( 1 ),
    general_upload_failure( 3 ),
    filesize_was_zero( 4 ),
    filetype_was_not_recognized( 5 ),
    user_exceeded_upload_limit( 6 ),
    invalid_signature( 96 ),
    missing_signature( 97 ),
    invalid_auth_token( 98 ),
    user_not_logged_in( 99 ),
    invalid_api_key( 100 ),
    service_currently_unavailable( 105 ),
    invalid_prob( 108 ),
    format_not_found( 111 ),
    method_not_found( 112 ),
    bad_url_found( 116 );

    private int code;

    private FlickrErrorCode( int code )
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }

    /**
     * Get the error code from an integer value.
     *
     * @param code The error code as an integer.
     * @return The corresponding error code.
     */
    public static FlickrErrorCode fromCode( int code )
    {
        for ( FlickrErrorCode flickrCode : values() ) {
            if ( flickrCode.getCode() == code ) {
                return flickrCode;
            }
        }
        return null;    // Should never happen !
    }

}
