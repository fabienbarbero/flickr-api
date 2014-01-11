Flickr API
==========

Flickr API is a Java / android implementation to access the Flickr web services defined [here](http://www.flickr.com/services/api/). The API uses only the REST-JSON requests to have the minimum payload size (which is useful with android device which do not have a high speed network access).

Getting started
===============
The API use the following dependencies:

 - [httpclient](http://hc.apache.org/downloads.cgi) to use the web services (not needed for android)
 - [httpmime](http://hc.apache.org/downloads.cgi) to use large data in httpclient
 - [json](http://mvnrepository.com/artifact/org.json/json/20090211) to read the web services responses (not needed for android)
 - [signpost](https://code.google.com/p/oauth-signpost/) to use the OAuth authentication
 
The calls to Flickr are quiet easy:

```java
Flickr flickr = new Flickr("my.api.key", "my.api.secret", new File("flickr.conf"));

// Check if a user is already logged
if(!flickr.isLogged()) {
    // Get the authorization URL to allow the application to use the Flickr services
    String url = flickr.getAuthorizationUrl("http://localhost");
    // Show the previous URL in a browser

    ...

    // Verify the token 
    String verifier = ...;
    String token = ...;
    flickr.verifyToken(verifier, token);
}

// Authenticate the user
UserInfo user = flickr.authenticate();

// Use the services
Paginated<Photo> photos = flickr.getPhotosService().getRecentlyUpdated(50, 0);
...
```
