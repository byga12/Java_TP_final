package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class TouristService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serviceId;

    private String serviceName;
    private String serviceDescription;
    private String serviceDestiny;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date serviceDate;

    private double servicePrice;

    @OneToMany(mappedBy = "tourist_service")
    private List<Sale> salesWithService = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "service_package", joinColumns = @JoinColumn(name = "packageId"), inverseJoinColumns = @JoinColumn(name = "serviceId"))
    private List<TouristPackage> packagesWithService = new ArrayList<>();

    public TouristService() {
    }

    public TouristService(int serviceId, String serviceName, String serviceDescription, String serviceDestiny, Date serviceDate, double servicePrice) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.serviceDestiny = serviceDestiny;
        this.serviceDate = serviceDate;
        this.servicePrice = servicePrice;
        this.salesWithService = new ArrayList<>();
        this.packagesWithService = new ArrayList<>();
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getServiceDestiny() {
        return serviceDestiny;
    }

    public void setServiceDestiny(String serviceDestiny) {
        this.serviceDestiny = serviceDestiny;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public List<Sale> getSalesWithService() {
        return salesWithService;
    }

    public void setSalesWithService(List<Sale> salesWithService) {
        this.salesWithService = salesWithService;
    }

    public List<TouristPackage> getPackagesWithService() {
        return packagesWithService;
    }

    public void setPackagesWithService(List<TouristPackage> packagesWithService) {
        this.packagesWithService = packagesWithService;
    }

    ////////////////////////////////////////////
    public void addSaleWithService(Sale sale) {
        this.salesWithService.add(sale);
    }

    public void addPackageWithService(TouristPackage touristPackage) {
        this.packagesWithService.add(touristPackage);
    }

    public void removePackageWithService(TouristPackage touristPackage) {
        this.packagesWithService.remove(touristPackage);
    }
}
