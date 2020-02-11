# Person related information
Simple CRUD application made with Spring Boot

- Spring Boot
- Spring Security
- Thymeleaf
- H2 database
- Bootstrap

Usage (with eclipse):
1.) Clone the project
2.) Eclipse: File -> Import -> Maven -> Existing Maven Projects
3.) Run
4.) Navigate to localhost:8080

on command line, run the following command:
1) git clone https://github.com/shivamshukla/PersonCRUD-SpringBoot.git
2) mvn clean
3) mvn install
4) mvn spring-boot:run
5) Open localhost:8080 on browser.

Application contains two users:user/user,admin/admin

Admin has only rights to delete the user.

Using Signup page, user can create another user of only type ROLE=USER.

After login with ADMIN rights, one can delete, add, edit the person record and with USER rights, one can add and edit the person record.
