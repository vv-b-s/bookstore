# Application Deployment

## 1. Configuring the database

This application works with Microsoft SQL server.
If you want to run it, you need to provide the following properties:

* `sqlserver.host` - the IP address of the SQL server instance
* `sqlserver.port` - the port to which the server is hosted
* `sqlserver.database` - the database where the bookstore entities will be persisted
* `sqlserver.user` - the username for the database
* `sqlserver.password` - the password for the database
* `google.api.key` - the API key used for Google Books

## 2. Compilation and execution

### Prerequisites

* JDK 8 - https://adoptopenjdk.net/
* Apache Maven 3 - https://maven.apache.org/
* GOOGLE API Key for Google Books

### Deployment

To compile and deploy the application run:

`
mvn -Dsqlserver.host=<yourDBHost> -Dsqlserver.port=<yourDBPort> -Dsqlserver.database=<yourDB> -Dsqlserver.user=<yourDbUser> -Dsqlserver.password=<yourDBPassword> -Dgoogle.api.key=<googleApiKey> wildfly:run
`

To check if server is working visit: `http://localhost:8008/rest/hello`

# SQL Scripts
After initial execution you can insert initial data to the database, located in `/sq