# libary
Project using to managmene users, books in libary. 

Project based on Spring(Boot, Security, JPA AOP,Web) and Hibernate.
Front based on thymeleaf.

Currently implemented:
User: settings, borrow book
Admin: add user, reset user password, active/deactive user, add book, remove book, edit book

Quest index page
![alt](https://i.imgur.com/JhceU15.png)

User borrow history
![alt](https://i.imgur.com/s6PdyZ8.png)

Admin view all books
![alt](https://i.imgur.com/BhTS6NW.png)

Admin view all books page
![alt](https://i.imgur.com/wcHmB2c.png)

Admin view users page
![alt](https://i.imgur.com/45BJRUc.png)

Admin add book page
![alt](https://i.imgur.com/XuMXCeA.png)

Admin lend books page
![alt](https://i.imgur.com/mfdIhyR.png)

To create user you need to do this sql in database:
"INSERT INTO role (role_id, role) VALUES ('1', 'USER');"
