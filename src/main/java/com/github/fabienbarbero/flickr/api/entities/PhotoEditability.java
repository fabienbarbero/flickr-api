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
public class PhotoEditability
        implements Serializable
{

    private final boolean canAddMedata;
    private final boolean canComment;

    PhotoEditability( JSONObject json )
            throws JSONException
    {
        canAddMedata = json.getInt( "canaddmeta" ) == 1;
        canComment = json.getInt( "cancomment" ) == 1;
    }

    /**
     * Indicates if a meta-data can be added to the photo
     *
     * @return true if meta-data can be added, false otherwise
     */
    public boolean canAddMedata()
    {
        return canAddMedata;
    }

    /**
     * Indicates if comments can be added to the photo
     *
     * @return true if comments can be added, false otherwise
     */
    public boolean canComment()
    {
        return canComment;
    }

}
