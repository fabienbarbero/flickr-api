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

import com.github.fabienbarbero.flickr.api.entities.ExifInfos;
import com.github.fabienbarbero.flickr.api.entities.License;
import com.github.fabienbarbero.flickr.api.entities.Paginated;
import com.github.fabienbarbero.flickr.api.entities.Photo;
import com.github.fabienbarbero.flickr.api.entities.PhotoInfos;
import com.github.fabienbarbero.flickr.api.entities.PhotoPermissions;
import com.github.fabienbarbero.flickr.api.entities.PhotoSize;
import com.github.fabienbarbero.flickr.api.entities.Photoset;
import com.github.fabienbarbero.flickr.api.entities.PhotosetInfos;
import com.github.fabienbarbero.flickr.api.entities.UserInfos;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Fabien Barbero
 */
public class PhotosTest
        extends AbstractTest
{

    private UserInfos caller;

    @Override
    public void setUp()
            throws Exception
    {
        super.setUp();
        caller = flickr.getUser();
    }

    @Test
    public void testFavorites()
            throws Exception
    {
        // Favorites
        Paginated<Photo> photos = flickr.getFavoritesService().getFavorites( caller, 10, 1 );
        assertFalse( photos.isEmpty() );
        Photo photo = photos.get( 0 );
        assertNotNull( photo.getId() );
        assertNotNull( photo.getImage() );
        assertNotNull( photo.getOwner() );
        assertNotNull( photo.getTitle() );
    }

    @Test
    public void testPhotoset()
            throws Exception
    {
        // Photoset
        Paginated<Photoset> sets = flickr.getPhotosetsService().getPhotosets( caller, 10, 0 );
        assertFalse( sets.isEmpty() );
        Photoset set = sets.get( 1 );
        assertNotNull( set.getId() );
        assertNotNull( set.getDescription() );
        assertNotNull( set.getTitle() );
        assertNotNull( set.getPrimaryPhoto() );
        assertNotNull( set.getCreationDate() );
        assertNotNull( set.getUpdateDate() );
        assertTrue( set.getPhotosCount() > 0 );
//        assertTrue(set.getCountViews() > 0);

        PhotosetInfos photosetInfos = flickr.getPhotosetsService().getInfos( set );
        assertNotNull( photosetInfos );
        assertNotNull( photosetInfos.getCreateDate() );
        assertNotNull( photosetInfos.getDescription() );
        assertNotNull( photosetInfos.getId() );
        assertNotNull( photosetInfos.getUpdateDate() );

        flickr.getPhotosetsService().getComments( set );

        Paginated<Photo> photos = flickr.getPhotosetsService().getPhotos( set, 10, 1 );
        assertFalse( photos.isEmpty() );
    }

    @Test
    public void testPhotos()
            throws Exception
    {
        // Photos
        Paginated<Photo> photos = flickr.getPhotosService().getContactsPhotos();
        assertFalse( photos.isEmpty() );
        Photo photo = photos.get( 0 );

        PhotoInfos photoInfos = flickr.getPhotosService().getInfos( photo );
        assertNotNull( photoInfos );
        assertNotNull( photoInfos.getDates().getLastUpdateDate() );
        assertNotNull( photoInfos.getDates().getPostedDate() );
        assertNotNull( photoInfos.getDates().getTakenDate() );
        assertNotNull( photoInfos.getDescription() );
        assertNotNull( photoInfos.getEditability() );
        assertNotNull( photoInfos.getLicense() );
        assertNotNull( photoInfos.getOwner().getId() );
        assertNotNull( photoInfos.getOwner().getRealName() );
        assertNotNull( photoInfos.getOwner().getUserName() );
        assertNotNull( photoInfos.getPublicEditability() );
        assertNotNull( photoInfos.getTitle() );
        assertNotNull( photoInfos.getUploadedDate() );
        assertNotNull( photoInfos.getUsage() );
        assertNotNull( photoInfos.getVisibility() );
//        assertTrue(photoInfos.getViews() > 0);

        PhotoPermissions access = flickr.getPhotosService().getPermissions( photo );
        assertNotNull( access );

        photos = flickr.getPhotosService().getRecent( 10, 1 );
        assertFalse( photos.isEmpty() );

        photos = flickr.getPhotosService().getRecentlyUpdated( 10, 1 );
        assertFalse( photos.isEmpty() );
        photo = photos.get( 0 );

        List<PhotoSize> sizes = flickr.getPhotosService().getSizes( photo );
        assertFalse( sizes.isEmpty() );

        List<License> licenses = flickr.getPhotosService().getLicenses();
        assertFalse( licenses.isEmpty() );

        ExifInfos exifInfos = flickr.getPhotosService().getExif( photo );
        assertNotNull( exifInfos );
        assertNotNull( exifInfos.getCamera() );
        assertFalse( exifInfos.getEntries().isEmpty() );

        flickr.getPhotosService().getComments( photo );
    }

}
