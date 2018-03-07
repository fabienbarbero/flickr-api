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

import com.github.fabienbarbero.flickr.api.entities.*;
import com.github.fabienbarbero.flickr.api.entities.PhotoInfo;

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

    private UserInfo caller;

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

        PhotosetInfo photosetInfo = flickr.getPhotosetsService().getInfo( set );
        assertNotNull( photosetInfo );
        assertNotNull( photosetInfo.getCreateDate() );
        assertNotNull( photosetInfo.getDescription() );
        assertNotNull( photosetInfo.getId() );
        assertNotNull( photosetInfo.getUpdateDate() );

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

        PhotoInfo photoInfo = flickr.getPhotosService().getInfo( photo );
        assertNotNull( photoInfo );
        assertNotNull( photoInfo.getDates().getLastUpdateDate() );
        assertNotNull( photoInfo.getDates().getPostedDate() );
        assertNotNull( photoInfo.getDates().getTakenDate() );
        assertNotNull( photoInfo.getDescription() );
        assertNotNull( photoInfo.getEditability() );
        assertNotNull( photoInfo.getLicense() );
        assertNotNull( photoInfo.getOwner().getId() );
        assertNotNull( photoInfo.getOwner().getRealName() );
        assertNotNull( photoInfo.getOwner().getUserName() );
        assertNotNull( photoInfo.getPublicEditability() );
        assertNotNull( photoInfo.getTitle() );
        assertNotNull( photoInfo.getUploadedDate() );
        assertNotNull( photoInfo.getUsage() );
        assertNotNull( photoInfo.getVisibility() );
//        assertTrue(photoInfo.getViews() > 0);

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

        ExifInfo exifInfo = flickr.getPhotosService().getExif( photo );
        assertNotNull( exifInfo );
        assertNotNull( exifInfo.getCamera() );
        assertFalse( exifInfo.getEntries().isEmpty() );

        flickr.getPhotosService().getComments( photo );
    }

}
