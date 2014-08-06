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
package org.ez.flickr.api;

import org.ez.flickr.api.entities.Contact;
import org.ez.flickr.api.entities.BaseUser;
import org.ez.flickr.api.entities.Paginated;
import org.ez.flickr.api.entities.ContactsResponse;

/**
 * Service used to get the contacts informations.
 *
 * @author Fabien Barbero
 */
public final class ContactsService
        extends FlickrService
{

    ContactsService( OAuthHandler oauthHandler )
    {
        super( oauthHandler );
    }

    /**
     * Get a list of contacts for the calling user
     *
     * @param perPage Number of photos to return per page. The maximum allowed value is 1000
     * @param page The page of results to return
     * @return The contacts
     * @throws FlickrException Error getting the contacts
     */
    public Paginated<Contact> getContacts( int perPage, int page )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.contacts.getList" );
        args.addParam( "per_page", perPage );
        args.addParam( "page", page );
        return doGet( args, ContactsResponse.class ).getPaginated();
    }

    /**
     * Get the contact list for a user
     *
     * @param user The user to fetch the contact list for
     * @param perPage Number of photos to return per page. The maximum allowed value is 1000
     * @param page The page of results to return
     * @return The contacts
     * @throws FlickrException Error getting the contacts
     */
    public Paginated<Contact> getPublicContacts( BaseUser user, int perPage, int page )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.contacts.getPublicList" );
        args.addParam( "per_page", perPage );
        args.addParam( "page", page );
        args.addParam( "user_id", user.getId() );
        return doGet( args, ContactsResponse.class ).getPaginated();
    }

}
