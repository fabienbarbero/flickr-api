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
package easy.easy.flickr.api;

/**
 *
 * @author Fabien Barbero
 */
public class FlickrException
        extends Exception
{

    private static final long serialVersionUID = 1L;
    private final FlickrErrorCode errorCode;

    public FlickrException( String s, Throwable t, FlickrErrorCode errorCode )
    {
        super( s, t );
        this.errorCode = errorCode;
    }

    public FlickrException( String s, Throwable t )
    {
        super( s, t );
        errorCode = null;
    }

    public FlickrException( String s, FlickrErrorCode errorCode )
    {
        super( s );
        this.errorCode = errorCode;
    }

    public FlickrException( String s )
    {
        super( s );
        errorCode = null;
    }

    /**
     * Get the error code
     *
     * @return The error code or null
     */
    public final FlickrErrorCode getErrorCode()
    {
        return errorCode;
    }

}
