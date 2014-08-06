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
package org.ez.flickr.api;

import org.ez.flickr.api.entities.BasePhoto;
import java.util.List;
import org.ez.flickr.api.entities.BaseUser;
import org.ez.flickr.api.entities.Comment;
import org.ez.flickr.api.entities.CommentResponse;
import org.ez.flickr.api.entities.CommentsResponse;
import org.ez.flickr.api.entities.ExifInfos;
import org.ez.flickr.api.entities.ExifInfosResponse;
import org.ez.flickr.api.entities.License;
import org.ez.flickr.api.entities.LicensesResponse;
import org.ez.flickr.api.entities.Paginated;
import org.ez.flickr.api.entities.PhotosResponse;
import org.ez.flickr.api.entities.Photo;
import org.ez.flickr.api.entities.PhotoPermissions;
import org.ez.flickr.api.entities.PhotoInfos;
import org.ez.flickr.api.entities.PhotoInfosResponse;
import org.ez.flickr.api.entities.PhotoSize;
import org.ez.flickr.api.entities.PhotoSizesResponse;
import org.ez.flickr.api.entities.PhotoTag;
import org.ez.flickr.api.entities.VoidResponse;

/**
 * Service used to access the photos.
 *
 * @author Fabien Barbero
 */
public class PhotosService
        extends FlickrService
{

    PhotosService( OAuthHandler oauthHandler )
    {
        super( oauthHandler );
    }

    /**
     * Fetch a list of recent photos from the calling users' contacts.
     *
     * @return The photos
     * @throws FlickrException Error getting the photos
     */
    public Paginated<Photo> getContactsPhotos()
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.getContactsPhotos" );
        return doGet( args, PhotosResponse.class ).getPaginated();
    }

    /**
     * Fetch a list of recent photos from the calling users' contacts.
     *
     * @param count Number of photos to return
     * @param justFriends To only show photos from friends and family (excluding regular contacts).
     * @param singlePhoto Only fetch one photo (the latest) per contact, instead of all photos in chronological order.
     * @param includeSelf To include photos from the user specified by user_id.
     * @return The photos
     * @throws FlickrException Error getting the photos
     */
    public Paginated<Photo> getContactsPhotos( int count, boolean justFriends, boolean singlePhoto, boolean includeSelf )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.getContactsPhotos" );
        args.addParam( "count", count );
        args.addParam( "just_friends", justFriends );
        args.addParam( "single_photo", singlePhoto );
        args.addParam( "include_self", includeSelf );
        return doGet( args, PhotosResponse.class ).getPaginated();
    }

    /**
     * Fetch a list of recent public photos from a users' contacts.
     *
     * @param user The user to fetch photos for
     * @return The photos
     * @throws FlickrException Error getting the photos
     */
    public Paginated<Photo> getContactsPublicPhotos( BaseUser user )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.getContactsPublicPhotos" );
        args.addParam( "user_id", user.getId() );
        return doGet( args, PhotosResponse.class ).getPaginated();
    }

    /**
     * Fetch a list of recent public photos from a users' contacts.
     *
     * @param user The user to fetch photos for
     * @param count Number of photos to return
     * @param justFriends To only show photos from friends and family (excluding regular contacts).
     * @param singlePhoto Only fetch one photo (the latest) per contact, instead of all photos in chronological order.
     * @param includeSelf To include photos from the user specified by user_id.
     * @return The photos
     * @throws FlickrException Error getting the photos
     */
    public Paginated<Photo> getContactsPublicPhotos( BaseUser user, int count, boolean justFriends, boolean singlePhoto, boolean includeSelf )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.getContactsPublicPhotos" );
        args.addParam( "user_id", user.getId() );
        args.addParam( "count", count );
        args.addParam( "just_friends", justFriends );
        args.addParam( "single_photo", singlePhoto );
        args.addParam( "include_self", includeSelf );
        return doGet( args, PhotosResponse.class ).getPaginated();
    }

    /**
     * Get information about a photo. The calling user must have permission to view the photo.
     *
     * @param photo The photo
     * @return The photo informations
     * @throws FlickrException Error getting the informations
     */
    public PhotoInfos getInfos( BasePhoto photo )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.getInfo" );
        args.addParam( "photo_id", photo.getId() );
        return doGet( args, PhotoInfosResponse.class ).getInfos();
    }

    /**
     * Get permissions for a photo.
     *
     * @param photo The photo
     * @return The photo permissions
     * @throws FlickrException Error getting the permissions
     */
    public PhotoPermissions getPermissions( BasePhoto photo )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.getPerms" );
        args.addParam( "photo_id", photo.getId() );
        return doGet( args, PhotoPermissions.class );
    }

    /**
     * Returns a list of the latest public photos uploaded to flickr.
     *
     * @param perPage Number of photos to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The recent photos
     * @throws FlickrException Error getting the photos
     */
    public Paginated<Photo> getRecent( int perPage, int page )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.getRecent" );
        args.addParam( "per_page", perPage );
        args.addParam( "page", page );
        Paginated<Photo> photos = doGet( args, PhotosResponse.class ).getPaginated();
        return photos;
    }

    /**
     * Returns the available sizes for a photo. The calling user must have permission to view the photo.
     *
     * @param photo The photo
     * @return The photo sizes
     * @throws FlickrException Error getting the sizes
     */
    public List<PhotoSize> getSizes( BasePhoto photo )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.getSizes" );
        args.addParam( "photo_id", photo.getId() );
        List<PhotoSize> sizes = doGet( args, PhotoSizesResponse.class ).getList();
        return sizes;
    }

    /**
     * Return a list of your photos that have been recently created or which have been recently modified. Recently
     * modified may mean that the photo's metadata (title, description, tags) may have been changed or a comment has
     * been added (or just modified somehow :-)
     *
     * @param perPage Number of photos to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The photos
     * @throws FlickrException Error getting the photos
     */
    public Paginated<Photo> getRecentlyUpdated( int perPage, int page )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.recentlyUpdated" );
        args.addParam( "per_page", perPage );
        args.addParam( "page", page );
        args.addParam( "extras", "date_upload" );
        args.addParam( "min_date", "10000" );
        Paginated<Photo> photos = doGet( args, PhotosResponse.class ).getPaginated();
        return photos;
    }

    /**
     * Retrieves a list of EXIF/TIFF/GPS tags for a given photo. The calling user must have permission to view the
     * photo.
     *
     * @param photo The photo
     * @return The exif informations
     * @throws FlickrException Error getting the exif informations
     */
    public ExifInfos getExif( BasePhoto photo )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.getExif" );
        args.addParam( "photo_id", photo.getId() );
        return doGet( args, ExifInfosResponse.class ).getExifInfos();
    }

    /**
     * Fetches a list of available photo licenses for Flickr.
     *
     * @return The licenses
     * @throws FlickrException Error getting the licenses
     */
    public List<License> getLicenses()
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.licenses.getInfo" );
        return doGet( args, LicensesResponse.class ).getList();
    }

    /**
     * Returns the comments for a photo
     *
     * @param photo The photo
     * @return The comments
     * @throws FlickrException Error getting the comments
     */
    public List<Comment> getComments( BasePhoto photo )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.comments.getList" );
        args.addParam( "photo_id", photo.getId() );
        return doGet( args, CommentsResponse.class ).getList();
    }

    /**
     * Delete a photo from flickr
     *
     * @param photo The photo to delete
     * @throws FlickrException Error deleting the photo
     */
    public void deletePhoto( BasePhoto photo )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.delete" );
        args.addParam( "photo_id", photo.getId() );
        doPost( args, VoidResponse.class );
    }

    /**
     * Set the tags for a photo.
     *
     * @param photo The photo to set the tags
     * @param tags The tags list
     * @throws FlickrException Error setting the tags
     */
    public void setTags( BasePhoto photo, String... tags )
            throws FlickrException
    {
        StringBuilder tagsBuilder = new StringBuilder();
        for ( String tag : tags ) {
            if ( tag.contains( " " ) ) {
                tagsBuilder.append( "\"" ).append( tag ).append( "\"" );
            } else {
                tagsBuilder.append( tag );
            }
            tagsBuilder.append( " " );
        }

        CommandArguments args = new CommandArguments( "flickr.photos.setTag" );
        args.addParam( "photo_id", photo.getId() );
        args.addParam( "tags", tagsBuilder );
        doPost( args, VoidResponse.class );
    }

    /**
     * Remove a tag on a photo
     *
     * @param tag The tag to remove
     * @throws FlickrException Error removing tag
     */
    public void removeTag( PhotoTag tag )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.removeTag" );
        args.addParam( "tag_id", tag.getId() );
        doPost( args, VoidResponse.class );
    }

    /**
     * Set the meta information for a photo.
     *
     * @param photo The photo to modify
     * @param title The new photo title
     * @param description The new photo description
     * @throws FlickrException Error updating the photo meta
     */
    public void setPhotoMeta( BasePhoto photo, String title, String description )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.setMeta" );
        args.addParam( "photo_id", photo.getId() );
        args.addParam( "title", title );
        args.addParam( "description", description );
        doPost( args, VoidResponse.class );
    }

    /**
     * Set permissions for a photo.
     *
     * @param photo The photo to modify the permissions
     * @param isPublic true to set the photo public, false otherwise
     * @param isFriend true to set the photo accessible for friends, false otherwise
     * @param isFamily true to set the photo accessible for family, false otherwise
     * @param commentsPerms Comments permissions
     * @param addMetaPerms Meta add permissions
     * @throws FlickrException Error setting the permissions
     */
    public void setPhotoPermissions( BasePhoto photo, boolean isPublic, boolean isFriend, boolean isFamily, Permission commentsPerms, Permission addMetaPerms )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.setPerms" );
        args.addParam( "photo_id", photo.getId() );
        args.addParam( "is_public", isPublic );
        args.addParam( "is_friend", isFriend );
        args.addParam( "is_family", isFamily );
        args.addParam( "perm_comment", commentsPerms.value );
        args.addParam( "perm_addmeta", addMetaPerms.value );
        doPost( args, VoidResponse.class );
    }

    /**
     * Add a comment to a photo
     *
     * @param photo The photo
     * @param text The comment to add
     * @return The comment
     * @throws FlickrException Error adding the comment
     */
    public Comment addPhotoComment( BasePhoto photo, String text )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.comments.addComment" );
        args.addParam( "photo_id", photo.getId() );
        args.addParam( "comment_text", text );
        return doPost( args, CommentResponse.class ).getComment();
    }

    /**
     * Delete a comment
     *
     * @param comment The comment to delete
     * @throws FlickrException Error deleting the comment
     */
    public void deleteComment( Comment comment )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.comments.deleteComment" );
        args.addParam( "comment_id", comment.getId() );
        doPost( args, VoidResponse.class );
    }

    /**
     * Edit the text of a comment as the currently authenticated user.
     *
     * @param comment The comment to update
     * @param text The new comment text
     * @throws FlickrException Error editing the comment
     */
    public void editComment( Comment comment, String text )
            throws FlickrException
    {
        CommandArguments args = new CommandArguments( "flickr.photos.comments.editComment" );
        args.addParam( "comment_id", comment.getId() );
        args.addParam( "comment_text", text );
        doPost( args, VoidResponse.class );
    }

    public enum Permission
    {

        JUST_OWNER( 0 ),
        FRIENDS_FAMILY( 1 ),
        CONTACTS( 2 ),
        EVERYBODY( 3 );

        private final int value;

        private Permission( int value )
        {
            this.value = value;
        }

    }

}
