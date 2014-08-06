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
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import org.ez.flickr.api.utils.JSONUtils;

/**
 *
 * @author Fabien Barbero
 */
public class UserPhotosInfo
        implements Serializable
{

    private static final long serialVersionUID = 5602104420678151276L;
    private final Date firstDate;
    private final Date firstDateTaken;
    private final int count;

    UserPhotosInfo( JSONObject json )
            throws JSONException
    {
        firstDate = JSONUtils.dateFromString( JSONUtils.getContent( json, "firstdate" ) );
        firstDateTaken = JSONUtils.dateFromString( JSONUtils.getContent( json, "firstdatetaken" ) );
        count = JSONUtils.getIntegerContent( json, "count" );
    }

    /**
     * Get the number of photos of the user.
     *
     * @return The number of photos.
     */
    public int getCount()
    {
        return count;
    }

    /**
     * Get the date of the first photo publication.
     *
     * @return The first date of publication.
     */
    public Date getFirstDate()
    {
        return firstDate;
    }

    /**
     * Get the date when the user has taken his first photo
     *
     * @return The first taken date
     */
    public Date getFirstDateTaken()
    {
        return firstDateTaken;
    }

}
