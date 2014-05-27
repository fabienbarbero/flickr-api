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

import com.flickr.api.entities.Contact;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.User;
import com.flickr.api.entities.UserInfos;
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
