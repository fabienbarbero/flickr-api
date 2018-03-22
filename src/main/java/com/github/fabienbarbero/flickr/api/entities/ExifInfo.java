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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents the exif information of a photo
 *
 * @author Fabien Barbero
 */
public class ExifInfo
        implements Serializable, Iterable<ExifEntry>
{

    private final String camera;
    private final List<ExifEntry> entries = new ArrayList<ExifEntry>();

    ExifInfo( JSONObject json )
            throws JSONException
    {
        camera = json.getString( "camera" );

        JSONArray array = json.getJSONArray( "exif" );
        for ( int i = 0; i < array.length(); i++ ) {
            entries.add( new ExifEntry( array.getJSONObject( i ) ) );
        }
    }

    /**
     * Get the camera name
     *
     * @return The camera name
     */
    public String getCamera()
    {
        return camera;
    }

    /**
     * Get the exif entries
     *
     * @return The entries
     */
    public List<ExifEntry> getEntries()
    {
        return entries;
    }

    /**
     * Get a exif entry
     *
     * @param tag The exif tag (see ExifEntry.TAG_*)
     * @return The entry or null if not present
     */
    public ExifEntry getEntry( String tag )
    {
        for ( ExifEntry entry : entries ) {
            if ( entry.getTag().equals( tag ) ) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public Iterator<ExifEntry> iterator()
    {
        return entries.iterator();
    }

}
