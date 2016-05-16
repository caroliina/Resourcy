# Resourcy

This application is for managing and exporting a companies employees cv's.

The application was generated using JHipster, you can find documentation and help at [https://jhipster.github.io](https://jhipster.github.io).

This is a Spring Boot application which means that we have embedded Tomcat and there is no need for war deployment. Spring Boot dependency is included in our pom.xml and does not need to be installed separately.

This project uses `JDK 1.8` and at least `Maven 3.0` is required.

# Building

To run this application locally on your machine, first you need to create a new folder and then clone this repository.

Before you can build this project, you must install and configure the following dependencies on your machine. These commands must be run in the previously created folder.

1. [Node.js][]: We use Node to run a development web server and to build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools (like
[Bower][] and [BrowserSync][]). You will only need to run this command when dependencies change in package.json.

    npm install

We use [Grunt][] as our build system. Install the grunt command-line tool globally with:

    npm install -g grunt-cli

Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

    mvn
    grunt

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

# Profiles

There are two profiles for running the application:
* `dev` for development which focuses on ease of development and productivity
* `prod` for production which focuses on performance and scalability

The Maven profiles are used at build time: `mvn -Pprod package` will package a production application (in order to have an executable WAR file).

By default, `dev` profile will be used. You can run this application in production directly using Maven: `mvn -Pprod`

When running production application use:
    
    ./java -jar jhipster-0.0.1-SNAPSHOT.war --spring.profiles.active=prod

There are also two additional Spring profiles used as switches and can be used with Maven as follows:
* `mvn -Pprod "-Drun.profiles=no-liquibase"` - disables Liquibase
* `mvn -Pprod "-Drun.profiles=no-swagger"` - disables Swagger

# Building for production

To optimize the Resourcy client for production, run:

    mvn -Pprod clean package

This will concatenate and minify CSS and JavaScript files. It will also modify `index.html` so it references
these new files.

To ensure everything worked, run:

    java -jar target/*.war --spring.profiles.active=prod

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

# Database

For development H2 database is used. This means you have an in-memory databse running inside your application, and it can be accessed from [http://localhost:8080/h2-console](http://localhost:8080/h2-console) by default. For production PostgreSQL database is being used.

If you add or modify a JPA entity, you will need to update your database schema. Liquibase manages the database updates and stores its configuration in the `/src/main/resources/config/liquibase/` directory.

# Integrated development environment

All team members used `Intellij IDEA` for development. To import this project there should be nothing more to do than just import it - Maven should be detected and the project will build automatically.

# Suggestions

1. Right-click on `src/main/webapp/bower_components` and "Mark Directory As" "Excluded"
2. You should also exclude `.tmp/`, `node_modules/` and `src/main/webapp/dist`
3. To access our Swagger generated interactive API documentation go to [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

# Continuous Integration

To setup this project in Jenkins, use the following configuration:

* Project name: `Resourcy`
* Source Code Management
    * Git Repository: `git@github.com:xxxx/Resourcy.git`
    * Branches to build: `*/master`
    * Additional Behaviours: `Wipe out repository & force clone`
* Build Triggers
    * Poll SCM / Schedule: `H/5 * * * *`
* Build
    * Invoke Maven / Tasks: `-Pprod clean package`
* Post-build Actions
    * Publish JUnit test result report / Test Report XMLs: `build/test-results/*.xml`

[JHipster]: https://jhipster.github.io/
[Node.js]: https://nodejs.org/
[Bower]: http://bower.io/
[Grunt]: http://gruntjs.com/
[BrowserSync]: http://www.browsersync.io/
[Karma]: http://karma-runner.github.io/
[Jasmine]: http://jasmine.github.io/2.0/introduction.html
[Protractor]: https://angular.github.io/protractor/
