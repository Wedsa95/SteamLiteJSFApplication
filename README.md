# SteamLiteJSFApplication
The working part of this Web Application is just the login part.Because the password is salted and hashed after every the best way to create a user is just to sign up. That you can find localhost:portNumber/SteamLiteWebApplication/faces/signup.xhtml.
# Kavzor
Som sagt så har jag inte blivi färdig till den längden jag vill bli. Så jag har inte andvänt alla classer som är i projektet jag skulle förutslår att du tittar:
* Allt under WebContent
* /com/jensen/steamlite/controller/authenticationfilter/CheckUserSession.java
* /src/com/jensen/steamlite/model/bean/
* ResidingUser.java
* UserHandler.java
* /com/jensen/steamlite/model/database/
* DatabaseConnectionUtil.java
* hibernate.cfg.xml
* /com/jensen/steamlite/model/entity/User.java
* /com/jensen/steamlite/model/security/CrypteUtil.java
* /com/jensen/steamlite/view/form/
* ChangeInfoHandler.java
* SignInHandler.java
* SignOutHandler.java
* SignUpHandler.java

# Technologies used and to what
Versions specifide in maven or here.
## Server
* TomEE - Apache tomcat v8.5

### Web Tools
* JSF 
* Primefaces
* CDI

### DB (Database) 
* SQL Database (XXAMP for easy setup)
* Hibernate
* c3p0 //not working as intended

### Other Libraries And Things
* JBCrypt (A "slow hach" generator fitted for java)
* Maven used for dependencies

# Setup process

## prerequisites 
* That you have a understanding of IDE:s, Maven, Java/EE, Relation Databases and SQL Querys. 

1. Clone or download this repository.
2. Follow this guide to download and install [xampp](https://www.youtube.com/watch?v=xdvVKywGlc0).
3. Open Xampp:s schell and copy paste the SQL from the MySQL Code further down.
4. Follow this guide to download and install [TomEE](http://tomee.apache.org/installing-tomee.html).
5. Open the project in your IDE and use maven to clean, update and install.
6. Make shore that the corect configurations are made in the /hibernate.cfg.xml ex. <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/steam_lite</property>
7. Create a server and run the project on it. 

# ERD of the database
![Steamlite ERD](https://github.com/Wedsa95/HibernateSteamLite/blob/master/SteamLite/img/SteamSkinERD.jpg)

# Hibernate Entity Relationship Map
![Steamlite ERM](https://github.com/Wedsa95/HibernateSteamLite/blob/master/SteamLite/img/SteamSkinHR.jpg)

# MySQL Code
```ruby
CREATE DATABASE steam_lite;

USE steam_lite;

CREATE TABLE users (
user_id INT(80) PRIMARY KEY AUTO_INCREMENT,
user_name VARCHAR(80) DEFAULT NULL, 
user_email VARCHAR(80) DEFAULT NULL,
user_password VARCHAR(80) NOT NULL
);

CREATE TABLE libraries (
library_id INT(80) PRIMARY KEY AUTO_INCREMENT,
owner INT(80) NOT NULL,
FOREIGN KEY (owner) REFERENCES users(user_id)
);

CREATE TABLE games (
game_id INT(80) PRIMARY KEY AUTO_INCREMENT,
game_name VARCHAR(80) DEFAULT NULL,
game_app_id INT(80) DEFAULT NULL
);

CREATE TABLE categories (
categories_id INT(80) PRIMARY KEY AUTO_INCREMENT,
categories_name VARCHAR(80) DEFAULT NULL
);

CREATE TABLE achievements (
achiev_id INT(80) PRIMARY KEY AUTO_INCREMENT,
game_with_achiev INT(80) NOT NULL,
achiev_name VARCHAR(80) DEFAULT NULL, 
achiev_points INT(80) DEFAULT NULL,
FOREIGN KEY (game_with_achiev) REFERENCES games(game_id)
);

CREATE TABLE ratings (
rating_id INT(80) PRIMARY KEY AUTO_INCREMENT,
critic INT(80) NOT NULL,
subject INT(80) NOT NULL,
rating_value INT(80) NOT NULL,
FOREIGN KEY (critic) REFERENCES users(user_id),
FOREIGN KEY (subject) REFERENCES games(game_id)
);

CREATE TABLE library_have_games (
library_have INT(80) NOT NULL,
have_game INT(80) NOT NULL,
FOREIGN KEY (library_have) REFERENCES libraries(library_id),
FOREIGN KEY (have_game) REFERENCES games(game_id)
);

CREATE TABLE user_have_achiev (
user_have_achiev_id INT(80) PRIMARY KEY AUTO_INCREMENT,
user_have INT(80) NOT NULL,
have_achiev INT(80) NOT NULL,
achiev_unlocked BOOLEAN DEFAULT 0,
FOREIGN KEY (user_have) REFERENCES users(user_id),
FOREIGN KEY (have_achiev) REFERENCES achievements(achiev_id)
);

CREATE TABLE categories_for_games (
categories_for INT(80) NOT NULL,
for_games INT(80) NOT NULL,
FOREIGN KEY (categories_for) REFERENCES categories(categories_id),
FOREIGN KEY (for_games) REFERENCES games(game_id)
);

INSERT INTO games VALUES(null , 'Portal 2', 620);
INSERT INTO games VALUES(null , 'Portal', 400);
INSERT INTO games VALUES(null , 'Team Fortress 2', 440);
INSERT INTO games VALUES(null , 'Terraria', 105600);
INSERT INTO games VALUES(null , 'Fallout', 38400);
INSERT INTO games VALUES(null , 'Fallout 3', 22300);
INSERT INTO games VALUES(null , 'DOOM', 379720);
INSERT INTO games VALUES(null , ‘Fallout 4’, 377160);
INSERT INTO games VALUES(null , 'Metro 2033', 43110);
INSERT INTO games VALUES(null , 'Metro: Last Light', 43160);
INSERT INTO games VALUES(null , 'Wolfenstein: The New Order', 201810);
INSERT INTO games VALUES(null , 'Batman: The Enemy Within - The Telltale Series
', 675260);
INSERT INTO games VALUES(null ,'i saw her standing there', 453910);
INSERT INTO games VALUES(null , 'Thug Life', 725050);
INSERT INTO games VALUES(null , 'Spin Rush', 528660);
INSERT INTO games VALUES(null , 'Slash It', 567290);
INSERT INTO games VALUES(null , 'Crusader Kings II', 203770);

INSERT INTO categories VALUES(null,'action');
INSERT INTO categories VALUES(null,'adventure');
INSERT INTO categories VALUES(null,'indie');
INSERT INTO categories VALUES(null,'racing');
INSERT INTO categories VALUES(null,'strategy');
INSERT INTO categories VALUES(null,'sports');
INSERT INTO categories VALUES(null,'free to play');
INSERT INTO categories VALUES(null,'rpg');
INSERT INTO categories VALUES(null,'simulation');
INSERT INTO categories VALUES(null,'singleplayer');

```
