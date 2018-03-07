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

import com.github.fabienbarbero.flickr.api.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * This class handle the configuration read and storage.
 *
 * @author Fabien Barbero
 */
public abstract class AbstractFlickrProperties
        implements FlickrProperties
{

    private final Properties props;

    public AbstractFlickrProperties()
    {
        this.props = new Properties();
    }

    @Override
    public boolean contains( String key )
    {
        return props.containsKey( key );
    }

    @Override
    public String getString( String key, String def )
    {
        return props.getProperty( key, def );
    }

    @Override
    public void putString( String key, String value )
    {
        props.setProperty( key, value );
    }

    @Override
    public void remove( String key )
    {
        props.remove( key );
    }

    @Override
    public void load()
    {
        if ( isConfigExists() ) {
            InputStream is = null;
            try {
                is = getInputStream();
                props.load( is );

            } catch ( IOException ex ) {
                throw new UnsupportedOperationException( "Error reading flickr properties", ex );
            } finally {
                IOUtils.closeQuietly( is );
            }
        }
    }

    @Override
    public void commit()
    {
        OutputStream os = null;
        try {
            os = getOutputStream();
            props.store( os, "Flickr configuration" );

        } catch ( IOException ex ) {
            throw new UnsupportedOperationException( "Error saving configuration", ex );
        } finally {
            IOUtils.closeQuietly( os );
        }
    }

    /**
     * Indicates if the configuration has already been defined
     *
     * @return true if the configuration has already been defined
     */
    protected abstract boolean isConfigExists();

    /**
     * Open an InputStream to read the configuration
     *
     * @return The stream
     * @throws IOException Error opening the stream
     */
    protected abstract InputStream getInputStream()
            throws IOException;

    /**
     * Open an OutputStream to write the configuration
     *
     * @return The stream
     * @throws IOException Error opening the stream
     */
    protected abstract OutputStream getOutputStream()
            throws IOException;

}
