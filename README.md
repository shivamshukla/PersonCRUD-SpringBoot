# Person relatd information
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
4.) Navigate to https://localhost:8080

Application contains two demo users:
user-name password   Role 
user       user      USER
admin      admin     ADMIN

Admin can only delete the user.

Using Signup page, user can create another user of only ROLE=USER.

After login with ADMIN rights, one can delete, add, edit the person.
and with USER rights, one can add and edit the person