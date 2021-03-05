Coverage: 78%
# IMS-Complete (NJ version 2021-01-21)

**QA internal use only**

Definitive completed version of SOFT/SDET project IMS - for use from 20DecSDET2 onwards.

## Version History

* 2021-01-22 - v1.0.1 - added Sonar Scanner for Maven to reduce the number of command-line arguments
* 2021-01-21 - v1.0.0 - currently uses an embedded H2 (with `db.properties`), JUnit4 & Mockito

## Requirements

* Java 8+
* Maven (locally installed)
* MySQL 8.0+

## Usage during training

* MySQL - Use the SQL provided in `src/main/resources` to create a local db in the MySQL side. When testing, this will be automatically replicated in the internal H2.
* JARs - Generate a fat jar in `target` via a `mvn clean package`.
* Sonar - Send to SonarQube with `mvn sonar:sonar`, editable in the `sonar` profile in the `pom.xml`.

## Dependencies

* [H2](https://h2database.com) - Embedded DBMS for testing
* [JDBC](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/) - Database Management via Java
* [JUnit4](https://junit.org/junit4/) - Unit Testing
* [Mockito](https://site.mockito.org/) - Integration Testing
* [EqualsVerifier](https://jqno.nl/equalsverifier/) - Domain class validation
* [Log4J](https://logging.apache.org/log4j/2.x/) - Logging
* [Jacoco](https://docs.sonarqube.org/display/SONARQUBE45/JaCoCo+Plugin) - SonarQube test coverage plugin
* [Sonar Scanner for Maven](http://sonarsource.github.io/sonar-scanner-maven/) - SonarQube Maven plugin

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Jordan Harrison** - *Significant refinements* - [jharry444](https://github.com/jharry444)
* **Nick Johnson** - *Furhter refinements* - [nickrstewarttds](https://github.com/nickrstewarttds)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details.