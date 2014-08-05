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

/**
 *
 * @author Fabien Barbero
 */
public class Member
        implements BaseUser
{

    private final String id;
    private final String username;
    private final String realname;
    private final Avatar avatar;
    private final Type type;

    Member( JSONObject json )
            throws JSONException
    {
        id = json.getString( "nsid" );
        username = json.getString( "username" );
        realname = json.getString( "realname" );
        avatar = new Avatar( json, id );
        type = Type.fromValue( json.getInt( "membertype" ) );
    }

    /**
     * Get the member avatar
     *
     * @return The avatar
     */
    public Avatar getAvatar()
    {
        return avatar;
    }

    /**
     * Get the member type
     *
     * @return The type
     */
    public Type getType()
    {
        return type;
    }

    @Override
    public String getRealName()
    {
        return realname;
    }

    @Override
    public String getUserName()
    {
        return username;
    }

    @Override
    public String getId()
    {
        return id;
    }

    public enum Type
    {

        MEMBER( 2 ),
        MODERATOR( 3 ),
        ADMIN( 4 );
        //
        private final int value;

        private Type( int value )
        {
            this.value = value;
        }

        private static Type fromValue( int value )
        {
            for ( Type type : values() ) {
                if ( type.value == value ) {
                    return type;
                }
            }
            return null;
        }

    }

}
