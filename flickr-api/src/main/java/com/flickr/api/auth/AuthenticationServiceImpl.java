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
package com.flickr.api.auth;

import com.flickr.api.CommandArguments;
import com.flickr.api.FlickrServiceException;
import com.flickr.api.OAuthHandler;
import com.flickr.api.entities.BaseUser;
import com.flickr.api.entities.LoginResponse;
import com.flickr.api.entities.UserInfo;
import com.flickr.api.FlickrService;
import com.flickr.api.people.PeopleService;
import org.apache.http.client.HttpClient;

/**
 * http://www.flickr.com/services/api/auth.spec.html
 * http://www.flickr.com/services/api/auth.howto.web.html
 *
 * @author Fabien Barbero
 */
public class AuthenticationServiceImpl extends FlickrService implements AuthenticationService {

    private final PeopleService peopleService;

    public AuthenticationServiceImpl(OAuthHandler oauthHandler, HttpClient client, PeopleService peopleService) {
        super(oauthHandler, client);
        this.peopleService = peopleService;
    }

    @Override
    public UserInfo authenticate() throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.test.login");
        LoginResponse response = doGet(args, LoginResponse.class);
        BaseUser identifier = response.getIdentifier();

        return peopleService.getUserInfo(identifier);
    }
}
