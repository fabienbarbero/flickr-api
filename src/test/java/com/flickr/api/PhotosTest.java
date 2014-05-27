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

import com.flickr.api.entities.ExifInfos;
import com.flickr.api.entities.License;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.PhotoInfos;
import com.flickr.api.entities.PhotoPermissions;
import com.flickr.api.entities.PhotoSize;
import com.flickr.api.entities.Photoset;
import com.flickr.api.entities.PhotosetInfos;
import com.flickr.api.entities.UserInfos;
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
