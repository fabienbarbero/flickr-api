/*
 * Copyright (C) 2011 by Fabien Barbero
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

/**
 *
 * @author Fabien Barbero
 */
public enum FlickrErrorCode {
    
    not_found(1),
    general_upload_failure(3),
    filesize_was_zero(4),
    filetype_was_not_recognized(5),
    user_exceeded_upload_limit(6),
    invalid_signature(96),
    missing_signature(97),
    invalid_auth_token(98),
    user_not_logged_in(99),
    invalid_api_key(100),
    service_currently_unavailable(105),
    invalid_prob(108),
    format_not_found(111),
    method_not_found(112),
    bad_url_found(116);
    
    private int code;
    
    private FlickrErrorCode(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
    
    /**
     * Get the error code from an integer value.
     * @param code  The error code as an integer.
     * @return      The corresponding error code.
     */
    public static FlickrErrorCode fromCode(int code) {
        for(FlickrErrorCode flickrCode : values()) {
            if(flickrCode.getCode() == code) {
                return flickrCode;
            }
        }
        return null;    // Should never happen !
    }
    
}
