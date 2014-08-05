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
package easy.easy.flickr.api;

import easy.flickr.api.entities.BaseUser;
import easy.flickr.api.entities.LoginResponse;

/**
 * http://www.flickr.com/services/api/auth.spec.html http://www.flickr.com/services/api/auth.howto.web.html
 *
 * @author Fabien Barbero
 */
final class AuthenticationService
        extends FlickrService
{

    AuthenticationService( OAuthHandler oauthHandler )
    {
        super( oauthHandler );
    }

    public BaseUser authenticate()
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.test.login" );
        LoginResponse response = doGet( args, LoginResponse.class );
        return response.getIdentifier();

    }

}
