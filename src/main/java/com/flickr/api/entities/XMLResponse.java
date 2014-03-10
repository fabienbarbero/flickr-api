/*
 * Copyright (C) 2014 Fabien Barbero
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights 
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.flickr.api.entities;

import com.flickr.api.FlickrErrorCode;
import com.flickr.api.FlickrException;
import com.flickr.api.ServerResponse;
import com.flickr.api.utils.XMLUtils;
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
public abstract class XMLResponse implements ServerResponse {

    @Override
    public final void read(String data, String method) throws FlickrException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(data)));
            
            Element rootElement = document.getDocumentElement();
            
            ResponseStatus status = ResponseStatus.valueOf(rootElement.getAttribute("stat"));
            
            if(status == ResponseStatus.fail) {
                Element errEl = XMLUtils.getChildElement(rootElement, "err");
                
                FlickrErrorCode code = FlickrErrorCode.fromCode(Integer.parseInt(errEl.getAttribute("code")));
                String message = errEl.getAttribute("msg");
                throw new FlickrException("Error calling method '" + method + "' (" + message + ")", code);
            }
            
            readObject(document);
            
        } catch (SAXException ex) {
            throw new FlickrException("Error parsing XML response", ex);
        } catch (IOException ex) {
            throw new FlickrException("Error parsing XML response", ex);
        } catch (ParserConfigurationException ex) {
            throw new FlickrException("Error parsing XML response", ex);
        }
    }
    
    protected abstract void readObject(Document document);

}
