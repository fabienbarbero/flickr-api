/*
 * Copyright (C) 2011 by Fabien Barbero
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
package com.flickr.api.contacts;

import com.flickr.api.entities.Contact;
import com.flickr.api.CommandArguments;
import com.flickr.api.FlickrServiceException;
import com.flickr.api.OAuthHandler;
import com.flickr.api.entities.BaseUser;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.PaginatedContactsResponse;
import com.flickr.api.FlickrService;
import org.apache.http.client.HttpClient;

/**
 *
 * @author Fabien Barbero
 */
public class ContactsServiceImpl extends FlickrService implements ContactsService {

    public ContactsServiceImpl(OAuthHandler oauthHandler, HttpClient client) {
        super(oauthHandler, client);
    }

    @Override
    public Paginated<Contact> getContacts(int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.contacts.getList", true);
        args.put("per_page", perPage);
        args.put("page", page);
        return doGet(args, PaginatedContactsResponse.class).getContacts();
    }

    @Override
    public Paginated<Contact> getPublicContacts(BaseUser user, int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.contacts.getPublicList", false);
        args.put("per_page", perPage);
        args.put("page", page);
        return doGet(args, PaginatedContactsResponse.class).getContacts();
    }
}
