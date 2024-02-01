// Defining the Engine interface
interface Engine {
    start();
    stop();
}

// Defining the Vehicle abstract class
class Vehicle {
    constructor(company, model, year) {
        this.company = company;
        this.model = model;
        this.year = year;
    }

    // Abstract method drive
    drive() {}
    
    // Method to display vehicle information
    displayInfo() {
        console.log("Vehicle Information: " + this.company + " " + this.model + " " + this.year);
    }
}

// Defining the Car class which extends Vehicle and implements Engine
class Car extends Vehicle {
    constructor(company, model, year, fuelTank) {
        super(company, model, year);
        this.fuelTank = fuelTank;
        this.isEngineRunning = false;
    }

    // Overridden drive method
    drive() {
        if (this.isEngineRunning) {
            if (this.fuelTank.getCurrentFuelLevel() > 0) {
                console.log("The car can be drive.");
                this.fuelTank.consumeFuel(10); 
            } else {
                console.log("car can't drive");
            }
        } else {
            console.log("Start the car before driving.");
        }
    }

    // Overridden start method from Engine interface
    start() {
        if (!this.isEngineRunning && this.fuelTank.getCurrentFuelLevel() > 0) {
            this.isEngineRunning = true;
            console.log("The car engine is now started.");
        } else {
            console.log("Unable to start the car engine.");
        }
    }

    // Overridden stop method from Engine interface
    stop() {
        if (this.isEngineRunning) {
            this.isEngineRunning = false;
            console.log("The car engine is now stopped.");
        } else {
            console.log("The car engine is already stopped.");
        }
    }
}

// Defining the ElectricCar class which extends Vehicle and implements Engine
class ElectricCar extends Vehicle {
    constructor(company, model, year) {
        super(company, model, year);
        this.batteryLevel = 100;
        this.isEngineRunning = false;
    }

    // Overridden drive method
    drive() {
        if (this.isEngineRunning) {
            if (this.batteryLevel > 0) {
                console.log("The electric car is now driving.");
                this.batteryLevel -= 10; 
            } else {
                console.log("need to charge battery");
            }
        } else {
            console.log("Start the electric car before driving.");
        }
    }

    // Overridden start method from Engine interface
    start() {
        if (!this.isEngineRunning && this.batteryLevel > 0) {
            this.isEngineRunning = true;
            console.log("The electric car engine is now started.");
        } else {
            console.log("Unable to start the electric car engine. Check battery level or engine status.");
        }
    }

    // Overridden stop method from Engine interface
    stop() {
        if (this.isEngineRunning) {
            this.isEngineRunning = false;
            console.log("The electric car engine is now stopped.");
        } else {
            console.log("The electric car engine is already stopped.");
        }
    }
}

// Defining the FuelTank interface
interface FuelTank {
    getCurrentFuelLevel();
    consumeFuel(amount);
}

// Defining the FuelTank1 class which implements FuelTank interface
class FuelTank1 {
    constructor(capacity) {
        this.capacity = capacity;
        this.currentFuelLevel = capacity; 
    }

    // Method to get current fuel level
    getCurrentFuelLevel() {
        return this.currentFuelLevel;
    }

    // Method to consume fuel
    consumeFuel(amount) {
        if (this.currentFuelLevel >= amount) {
            this.currentFuelLevel -= amount;
            console.log(amount + " liters fuel used. Current fuel level: " + this.currentFuelLevel);
        } else {
            console.log("Not enough fuel to consume.");
        }
    }
}

// Main function
function carDemo() {
    const carFuelTank = new FuelTank1(50);
    const myCar = new Car("Toyota", "Fortuner", 2022, carFuelTank);
    
    const myElectricCar = new ElectricCar("Maruti", "Baleno", 2022);
    
    myCar.displayInfo();
    myCar.start();
    myCar.drive();
    myCar.displayInfo();
    myCar.stop();
    myCar.displayInfo();
    myCar.drive();
    myCar.displayInfo();
    
    myElectricCar.displayInfo();
    myElectricCar.start();
    myElectricCar.drive();
    myElectricCar.displayInfo();
    myElectricCar.stop();
    myElectricCar.displayInfo();
}

// Calling the main function
carDemo();
