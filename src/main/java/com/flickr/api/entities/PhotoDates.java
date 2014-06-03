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
package com.flickr.api.entities;

import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import com.flickr.api.utils.JSONUtils;
import java.io.Serializable;

/**
 * Represents the dates of the photo
 *
 * @author Fabien Barbero
 */
public class PhotoDates
        implements Serializable
{

    private final Date posted;
    private final Date taken;
    private final int takengranularity;
    private final Date lastupdate;

    PhotoDates( JSONObject json )
            throws JSONException
    {
        posted = JSONUtils.dateFromString( json.getString( "posted" ) );
        taken = JSONUtils.dateFromString( json.getString( "taken" ) );
        takengranularity = json.getInt( "takengranularity" );
        lastupdate = JSONUtils.dateFromString( json.getString( "lastupdate" ) );
    }

    /**
     * Get the date of the last update of the photo.
     *
     * @return The date of the last update.
     */
    public Date getLastUpdateDate()
    {
        return lastupdate;
    }

    /**
     * Get the date when the photo has been posted
     *
     * @return The post date
     */
    public Date getPostedDate()
    {
        return posted;
    }

    /**
     * Get the date when the photo has been taken
     *
     * @return The taken date
     */
    public Date getTakenDate()
    {
        return taken;
    }

}
