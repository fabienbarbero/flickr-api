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

import com.flickr.api.entities.CameraBrand;
import com.flickr.api.entities.CameraBrandModel;
import com.flickr.api.entities.Group;
import com.flickr.api.entities.GroupInfos;
import com.flickr.api.entities.Member;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.PhotoStats;
import com.flickr.api.entities.TotalViews;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Fabien Barbero
 */
public class VariousTest
        extends AbstractTest
{

    @Test
    public void testStatistics()
            throws Exception
    {
        // Stats
        TotalViews views = flickr.getStatsService().getTotalViews( null );
        assertNotNull( views );
        assertTrue( views.getPhotosViews() > 0 );
        assertTrue( views.getPhotosetsViews() > 0 );

        Paginated<PhotoStats> photoStats = flickr.getStatsService().getPopularPhotos( null, 10, 1 );
        assertFalse( photoStats.isEmpty() );
        assertNotNull( photoStats.get( 0 ).getPhoto() );
        assertNotNull( photoStats.get( 0 ).getStats() );
    }

    @Test
    public void testGroups()
            throws Exception
    {
        // Groups
        Paginated<Group> groups = flickr.getGroupsService().getGroups( 500, 1 );
        assertFalse( groups.isEmpty() );
        Group group = groups.get( 0 );
        assertNotNull( group.getName() );
        assertTrue( group.getPhotos() > 0 );
//            assertTrue(isAccessible(group.getCover()));

        GroupInfos groupInfos = flickr.getGroupsService().getGroupInfos( group );
        assertNotNull( groupInfos );
        assertNotNull( groupInfos.getDescription() );
        assertNotNull( groupInfos.getName() );
        assertNotNull( groupInfos.getRules() );
        assertTrue( groupInfos.getMembers() > 0 );

        Paginated<Photo> photos = flickr.getGroupsService().getGroupPhotos( group, 50, 1 );
        assertFalse( photos.isEmpty() );

        Paginated<Member> members = flickr.getGroupsService().getGroupMembers( group, 1000, 1 );
        assertFalse( members.isEmpty() );
    }

    @Test
    public void testCamera()
            throws Exception
    {
        // Cameras
        List<CameraBrand> brands = flickr.getCameraService().getBrands();
        assertFalse( brands.isEmpty() );

        List<CameraBrandModel> models = flickr.getCameraService().getBrandModels( brands.get( 0 ) );
        assertFalse( models.isEmpty() );
    }

    @Test
    public void testInterestingness()
            throws Exception
    {
        // Interestingness
        Paginated<Photo> photos = flickr.getInterestingnessService().getInterestingPhotos( 1000, 1 );
        assertFalse( photos.isEmpty() );
    }

}
