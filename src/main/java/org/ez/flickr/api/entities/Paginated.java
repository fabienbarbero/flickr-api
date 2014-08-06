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

import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public final class Paginated<T>
        implements Iterable<T>
{

    private final int page;
    private final int pages;
    private final int perpage;
    private final int total;
    private final List<T> content;

    protected Paginated( JSONObject json, List<T> content )
            throws JSONException
    {
        page = json.getInt( "page" );
        pages = json.getInt( "pages" );
        if ( json.has( "per_page" ) ) {
            perpage = json.getInt( "per_page" );
        } else {
            perpage = json.getInt( "perpage" );
        }
        total = json.getInt( "total" );
        this.content = content;
    }

    /**
     * Get the current page.
     *
     * @return The index of the page.
     */
    public int getPageIndex()
    {
        return page;
    }

    /**
     * Get the number of pages.
     *
     * @return The number of pages.
     */
    public int getPagesCount()
    {
        return pages;
    }

    /**
     * Get the number of objects per page.
     *
     * @return The number of objects per page.
     */
    public int getPerPage()
    {
        return perpage;
    }

    /**
     * Get the total number of available objects.
     *
     * @return The number of objects.
     */
    public int getTotalCount()
    {
        return total;
    }

    /**
     * Indicates if the list is empty
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return total == 0;
    }

    /**
     * Get the paginated values.
     *
     * @return The values.
     */
    public List<T> asList()
    {
        return content;
    }

    @Override
    public Iterator<T> iterator()
    {
        return content.iterator();
    }

    /**
     * Get a value at a given index
     *
     * @param index The index
     * @return The value
     */
    public T get( int index )
    {
        return content.get( index );
    }

}
