package com.flickr.api.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class URLUtils
{

    private URLUtils()
    {
    }

    public static URL fromString( String urlStr )
    {
        try {
            return new URL( urlStr );
        } catch ( MalformedURLException e ) {
            throw new UnsupportedOperationException( "URL conversion error", e );
        }
    }

}
