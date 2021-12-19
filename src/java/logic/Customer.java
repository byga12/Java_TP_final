package logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Customer extends Person {

    private int purchasesQuantity;

    @OneToMany(mappedBy = "customer")
    private List<Sale> salesAssociated;

    public Customer() {
    }

    public Customer(int userId, String name, String surname, String address, String dni, String nationality, String phone, String email, Date birthDate) {
        super(userId, name, surname, address, dni, nationality, phone, email, birthDate);
        this.purchasesQuantity = 0;
        this.salesAssociated = new ArrayList<>();
    }

    public int getPurchasesQuantity() {
        return purchasesQuantity;
    }

    public void setPurchasesQuantity(int purchasesQuantity) {
        this.purchasesQuantity = purchasesQuantity;
    }

    public List<Sale> getSalesAssociated() {
        return salesAssociated;
    }

    public void setSalesAssociated(List<Sale> salesAssociated) {
        this.salesAssociated = salesAssociated;
        this.purchasesQuantity = this.salesAssociated.size();
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

    ////////////////////////////////////////////
    public void addSale(Sale sale) {
        this.salesAssociated.add(sale);
        this.purchasesQuantity = this.salesAssociated.size();
    }

    public void removeSale(Sale sale) {
        this.salesAssociated.remove(sale);
        this.purchasesQuantity = this.salesAssociated.size();
    }
}
