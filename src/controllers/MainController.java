package controllers;

import models.Restapi;
import views.Mainconsole;

public class MainController {
    Mainconsole maincon;
    Restapi rest;

    public MainController() {
        
        this.rest = new Restapi();
        this.maincon = new Mainconsole(this.rest);
    
}
}