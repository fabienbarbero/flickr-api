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
package easy.flickr.api.entities;

import org.json.JSONException;
import org.json.JSONObject;
import easy.flickr.api.utils.JSONUtils;

public class LoginResponse
        extends JSONResponse
{

    private BaseUser identifier;

    @Override
    protected void readObject( JSONObject json )
            throws JSONException
    {
        identifier = new LoginUser( json.getJSONObject( "user" ) );
    }

    public BaseUser getIdentifier()
    {
        return identifier;
    }

    private static class LoginUser
            implements BaseUser
    {

        private final String username;
        private final String id;

        public LoginUser( JSONObject json )
                throws JSONException
        {
            id = json.getString( "id" );
            username = JSONUtils.getContent( json, "username" );
        }

        @Override
        public String getId()
        {
            return id;
        }

        @Override
        public String getRealName()
        {
            return username;
        }

        @Override
        public String getUserName()
        {
            return username;
        }

    }

}
