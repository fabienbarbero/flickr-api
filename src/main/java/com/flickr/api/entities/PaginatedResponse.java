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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public abstract class PaginatedResponse<T> extends JSONResponse {

    private Paginated<T> value;

    @Override
    protected final void readObject(JSONObject json) throws JSONException {
        List<T> values = new ArrayList<T>();

        JSONObject parent = find(json, JSONObject.class);
        JSONArray array = find(parent, JSONArray.class);
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                values.add(unmarshall(array.getJSONObject(i)));
            }
        }

        value = new Paginated<T>(parent, values);
    }

    private <T> T find(JSONObject json, Class<T> clazz) throws JSONException {
        Iterator<String> it = json.keys();
        while (it.hasNext()) {
            Object obj = json.get(it.next());
            if (clazz.isInstance(obj)) {
                return (T) obj;
            }
        }
        return null;
    }

    protected abstract T unmarshall(JSONObject json) throws JSONException;

    public final Paginated<T> getPaginated() {
        return value;
    }

}
