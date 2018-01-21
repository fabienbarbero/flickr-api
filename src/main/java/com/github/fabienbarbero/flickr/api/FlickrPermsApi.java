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


import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.model.OAuth1RequestToken;

/**
 *
 * @author Fabien Barbero
 */
class FlickrPermsApi
        extends FlickrApi
{

    private final String perms;

    FlickrPermsApi( String perms )
    {
        this.perms = perms;
    }

    @Override
    public String getAuthorizationUrl( OAuth1RequestToken requestToken )
    {
        return "https://www.flickr.com/services/oauth/authorize?perms=" + perms + "&oauth_token=" + requestToken.getToken();
    }

}
