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

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class PhotoUsage
        implements Serializable
{

    private final int canDownload;
    private final int canBlog;
    private final int canPrint;
    private final int canShare;

    PhotoUsage( JSONObject json )
            throws JSONException
    {
        canDownload = json.getInt( "candownload" );
        canBlog = json.getInt( "canblog" );
        canPrint = json.getInt( "canprint" );
        canShare = json.getInt( "canshare" );
    }

    /**
     * Indicates if the photo can be added to a blog
     *
     * @return true if the photo can be added to a blog, false otherwise
     */
    public boolean canBlog()
    {
        return canBlog == 1;
    }

    /**
     * Indicates if the photo can be downloaded
     *
     * @return true if the photo can be downloaded, false otherwise
     */
    public boolean canDownload()
    {
        return canDownload == 1;
    }

    /**
     * Indicates if the photo can be printed
     *
     * @return true if the photo can be printed, false otherwise
     */
    public boolean canPrint()
    {
        return canPrint == 1;
    }

    /**
     * Indicates if the photo can be shared
     *
     * @return true if the photo can be shared, false otherwise
     */
    public boolean canShare()
    {
        return canShare == 1;
    }

}
