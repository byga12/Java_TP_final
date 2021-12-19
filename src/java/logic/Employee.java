package logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Employee extends Person {

    private String job;
    private Double salary;
    private String username;
    private String password;

    @OneToMany(mappedBy = "employee")
    private List<Sale> salesDone;

    public Employee() {
    }

    public Employee(String job, Double salary, String username, String password, int userId, String name, String surname, String address, String dni, String nationality, String phone, String email, Date birthDate) {
        super(userId, name, surname, address, dni, nationality, phone, email, birthDate);
        this.job = job;
        this.salary = salary;
        this.username = username;
        this.password = password;
        this.salesDone = new ArrayList<>();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Sale> getSalesDone() {
        return salesDone;
    }

    public void setSalesDone(List<Sale> salesDone) {
        this.salesDone = salesDone;
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
    public void removeSale(Sale sale) {
        this.salesDone.remove(sale);
    }

    public void addSale(Sale sale) {
        this.salesDone.add(sale);
    }
}
