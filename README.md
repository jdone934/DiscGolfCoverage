# DiscGolfCoverage

### Problem Statement

Disc Golf has recently been seeing a large explosion in popularity. In 2019 there were 6.643 disc golf courses in the United States alone, compared to 3,684 just five years earlier (an 80% increase). With this growth in popularity, has come a growing interest in Professional Disc Golf. Many professional tournaments have post-produced coverage that is usually posted to Youtube the day after that tournament round actually takes place. JomezPro is the largest Disc Golf coverage channel on Youtube and currently has 212,000 subscribers, with their most recent round of coverage reaching over 243,000 on the front 9.

While Youtube is a great place to host his coverage, there is a lack of a central hub for people to go is they are specifically looking for Disc Golf coverage. This makes finding historical coverage very hard, as you have to know what you're looking for in order to find it on Youtube. I aim to solve this problem with a website centered around aggregating Disc Golf coverage.

This website will allow users to easily find Disc Golf coverage based on whatever specific type of coverage they are looking for. This could mean being able to find coverage for a specific tournament or location from years past. They would also be able to search based on specific professional players. Given the large amount of coverage that is already out there, and the amount of coverage that is constantly comming in, I would also need to streamline the process of adding new coverage to the database. This would mean giving users the ability to recommend coverage they may not already see on the site. The website will also have an admin functionality to easily review these requests and add new coverage to our database.

Given that this website will be a hub for watching Disc Golf coverage, I also plan on creating some user amenities to make using the site a more enjoyable experience. Some of these features may include favoriting players, tournaments, or specific rounds of coverage; saving specific clips from a video to view later; compiling a list of "cool shots" from past tournaments.

### Project Technologies/Techniques 

* Security/Authentication
  * Tomcat's JDBC Realm Authentication
  * Admin role: create/read/update/delete (crud) of all data
  * User role: Favorite players/tournaments/rounds, report new rounds to add to website
  * All: anyone can search and find coverage results(no login)
* Database
  * MySQL
  * Store users and roles
  * Store all data for tournament coverage
* ORM Framework
  * Hibernate 5
* Dependency Management
  * Maven
* CSS 
  * Bootstrap
* Logging
  * Configurable logging using Log4J2. In production, only errors will normally be logged, but logging at a debug level can be turned on to facilitate trouble-shooting.
* Unit Testing
  * JUnit tests to achieve 80%+ code coverage 
* IDE
  * IntelliJ IDEA
* API
  * Youtube (to embed videos on website)

### Design

* [User Stories (Coming Soon)](#)
* [Screen Design (External Page to Moqups Project)]()
* [Application Flow (Coming Soon)](#)
* [Database Design (Coming Soon)]()
