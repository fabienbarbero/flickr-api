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
package org.ez.flickr.api.utils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Fabien Barbero
 */
public class XMLUtils
{

    private XMLUtils()
    {
    }

    public static Element getChildElement( Element parent, String childName )
    {
        NodeList list = parent.getElementsByTagName( childName );

        Node node;
        for ( int i = 0; i < list.getLength(); i++ ) {
            node = list.item( i );
            if ( node.getNodeType() == Node.ELEMENT_NODE ) {
                return ( Element ) node;
            }
        }
        return null;
    }

}
