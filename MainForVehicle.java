//EXP 2
public class MainForVehicle {
     public static void main(String[] a) 
     {
         Vehicle dv = new Vehicle("Kia","Seltos","Orange",3200000.33,true);
        
         Vehicle pv = new Vehicle("MG","LXI","Silver",3400000.55,false);
         System.out.println("========Vehicle Details=======");
         System.out.printf("%-12s | %-12s | %-10s | %-15s | %-10s%n", 
                          "BRAND", "MODEL", "COLOR", "PRICE", "MILEAGE");
        
        printVehicleDetails(dv, 450.0, 10.0);
        printVehicleDetails(pv, 550.0, 10.0);
        
         Vehicle pv1 = dv;
         Vehicle cv1= pv;
         cv1.brandName = "Hyundai";
         //printvehicledetails
         Vehicle[] myVehicles = new Vehicle[]{dv,pv,pv1,cv1};
        
        
    }
     public static void printVehicleDetails(Vehicle v, double dist, double fuel) 
     {
         
         double mileage = v.calculateMileage(dist,fuel);
         

         System.out.printf("%-12s | %-12s | %-10s | %-15.2f | %-10.2f km/l%n", 
                          v.brandName, v.modelName, v.color, v.price, mileage);
    }
    
}
