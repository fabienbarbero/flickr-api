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

import easy.flickr.api.entities.Contact;
import easy.flickr.api.entities.Paginated;
import easy.flickr.api.entities.Photo;
import easy.flickr.api.entities.User;
import easy.flickr.api.entities.UserInfos;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Fabien Barbero
 */
public class UsersTest
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
    public void testContacts()
            throws Exception
    {
        // Contacts
        Paginated<Contact> contacts = flickr.getContactsService().getContacts( 10, 1 );
        assertFalse( contacts.isEmpty() );
        Contact contact = contacts.get( 0 );
        assertNotNull( contact.getId() );
        assertNotNull( contact.getRealName() );
        assertNotNull( contact.getUserName() );
        assertNotNull( contact.getAvatar() );
    }

    @Test
    public void testPeople()
            throws Exception
    {
        // People
        UserInfos userInfo = flickr.getPeopleService().getUserInfo( caller );
        assertNotNull( userInfo );
        assertNotNull( userInfo.getAvatar() );
        assertNotNull( userInfo.getId() );
        assertNotNull( userInfo.getPhotosUrl() );
        assertNotNull( userInfo.getProfileUrl() );
        assertNotNull( userInfo.getRealName() );
        assertNotNull( userInfo.getUserName() );
        assertNotNull( userInfo.getPhotosInfo().getFirstDate() );
        assertNotNull( userInfo.getPhotosInfo().getFirstDateTaken() );
        assertTrue( userInfo.getPhotosInfo().getCount() > 0 );

        User user = flickr.getPeopleService().findByEmail( "fabien.barbero@gmail.com" );
        assertNotNull( user );
        assertEquals( caller.getId(), user.getId() );

        Paginated<Photo> photos = flickr.getPeopleService().getUserPhotos( user, 10, 1 );
        assertFalse( photos.isEmpty() );
    }

}
