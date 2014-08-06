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

import org.ez.flickr.api.FlickrErrorCode;
import org.ez.flickr.api.FlickrException;
import org.ez.flickr.api.ServerResponse;
import org.ez.flickr.api.utils.XMLUtils;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Fabien Barbero
 */
public abstract class XMLResponse
        implements ServerResponse
{

    @Override
    public final void read( String data, String method )
            throws FlickrException
    {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse( new InputSource( new StringReader( data ) ) );

            Element rootElement = document.getDocumentElement();

            ResponseStatus status = ResponseStatus.valueOf( rootElement.getAttribute( "stat" ) );

            if ( status == ResponseStatus.fail ) {
                Element errEl = XMLUtils.getChildElement( rootElement, "err" );

                FlickrErrorCode code = FlickrErrorCode.fromCode( Integer.parseInt( errEl.getAttribute( "code" ) ) );
                String message = errEl.getAttribute( "msg" );
                throw new FlickrException( "Error calling method '" + method + "' (" + message + ")", code );
            }

            readObject( document );

        } catch ( SAXException ex ) {
            throw new FlickrException( "Error parsing XML response: " + data, ex );
        } catch ( IOException ex ) {
            throw new FlickrException( "Error parsing XML response: " + data, ex );
        } catch ( ParserConfigurationException ex ) {
            throw new FlickrException( "Error parsing XML response: " + data, ex );
        }
    }

    protected abstract void readObject( Document document );

}
