This app allows you to search for photos by image
meta tags like author, camera, tags. 
The service receives photos from a third-party service and stores them in its cache.
The cache is updated on its own in time.
The cache is built on the in-memory m2 database, which is created automatically when 
the application starts. You do not need to install any additional bases.
To build a jar-file, run the command mvn clean package
All application parameters are located in application.properties file.
The server starts by default on port 8090. 
scheduler.update.delay - time in mils for scheduled update cache.
api.key - secret key for third-party service
After starting the server, you can use a get request at endpoint: GET /search/${searchTerm}, 
that will return all the photos with any of the meta fields (author, camera, tags, etc) 
matching the search term.
IDs excluded for privacy.
Tests will be added recently.
