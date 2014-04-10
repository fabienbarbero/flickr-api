/*
 * Copyright (C) 2013 Fabien Barbero
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
package com.flickr.api;

import com.flickr.api.utils.IOUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 
 * @author Fabien Barbero
 */
public class FlickrProperties {

    private final Properties props;
    private final File file;

    public FlickrProperties(File file) {
        this.props = new Properties();
        this.file = file;
    }

    final boolean contains(String key) {
        return props.containsKey(key);
    }

    final String getString(String key, String def) {
        return props.getProperty(key, def);
    }

    final void putString(String key, String value) {
        props.setProperty(key, value);
    }

    final void remove(String key) {
        props.remove(key);
    }

    final void load() {
        if (file.exists()) {
            InputStream is = null;
            try {
                is = getInputStream(file);
                props.load(is);

            } catch (IOException ex) {
                throw new UnsupportedOperationException("Error reading flickr properties", ex);
            } finally {
                IOUtils.closeQuietly(is);
            }
        }
    }

    final void commit() {
        OutputStream os = null;
        try {
            os = getOutputStream(file);
            props.store(os, "Flickr configuration");

        } catch (IOException ex) {
            throw new UnsupportedOperationException("Error saving configuration", ex);
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

    protected InputStream getInputStream(File file) throws IOException {
        return new FileInputStream(file);
    }

    protected OutputStream getOutputStream(File file) throws IOException {
        return new FileOutputStream(file);
    }
}
