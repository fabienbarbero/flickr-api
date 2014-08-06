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

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class PhotoVisibility
        implements Serializable
{

    private final int isPublic;
    private final int isFriend;
    private final int isFamily;

    PhotoVisibility( JSONObject json )
            throws JSONException
    {
        isPublic = json.getInt( "ispublic" );
        isFriend = json.getInt( "isfriend" );
        isFamily = json.getInt( "isfamily" );
    }

    /**
     * Indicates if the photo has been taken by a member of the family of th user.
     *
     * @return true if the photo has been taken by a member of the family of th user.
     */
    public boolean isFamily()
    {
        return isFamily == 1;
    }

    /**
     * Indicates if the photo has been taken by a friend.
     *
     * @return true if the photo has been taken by a friend.
     */
    public boolean isFriend()
    {
        return isFriend == 1;
    }

    /**
     * Indicates if the photo is public.
     *
     * @return true if the photo is public.
     */
    public boolean isPublic()
    {
        return isPublic == 1;
    }

}
