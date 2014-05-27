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
package com.flickr.api;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.Before;

/**
 *
 * @author Fabien Barbero
 */
public abstract class AbstractTest
{

    protected Flickr flickr;

    @Before
    public void setUp()
            throws Exception
    {
        // System.setProperty("flickr.api.debug", "true");

        File props = new File( System.getProperty( "user.home" ), ".flickr-api/flickr.conf" );
        flickr = new Flickr( "b8b463e052bb34563b8bd2e14cd02365", "177c21b07922c7f4", "http://localhost", "delete", props );
    }

    protected boolean isAccessible( URL url )
            throws IOException
    {
        HttpURLConnection conn = null;
        try {
            conn = ( HttpURLConnection ) url.openConnection();
            conn.connect();
            return conn.getResponseCode() == 200;

        } finally {
            conn.disconnect();
        }
    }

}
