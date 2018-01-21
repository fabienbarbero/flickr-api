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
package com.github.fabienbarbero.flickr.api.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;

public class IOUtils
{

    private static final int DEFAULT_BUFFER_SIZE = 4096;

    private IOUtils()
    {
    }

    public static final Charset UTF8 = Charset.forName( "UTF-8" );

    /**
     * Convert a string into a byte array using the UTF-8 charset.
     *
     * @param str The string to convert.
     * @return The corresponding byte array.
     */
    public static byte[] toByteArray( String str )
    {
        return str.getBytes( UTF8 );
    }

    /**
     * Close silently a stream.
     *
     * @param closeable The stream to close.
     */
    public static void closeQuietly( Closeable closeable )
    {
        if ( closeable != null ) {
            try {
                closeable.close();
            } catch ( IOException e ) {
            }
        }
    }

    /**
     * Read a String from an InputStream.
     *
     * @param is The InputStream to read.
     * @param charset The Charset to use.
     * @return The String.
     * @throws IOException Error reading stream.
     */
    public static String toString( InputStream is, String charset )
            throws IOException
    {
        StringWriter sw = new StringWriter();
        InputStreamReader reader = new InputStreamReader( is, charset );

        char[] buffer = new char[ DEFAULT_BUFFER_SIZE ];
        long count = 0;
        int n = 0;
        while ( -1 != ( n = reader.read( buffer ) ) ) {
            sw.write( buffer, 0, n );
            count += n;
        }
        return sw.toString();
    }

    /**
     * Copy the content of an {@link InputStream} into a {@link OutputStream}.
     *
     * @param is The stream to read.
     * @param os The output stream.
     * @throws IOException Copy error.
     */
    public static void copy( InputStream is, OutputStream os )
            throws IOException
    {
        byte[] buffer = new byte[ DEFAULT_BUFFER_SIZE ];
        int len;
        while ( ( len = is.read( buffer ) ) != -1 ) {
            os.write( buffer, 0, len );
        }
    }

}
