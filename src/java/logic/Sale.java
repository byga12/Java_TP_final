package logic;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int saleId;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date saleDate;

    private String paymentMethod;
    private double price;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "tourist_service_id")
    private TouristService tourist_service;

    @ManyToOne
    @JoinColumn(name = "tourist_package_id")
    private TouristPackage tourist_package;

    public Sale() {
    }

    //Constructor con servicio turístico
    public Sale(int saleId, Date saleDate, String paymentMethod, Employee employee, Customer customer, TouristService tourist_service) {
        this.saleId = saleId;
        this.saleDate = saleDate;
        this.paymentMethod = paymentMethod;
        this.employee = employee;
        this.customer = customer;
        this.tourist_service = tourist_service;
        this.price = tourist_service.getServicePrice();

    }

    //Constructor con paquete turístico
    public Sale(int saleId, Date saleDate, String paymentMethod, Employee employee, Customer customer, TouristPackage tourist_package) {
        this.saleId = saleId;
        this.saleDate = saleDate;
        this.paymentMethod = paymentMethod;
        this.employee = employee;
        this.customer = customer;
        this.tourist_package = tourist_package;
        this.price = tourist_package.getPackagePrice();

    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public TouristService getTourist_service() {
        return tourist_service;
    }

    public void setTourist_service(TouristService tourist_service) {
        this.tourist_service = tourist_service;
    }

    public TouristPackage getTourist_package() {
        return tourist_package;
    }

    public void setTourist_package(TouristPackage tourist_package) {
        this.tourist_package = tourist_package;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        //el empleado anterior se desvincula de esta venta
        this.employee.removeSale(this);
        //el empleado nuevo añade esta venta
        employee.addSale(this);
        this.employee = employee;

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        //el cliente anterior se desvincula
        this.customer.removeSale(this);
        //el cliente nuevo añade esta venta
        customer.addSale(this);
        this.customer = customer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    ////////////////////////////////////////////
}
