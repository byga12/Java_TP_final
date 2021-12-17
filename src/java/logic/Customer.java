package logic;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Customer extends Person {

    private int purchasesQuantity;

    public Customer() {
    }

    public Customer(int purchasesQuantity, int userId, String name, String surname, String address, String dni, String nationality, String phone, String email, Date birthDate) {
        super(userId, name, surname, address, dni, nationality, phone, email, birthDate);
        this.purchasesQuantity = purchasesQuantity;
    }

    public int getPurchasesQuantity() {
        return purchasesQuantity;
    }

    public void setPurchasesQuantity(int purchasesQuantity) {
        this.purchasesQuantity = purchasesQuantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

}
