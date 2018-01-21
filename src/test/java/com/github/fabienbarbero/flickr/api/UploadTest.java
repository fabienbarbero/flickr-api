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

import com.github.fabienbarbero.flickr.api.entities.PhotoInfos;
import com.github.fabienbarbero.flickr.api.entities.UploadedPhoto;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fabien Barbero
 */
public class UploadTest
        extends AbstractTest
{

    @Test
    public void testUpload()
            throws Exception
    {
        File photoFile = new File( UploadTest.class.getResource( "photo.jpg" ).toURI() );
        UploadedPhoto photo = flickr.getUploadService().uploadPhoto( photoFile, "test", null );
        assertNotNull( photo );

        PhotoInfos infos = flickr.getPhotosService().getInfos( photo );
        assertEquals( "test", infos.getTitle() );

        // Delete photo
        flickr.getPhotosService().deletePhoto( photo );

        try {
            flickr.getPhotosService().getInfos( photo );
            fail( "Photo no longer exists" );
        } catch ( FlickrException ex ) {
            assertEquals( FlickrErrorCode.not_found, ex.getErrorCode() );
        }
    }

}
