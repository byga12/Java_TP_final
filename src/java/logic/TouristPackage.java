package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class TouristPackage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int packageId;

    private double packagePrice;

    @OneToMany(mappedBy = "tourist_package")
    private List<Sale> salesWithPackage = new ArrayList<>();

    @ManyToMany(mappedBy = "packagesWithService")
    private List<TouristService> servicesList = new ArrayList<>();

    public TouristPackage() {
    }

    public TouristPackage(int packageId, List<TouristService> servicesList) {
        this.packageId = packageId;
        this.servicesList = servicesList;
        double finalPrice = 0;
//        for (TouristService service : servicesList) {
////            service.addPackageWithService(this);
//            finalPrice += service.getServicePrice();
//            finalPrice = finalPrice - finalPrice * 0.1;
//        }
        this.packagePrice = finalPrice;
        this.salesWithPackage = new ArrayList<>();
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public double getPackagePrice() {

        return packagePrice;
    }

    public void setPackagePrice(double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public List<Sale> getSalesWithPackage() {
        return salesWithPackage;
    }

    public void setSalesWithPackage(List<Sale> salesWithPackage) {
        this.salesWithPackage = salesWithPackage;
    }

    public List<TouristService> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<TouristService> servicesList) {
//        //borro las asociaciones de servicios anteriores.
//        for (TouristService service : this.servicesList) {
//            service.removePackageWithService(this);
//        }
        //asocio los nuevos servicios a este paquete
//        for (TouristService service : servicesList) {
//            service.removePackageWithService(this);
//        }
        //calculo nuevamente el precio final
        double finalPrice = 0;
        for (TouristService service : servicesList) {
            service.addPackageWithService(this);
            finalPrice += service.getServicePrice();
            finalPrice = finalPrice - finalPrice * 0.1;
        }
        this.setPackagePrice(finalPrice);
        this.servicesList = servicesList;
    }

    ////////////////////////////////////////////
    public void addSaleWithPackage(Sale sale) {
        this.salesWithPackage.add(sale);
    }

    //este método devuelve true si existe el servicio que le pasó como parámetro en el paquete.
    public boolean hasTouristService(TouristService actualTouristService) {
        boolean exists = false;
        for (TouristService touristService : this.getServicesList()) {
            if (touristService.getServiceName().equals(actualTouristService.getServiceName())) {
                exists = true;
            }
        }
        return exists;
    }
}
