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
public class PhotoStats
        implements Serializable
{

    private final Stats stats;
    private final Photo photo;

    public PhotoStats( JSONObject json )
            throws JSONException
    {
        stats = new Stats( json.getJSONObject( "stats" ) );
        photo = new Photo( json );
    }

    /**
     * Get the statistics
     *
     * @return The statistics
     */
    public Stats getStats()
    {
        return stats;
    }

    /**
     * Get the photo
     *
     * @return The photo
     */
    public Photo getPhoto()
    {
        return photo;
    }

}
