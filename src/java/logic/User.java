
package logic;

import java.util.Date;


public abstract class User {
    int userId;
    String name;
    String surname;
    String address;
    Date birthDate;
    String nationality;
    String phone;
    String username;
    String password;

   

    protected User(int userId, String name, String surname, String address, Date birthDate, String nationality, String phone, String username, String password) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }


    
}
