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
package org.ez.flickr.api.entities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class PhotoPermissions
        extends JSONResponse
{

    private int ispublic;
    private int isfriend;
    private int isfamily;

    @Override
    protected void readObject( JSONObject json )
            throws JSONException
    {
        JSONObject permObj = json.getJSONObject( "perms" );
        ispublic = permObj.getInt( "ispublic" );
        isfriend = permObj.getInt( "isfriend" );
        isfamily = permObj.getInt( "isfamily" );
    }

    /**
     * Indicates if the photo has been taken by a member of the family of th user.
     *
     * @return true if the photo has been taken by a member of the family of th user.
     */
    public boolean isFamily()
    {
        return isfamily == 1;
    }

    /**
     * Indicates if the photo has been taken by a friend.
     *
     * @return true if the photo has been taken by a friend.
     */
    public boolean isFriend()
    {
        return isfriend == 1;
    }

    /**
     * Indicates if the photo is public.
     *
     * @return true if the photo is public.
     */
    public boolean isPublic()
    {
        return ispublic == 1;
    }

}
