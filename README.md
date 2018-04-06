# Angular 2 with Spring boot

Java project which integrates Angular 2 with Spring boot.


## Requirements:

Install Homebrew. Follow the steps on 
  https://treehouse.github.io/installation-guides/mac/homebrew.


 * Nodejs 4 + installed;
 * Java 8 installed;
 * Maven installed


#### Download project dependencies


Angular CLI
  * npm install -g @angular/cli

Maven dependencies
  * cd ebbi-service;
  * mvn clean install;

Frontend dependencies
  * cd frontend
  * npm install


## Usage 

* Execute `ng build` on frontend folder. (this command will build frontend and output files 
into "src/main/resources/static"). You can also use `ng build --watch` to auto copy your files into static folder when you edit files.

* On root application directory execute `mvn spring-boot:run`


## Usage (Run frontend on nodejs and backend with spring)

Local instance is:
 http://localhost:9091/admin


## License

* LarSen EBBI Energy Branding
