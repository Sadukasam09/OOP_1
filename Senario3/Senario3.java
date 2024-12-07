package Senario3;
import java.util.*;

abstract  class LogisticCompanyVehicelType{
    protected double vehicleWeight;
    protected double vehicleDistance;
    public LogisticCompanyVehicelType(double vehicleWeight,double vehicleDistance){
        this.vehicleWeight = vehicleWeight;
        this.vehicleDistance = vehicleDistance;
    }

    public abstract void vehicleCalculations();
}
class Truck extends LogisticCompanyVehicelType{

    public Truck(double vehicleWeight,double vehicleDistance){
        super(vehicleWeight,vehicleDistance);
    }

    @Override
    public void vehicleCalculations(){
       
       double cost = vehicleDistance * 5 + vehicleWeight * 2;
         System.out.println("Cost: "+cost);
    }
}
class Ship extends LogisticCompanyVehicelType{
    
     public Ship(double vehicleWeight,double vehicleDistance){
        super(vehicleWeight,vehicleDistance);
    }

    @Override
    public void vehicleCalculations(){

        double cost = vehicleDistance * 3 + vehicleWeight * 1.5;
        System.out.println("Cost: "+cost);  
    }
}

class Airplane extends LogisticCompanyVehicelType{

    public Airplane(double vehicleWeight,double vehicleDistance){
        super(vehicleWeight,vehicleDistance);
    }
    @Override
    public void vehicleCalculations(){
        
        double cost = vehicleDistance* 10 + vehicleWeight * 5;
        System.out.println("Cost: "+cost);
    }
        
}

public class Senario3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<LogisticCompanyVehicelType> vehicles = new ArrayList<LogisticCompanyVehicelType>();
        while(true){

                System.out.println("Enter the vehicle weight: ");
                double vehicleWeight = sc.nextDouble();
                System.out.println("Enter the vehicle distance: ");
                double vehicleDistance = sc.nextDouble();


                System.err.println("1.Truck");
                System.err.println("2.Ship");
                System.err.println("3.Airplane");
                System.out.println("Enter the vehicle type: ");
                int vehicleType = sc.nextInt();

            
                if(vehicleType < 1 || vehicleType > 3){
                    System.out.println("Invalid Vehicle Type");
                }
                else{
                 LogisticCompanyVehicelType vehicle = null;
                            switch (vehicleType) {
                                case 1:
                                vehicle = new Truck( vehicleWeight, vehicleDistance);
                                    break;
                                case 2:
                                vehicle = new Ship( vehicleWeight, vehicleDistance);
                                    break;
                                case 3:
                                vehicle = new Airplane( vehicleWeight, vehicleDistance);
                                    break;    
                                default:
                                    throw new AssertionError();
                            }
        
                         if (vehicle != null){
                            vehicles.add(vehicle);
                        }
                        
                        
                     for (LogisticCompanyVehicelType vehiclesCalculate : vehicles){
                          ((LogisticCompanyVehicelType) vehicle).vehicleCalculations();
            
                            
                      }
                }
        }
     
    
    }

}
