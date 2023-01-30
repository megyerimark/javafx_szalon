package views;

import java.util.ArrayList;

import models.Car;
import models.Restapi;

public class Mainconsole {
    Restapi rest;

    public Mainconsole(Restapi rest) {
        this.rest = rest;
        this.start();
    }
    public void  start(){
       ArrayList<Car> cars =  this.rest.getCars();
       for(Car car: cars ){
        System.out.printf("%s  %12s %6.2f\n" ,
         car.name, car.brand, car.price);

       }
    };
    

}
