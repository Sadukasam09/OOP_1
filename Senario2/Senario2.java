package Senario2;
import java.util.*;

abstract class Shipments{
    protected String shipmentId;
    protected double ShipmentsWeight;
    protected String destination;

    public Shipments(String shipmentId,double ShipmentsWeight,String destination){
        this.shipmentId = shipmentId;
        this.ShipmentsWeight = ShipmentsWeight;
        this.destination = destination;
    }
    public String getShipmentId(){
        return shipmentId;
    }
    public void setShipmentId(String shipmentId){
        this.shipmentId = shipmentId;
    }
    public double getShipmentsWeight(){
        return ShipmentsWeight;
    }
    public void setShipmentsWeight(double ShipmentsWeight){
        this.ShipmentsWeight = ShipmentsWeight;
    }
    public String getDestination(){
        return destination;
    }
    public void setDestination(String destination){
        this.destination = destination;
    }
    public abstract void calculateCost(double billamount);


}

 class Domestic extends Shipments{
    private String localregion;
  

   public Domestic(String shipmentId,double ShipmentsWeight,String destination,String localregion){
       super(shipmentId,ShipmentsWeight,destination);
       this.localregion = localregion;

   }
    public String getLocalregion(){
         return localregion;
    }
    public void setLocalregion(String localregion){
        this.localregion = localregion;
    }
    

    @Override
    public void calculateCost(double billamount){
        billamount = 50;
        double tax = 0.05;
            double totalcost = ShipmentsWeight * billamount + (ShipmentsWeight * billamount * tax);
            System.out.println("Total Cost: "+totalcost);
       
    }
}


  
   



  class International extends Shipments{
    private double customesfee ;
    private double insurancefee;
    private int type;

    public International(String shipmentId,double ShipmentsWeight,String destination,int type){
        super(shipmentId,ShipmentsWeight,destination);
        this.type = type;
        setFeeType(type);
    }
    public double getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    public void setFeeType(int type){
        if(type == 1){
            customesfee = 100;
            insurancefee = 50;
        }else if(type == 2){
            customesfee = 200;
            insurancefee = 100;
        }else if(type == 3){
            customesfee = 300;
            insurancefee = 150;
        }
    }
    @Override
    public void calculateCost(double billamount){
        billamount = 100;
        double tax = 0.15;
        double totalcost = ShipmentsWeight * billamount + (ShipmentsWeight * billamount * tax) + customesfee + insurancefee;
        System.out.println("Total Cost: "+totalcost);
       
    }
    

}

public class Senario2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Shipment ID: ");
        String shipmentId = input.nextLine();

        System.out.println("Enter Shipments Weight: ");
        double ShipmentsWeight = input.nextDouble();

        System.err.println("1. Domestic");
        System.err.println("2. International");
        System.out.println("Enter Destination: ");
        String destination = input.next();

    
        if(!destination.equals("Domestic") && !destination.equals("International")){
            System.out.println("Invalid Destination");
        }
        else if(destination.equals("Domestic")){
            System.out.println("Enter Local Region: ");
            String localregion = input.next();
            
            Domestic d = new Domestic(shipmentId,ShipmentsWeight,destination,localregion);
            d.calculateCost(0);


        }
        else if(destination.equals("International")){
            System.err.println("1. clothing");
            System.err.println("2. electronics");
            System.err.println("3. food");
            System.out.println("Enter Type: ");
            int type = input.nextInt();
            International i = new International(shipmentId,ShipmentsWeight,destination,type);
            i.calculateCost(0);
          
            if(type < 1 || type > 3){
                System.out.println("Invalid Type");
            }
        }

       

       
       


        

       

        
       
    }
    
}
