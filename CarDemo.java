import java.lang.*;
import java.util.Scanner;

interface Engine {
    void start();

    void stop();
}


abstract class Vehicle {
    private String company;
    private String model;
    private int year;

    public Vehicle(String company, String model, int year) {
        this.company = company;
        this.model = model;
        this.year = year;
    }

    
    public abstract void drive();   //abstract method

    
    public void displayInfo() {
        System.out.println("Vehicle Information: " + company + " " + model + " " + year);
    }
}

class Car extends Vehicle implements Engine {    //inheritance and interface implement
    private FuelTank fuelTank;
    private boolean isEngineRunning;

    public Car(String company, String model, int year, FuelTank fuelTank) {
        super(company, model, year);
        this.fuelTank = fuelTank;
        this.isEngineRunning = false;
    }

    
    public void drive() {      //override abstract method
        if (isEngineRunning) {
            if (fuelTank.getCurrentFuelLevel() > 0) {
                System.out.println("The car can be drive.");
                fuelTank.consumeFuel(10); 
            } 
            else {
                System.out.println("car can't drive");
            }
        } else {
            System.out.println("Start the car before driving.");
        }
    }

    public void start() {       //override method of interface
        if (!isEngineRunning && fuelTank.getCurrentFuelLevel() > 0) {
            isEngineRunning = true;
            System.out.println("The car engine is now started.");
        } else {
            System.out.println("Unable to start the car engine.");
        }
    }

    
    public void stop() {     //override method of interface
        if (isEngineRunning) {
            isEngineRunning = false;
            System.out.println("The car engine is now stopped.");
        } else {
            System.out.println("The car engine is already stopped.");
        }
    }
}


class ElectricCar extends Vehicle implements Engine {
    private int batteryLevel;
    private boolean isEngineRunning;

    public ElectricCar(String company, String model, int year) {
        super(company, model, year);
        this.batteryLevel = 100; 
        this.isEngineRunning = false;
    }

    
    public void drive() {         //override
        if (isEngineRunning) {
            if (batteryLevel > 0) {
                System.out.println("The electric car is now driving.");
                batteryLevel -= 10; 
            } else {
                System.out.println("need to charge battery");
            }
        } else {
            System.out.println("Start the electric car before driving.");
        }
    }

    
    public void start() {   //override
        if (!isEngineRunning && batteryLevel > 0) {
            isEngineRunning = true;
            System.out.println("The electric car engine is now started.");
        } else {
            System.out.println("Unable to start the electric car engine. Check battery level or engine status.");
        }
    }

    
    public void stop() {   //override
        if (isEngineRunning) {
            isEngineRunning = false;
            System.out.println("The electric car engine is now stopped.");
        } else {
            System.out.println("The electric car engine is already stopped.");
        }
    }
}


interface FuelTank {            //interface for fuel
    int getCurrentFuelLevel();
    void consumeFuel(int amount);
   // void refuel(int amount);
}

class FuelTank1 implements FuelTank {
    private int capacity;
    private int currentFuelLevel;

    public FuelTank1(int capacity) {
        this.capacity = capacity;
        this.currentFuelLevel = capacity; 
    }

    
    public int getCurrentFuelLevel() {   //override
        return currentFuelLevel;
    }

    
    public void consumeFuel(int amount) {   //override
        if (currentFuelLevel >= amount) {
            currentFuelLevel -= amount;
            System.out.println(amount + " liters fuel used. Current fuel level: " + currentFuelLevel);
        } else {
            System.out.println("Not enough fuel to consume.");
        }
    }

    
    /*public void refuel(int amount) {      //override
        if (currentFuelLevel + amount <= capacity) {
            currentFuelLevel += amount;
        } else {
            currentFuelLevel = capacity;
        }
    }*/
}

public class CarDemo {
    public static void main(String[] args) {
        
        FuelTank1 carFuelTank = new FuelTank1(50);
        Car myCar = new Car("Toyota", "Fortuner", 2022, carFuelTank);

    
        ElectricCar myElectricCar = new ElectricCar("Maruti", "Baleno", 2022);

        myCar.displayInfo();
        myCar.start();
        myCar.drive();
        myCar.displayInfo();
        myCar.stop();
        myCar.displayInfo();
       // myCar.refuel(20);
        myCar.drive();
        myCar.displayInfo();

        myElectricCar.displayInfo();
        myElectricCar.start();
        myElectricCar.drive();
        myElectricCar.displayInfo();
        myElectricCar.stop();
        myElectricCar.displayInfo();
    }
}