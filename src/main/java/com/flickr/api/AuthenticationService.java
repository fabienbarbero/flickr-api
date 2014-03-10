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
package com.flickr.api;

import com.flickr.api.entities.BaseUser;
import com.flickr.api.entities.LoginResponse;
import com.flickr.api.entities.UserInfos;

/**
 * http://www.flickr.com/services/api/auth.spec.html
 * http://www.flickr.com/services/api/auth.howto.web.html
 *
 * @author Fabien Barbero
 */
public final class AuthenticationService extends FlickrService {

    private final PeopleService peopleService;

    AuthenticationService(OAuthHandler oauthHandler, PeopleService peopleService) {
        super(oauthHandler);
        this.peopleService = peopleService;
    }

    public UserInfos authenticate() throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.test.login");
        LoginResponse response = doGet(args, LoginResponse.class);
        BaseUser identifier = response.getIdentifier();

        return peopleService.getUserInfo(identifier);
    }
}
