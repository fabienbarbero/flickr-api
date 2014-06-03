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
package com.flickr.api.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils
{

    private JSONUtils()
    {
    }

    private static final String DATE_REGEX = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();
    private static final String DATE_TIME_REGEX = "^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}$";
    private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

    /**
     * Get a URL object from a String.
     *
     * @param url The url as a String.
     * @return The URL object.
     */
    public static URL urlFromString( String url )
    {
        try {
            return new URL( url );
        } catch ( MalformedURLException ex ) {
            throw new UnsupportedOperationException( ex.getMessage(), ex );
        }
    }

    /**
     * Get a date from a String.
     *
     * @param s The date as a String.
     * @return The date.
     */
    public static Date dateFromString( String s )
    {
        if ( s == null ) {
            return null;
        }
        try {
            if ( s.matches( DATE_REGEX ) ) {
                return DATE_FORMAT.parse( s );

            } else if ( s.matches( DATE_TIME_REGEX ) ) {
                return DATE_TIME_FORMAT.parse( s );

            } else {
                long date = Long.parseLong( s );
                return new Date( date * 1000 );
            }

        } catch ( ParseException ex ) {
            throw new UnsupportedOperationException( "Error parsing date", ex );
        }
    }

    /**
     * Get a content value. Some values can be included in a sub json object. For instance :
     *
     * <pre>
     * "title":{"_content":"My Photoset"}
     * </pre>
     *
     * @param json The json object to parse.
     * @param key The key of the content to get (in the premious example it will be "title").
     * @return The content.
     * @throws JSONException Parsing error.
     */
    public static String getContent( JSONObject json, String key )
            throws JSONException
    {
        String value = json.getJSONObject( key ).getString( "_content" );
        if ( "null".equals( value ) ) {
            return null;
        }
        return value;
    }

    public static int getIntegerContent( JSONObject json, String key )
            throws JSONException
    {
        return Integer.parseInt( getContent( json, key ) );
    }

}
