package com.flickr.api.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils
{

    private JSONUtils()
    {
    }

    /**
     * Get a URL object from a String.
     * 
     * @param url The url as a String.
     * @return The URL object.
     */
    public static URL urlFromString(String url)
    {
        try {
            return new URL(url);
        } catch (MalformedURLException ex) {
            throw new UnsupportedOperationException(ex.getMessage(), ex);
        }
    }

    private static DateFormat dateFormat = DateFormat.getInstance();

    /**
     * Get a date from a String.
     * 
     * @param s The date as a String.
     * @return The date.
     */
    public static Date dateFromString(String s)
    {
        try {
            return dateFormat.parse(s);
            
        } catch (ParseException ex) {
            long date = Long.parseLong(s);
            return new Date(date * 1000);
        }
    }
    
    public static Date dateFromString2(String s)
    {
        long time = Long.valueOf(s);
        return new Date(time);
    }

    /**
     * Get a content value. Some values can be included in a sub json object.
     * For instance :
     * 
     * <pre>
     * "title":{"_content":"My Photoset"}
     * </pre>
     * 
     * @param json The json object to parse.
     * @param key The key of the content to get (in the premious example it will
     *        be "title").
     * @return The content.
     * @throws JSONException Parsing error.
     */
    public static String getContent(JSONObject json, String key) throws JSONException
    {
        return json.getJSONObject(key).getString("_content");
    }
    
    public static int getIntegerContent(JSONObject json, String key) throws JSONException
    {
        return Integer.parseInt(getContent(json, key));
    }

}
