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

import easy.easy.flickr.api.Flickr;
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
