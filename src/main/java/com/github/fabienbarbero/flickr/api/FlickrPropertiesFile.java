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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Simple implementation of {@link FlickrProperties} which store the data in a file.
 *
 * @author Fabien Barbero
 */
public class FlickrPropertiesFile
        extends FlickrProperties
{

    private final File file;

    public FlickrPropertiesFile( File file )
    {
        this.file = file;
    }

    @Override
    protected boolean isConfigExists()
    {
        return file.exists();
    }

    @Override
    protected InputStream getInputStream()
            throws IOException
    {
        return new FileInputStream( file );
    }

    @Override
    protected OutputStream getOutputStream()
            throws IOException
    {
        return new FileOutputStream( file );
    }

}
