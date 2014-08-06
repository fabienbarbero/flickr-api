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

/**
 * Represents a user. It can be a contact, someone on a photo, the calling user ...
 *
 * @author Fabien Barbero
 */
public interface BaseUser
        extends IdObject
{

    /**
     * Get the real name of the user
     *
     * @return The real name
     */
    String getRealName();

    /**
     * Get the user name
     *
     * @return The user name
     */
    String getUserName();

}
