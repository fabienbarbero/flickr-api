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

import com.github.fabienbarbero.flickr.api.entities.CameraBrand;
import com.github.fabienbarbero.flickr.api.entities.CameraBrandModel;
import com.github.fabienbarbero.flickr.api.entities.Group;
import com.github.fabienbarbero.flickr.api.entities.GroupInfos;
import com.github.fabienbarbero.flickr.api.entities.Member;
import com.github.fabienbarbero.flickr.api.entities.Paginated;
import com.github.fabienbarbero.flickr.api.entities.Photo;
import com.github.fabienbarbero.flickr.api.entities.PhotoStats;
import com.github.fabienbarbero.flickr.api.entities.TotalViews;
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
