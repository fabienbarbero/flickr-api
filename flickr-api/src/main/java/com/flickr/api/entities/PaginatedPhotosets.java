package com.flickr.api.entities;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


class PaginatedPhotosets extends Paginated<Photoset>
{

    private final List<Photoset> photosets;
    
    PaginatedPhotosets(JSONObject json) throws JSONException {
        super(json);
        
        JSONArray array = json.getJSONArray("photoset");
        photosets = new ArrayList<Photoset>();
        for(int i=0; i<array.length(); i++) {
            photosets.add(new Photoset(array.getJSONObject(i)));
        }
    }

    @Override
    public List<Photoset> asList()
    {
        return photosets;
    }
    
}
