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
package com.github.fabienbarbero.flickr.api.entities;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class Stats
        implements Serializable
{

    private final int views;
    private final int comments;
    private final int favorites;

    Stats( JSONObject json )
            throws JSONException
    {
        views = json.getInt( "views" );
        comments = json.getInt( "comments" );
        favorites = json.getInt( "favorites" );
    }

    /**
     * Get the total views count by other users
     *
     * @return the views count
     */
    public int getViewsCount()
    {
        return views;
    }

    /**
     * Get the photo count added as favorites by other users
     *
     * @return The favorites count
     */
    public int getFavoritesCount()
    {
        return favorites;
    }

    /**
     * Get the comments count added by other users
     *
     * @return The comments count
     */
    public int getCommentsCount()
    {
        return comments;
    }

}
