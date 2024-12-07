package Senario1;
import java.util.ArrayList;
import java.util.List;


class Employee{
    private int id ;
    private String name,department;
    private double salary; 

    public Employee(int id,String name,String department,double salary){
        setId(id);
        this.name = name;
        this.department = department;
        setSalary(salary);
    }
    public int getId(){
        return id;
    }

    public void setId(int id){
        if(id > 0){
            this.id = id;
        }else{
            System.out.println("Invalid ID");
        }
    }

    public String getname(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDepartment(){
        return department;
    }
    public void setDepartment(String department){
        this.department = department;
    }
    public double getSalary(){
        return salary;
    }
    public void setSalary(double salary){
        if(salary > 0){
            this.salary = salary;
        }else{
            System.out.println("Invalid Salary");
        }
    }

public double FinalSalary(){
    if(salary > 0){
        double FinalSalary = salary + (salary * 0.1);
        return FinalSalary;
    } else {
        return 0;
    }
}
}



public class Senario1 {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            Employee e1 = new Employee(1, "Ahmed", "ItDepartment", 1000);
            Employee e2 = new Employee(2, "Sahan", "ItDepartment", 2000);
            Employee e3 = new Employee(3, "Prince", "ItDepartment", 3000);

            employees.add(e1);
            employees.add(e2);
            employees.add(e3);

            for (Employee employee : employees) {
                System.out.println("Employee ID: " + employee.getId());
                System.out.println("Employee Name: " + employee.getname());
                System.out.println("Employee Department: " + employee.getDepartment());
                // System.out.println("Employee Salary: " + employee.getSalary());
                System.out.println("Employee final salary: " + employee.FinalSalary());
                System.out.println("_____________________________");
                
            }
            
            e1.setName("Ali");
            e1.setSalary(5000);
            e1.setDepartment("HR");
            e1.setId(4);

            System.out.println("Employee ID: " + e1.getId());
            System.out.println("Employee Name: " + e1.getname());
            System.out.println("Employee Department: " + e1.getDepartment());
            // System.out.println("Employee final salary: " + e1.getSalary());
            System.out.println("Employee final salary: " + e1.FinalSalary());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    
}
