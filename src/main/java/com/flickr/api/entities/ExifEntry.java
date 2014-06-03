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
package com.flickr.api.entities;

import com.flickr.api.utils.JSONUtils;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents an exif entry for a photo
 *
 * @author Fabien Barbero
 */
public class ExifEntry
        implements Serializable
{

    public static final String TAG_MAKE = "Make";
    public static final String TAG_MODEL = "Model";
    public static final String TAG_ORIENTATION = "Orientation";
    public static final String TAG_X_RESOLUTION = "XResolution";
    public static final String TAG_Y_RESOLUTION = "YResolution";
    public static final String TAG_SOFTWARE = "Software";
    public static final String TAG_MODIFY_DATE = "ModifyDate";
    public static final String TAG_EXPOSURE_TIME = "ExposureTime";
    public static final String TAG_APERTURE = "FNumber";
    public static final String TAG_EXPOSURE_PROGRAM = "ExposureProgram";
    public static final String TAG_ISO = "ISO";
    public static final String TAG_EXIF_VERSION = "ExifVersion";
    public static final String TAG_DATE_TIME_ORIGINAL = "DateTimeOriginal";
    public static final String TAG_CREATE_DATE = "CreateDate";
    public static final String TAG_EXPOSURE_COMPENSATION = "ExposureCompensation";
    public static final String TAG_MAX_EXPOSURE_VALUE = "MaxApertureValue";
    public static final String TAG_SUBJECT_DISTANCE = "SubjectDistance";
    public static final String TAG_METERING_MODE = "MeteringMode";
    public static final String TAG_LIGHT_SOURCE = "LightSource";
    public static final String TAG_FLASH = "Flash";
    public static final String TAG_FOCAL_LENGTH = "FocalLength";
    public static final String TAG_SUB_SEC_TIME = "SubSecTime";
    public static final String TAG_SUB_SEC_TIME_ORIGINAL = "SubSecTimeOriginal";
    public static final String TAG_SUB_SEC_TIME_DIGITALIZED = "SubSecTimeDigitized";
    public static final String TAG_COLOR_SPACE = "ColorSpace";
    public static final String TAG_SENSING_METHOD = "SensingMethod";
    public static final String TAG_FILE_SOURCE = "FileSource";
    public static final String TAG_SCENE_TYPE = "SceneType";
    public static final String TAG_CFA_PATTERN = "CFAPattern";
    public static final String TAG_CUSTOM_RENDERED = "CustomRendered";
    public static final String TAG_EXPOSURE_MODE = "ExposureMode";
    public static final String TAG_WHITE_BALANCE = "WhiteBalance";
    public static final String TAG_DIGITAL_ZOOM_RATIO = "DigitalZoomRatio";
    public static final String TAG_FOCAL_LENGTH_35MM_FORMAT = "FocalLengthIn35mmFormat";
    public static final String TAG_SCENE_CAPTURE_TYPE = "SceneCaptureType";
    public static final String TAG_GAIN_CONTROL = "GainControl";
    public static final String TAG_CONTRAST = "Contrast";
    public static final String TAG_SATURATION = "Saturation";
    public static final String TAG_SHARPNESS = "Sharpness";
    public static final String TAG_SUBJECT_DISTANCE_RANGE = "SubjectDistanceRange";
    public static final String TAG_LENS_INFO = "LensInfo";
    public static final String TAG_LENS_MODEL = "LensModel";
    public static final String TAG_COMPRESSION = "Compression";
    public static final String TAG_RESOLUTION_UNIT = "ResolutionUnit";
    public static final String TAG_THUMBNAIL_OFFSET = "ThumbnailOffset";
    public static final String TAG_THUMBNAIL_LENGTH = "ThumbnailLength";
    public static final String TAG_CODED_CHARSET = "CodedCharacterSet";
    public static final String TAG_APPLICATION_RECORD_VERSION = "ApplicationRecordVersion";
    public static final String TAG_DATE_CREATED = "DateCreated";
    public static final String TAG_TIME_CREATED = "TimeCreated";
    public static final String TAG_IPTC_DIGEST = "IPTCDigest";
    public static final String TAG_DISPLAY_UNITS_X = "DisplayedUnitsX";
    public static final String TAG_DISPLAY_UNITS_Y = "DisplayedUnitsY";
    public static final String TAG_GLOBAL_ANGLE = "GlobalAngle";
    public static final String TAG_GLOBAL_ALTITUDE = "GlobalAltitude";
    public static final String TAG_PHOTOSHOP_THUMBNAIL = "PhotoshopThumbnail";
    public static final String TAG_PHOTOSHOP_FORMAT = "PhotoshopFormat";
    public static final String TAG_PROGRESSIVE_SCANS = "ProgressiveScans";
    public static final String TAG_XMP_TOOLKIT = "XMPToolkit";
    public static final String TAG_CREATOR_TOOL = "CreatorTool";
    public static final String TAG_RATING = "Rating";
    public static final String TAG_METADATA_DATE = "MetadataData";
    public static final String TAG_LENS = "Lens";
    public static final String TAG_LENS_ID = "LensID";
    public static final String TAG_IMAGE_NUMBER = "ImageNumber";
    public static final String TAG_APPROXIMATE_FOCUS_DISTANCE = "ApproximateFocusDistance";
    public static final String TAG_COLOR_MODE = "ColorMode";
    public static final String TAG_DOCUMENT_ID = "DocumentID";
    public static final String TAG_DOCUMENT_ID_ORIGINAL = "OriginalDocumentID";
    public static final String TAG_INSTANCE_ID = "InstanceID";
    public static final String TAG_HISTORY_ACTION = "HistoryAction";
    public static final String TAG_HISTORY_INSTANCE_ID = "HistoryInstanceID";
    public static final String TAG_HISTORY_WHEN = "HistoryWhen";
    public static final String TAG_HISTORY_SOFTWARE_AGENT = "HistorySoftwareAgent";
    public static final String TAG_HISTORY_CHANGED = "HistoryChanged";
    public static final String TAG_HISTORY_PARAMETERS = "HistoryParameters";
    public static final String TAG_DERIVED_FROM_INSTANCE_ID = "DerivedFromInstanceID";
    public static final String TAG_DERIVED_FROM_DOCUMENT_ID = "DerivedFromDocumentID";
    public static final String TAG_DERIVED_FROM_ORIGINAL_DOCUMENT_ID = "DerivedFromOriginalDocumentID";
    public static final String TAG_FORMAT = "Format";
    public static final String TAG_DCT_ENCODED_VERSION = "DCTEncodeVersion";
    public static final String TAG_APP14_FLAGS0 = "APP14Flags0";
    public static final String TAG_APP14_FLAGS1 = "APP14Flags1";
    public static final String TAG_COLOR_TRANSFORM = "ColorTransform";
    //
    private final String label;
    private final String raw;
    private final String tag;
    private final String clean;

    ExifEntry( JSONObject json )
            throws JSONException
    {
        label = json.getString( "label" );
        raw = JSONUtils.getContent( json, "raw" );
        tag = json.getString( "tag" );
        if ( json.has( "clean" ) ) {
            clean = JSONUtils.getContent( json, "clean" );
        } else {
            clean = null;
        }
    }

    /**
     * Get the "human readable" value of the entry
     *
     * @return The value or null
     */
    public String getClean()
    {
        return clean;
    }

    /**
     * Get the exif label
     *
     * @return the label
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * Get the "raw" value of the entry
     *
     * @return The raw value
     */
    public String getRaw()
    {
        return raw;
    }

    /**
     * Get the exif tag (see the contants TAG_*)
     *
     * @return The tag
     */
    public String getTag()
    {
        return tag;
    }

    @Override
    public String toString()
    {
        if ( clean != null ) {
            return clean;
        }
        return raw;
    }

}
