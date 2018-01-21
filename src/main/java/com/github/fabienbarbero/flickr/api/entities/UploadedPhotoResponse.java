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

import com.github.fabienbarbero.flickr.api.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Fabien Barbero
 */
public class UploadedPhotoResponse
        extends XMLResponse
{

    private UploadedPhoto photoId;

    @Override
    protected void readObject( Document document )
    {
        Element photoEl = XMLUtils.getChildElement( document.getDocumentElement(), "photoid" );
        photoId = new UploadedPhoto( photoEl.getTextContent() );
    }

    public UploadedPhoto getPhoto()
    {
        return photoId;
    }

}
