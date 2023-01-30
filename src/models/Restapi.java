package models;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Restapi {
    String host;

    public Restapi() {
        this.host ="http://[::1]:3000/";
    }
    
    public String getCarsAsString(){
        String response = null;

        try {
            response = trygetCarsAsString();
            
        } catch (MalformedURLException e) {
           System.err.println("Hiba! , Érvénytelen URL");
        }

        catch(IOException e ) {
            System.err.println("Hiba! A REST API elérése sikertelen!");
        }

        return response;
    }


    public String trygetCarsAsString() 
            throws MalformedURLException,IOException{
        String endpoint = "cars";

        String urlStr = this.host + endpoint;
        URL url = new URL(urlStr);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("GET");
        http.connect();
        int responseCode = http.getResponseCode();
         System.out.println(responseCode);
        String text = "--hiba--";
        if(responseCode == 200) {
            InputStream inputStream = http.getInputStream();
            text = this.convertInputStreamtoString(inputStream);
        }
        // System.out.println(text);
        return text;
    }
    public String convertInputStreamtoString(InputStream inputStream) {
        Reader reader = new InputStreamReader(inputStream);
        Scanner scanner = new Scanner(reader);
        StringBuilder stringBuilder = new StringBuilder();
        while(scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        scanner.close();
        return stringBuilder.toString();

    }
    public ArrayList<Car> getCars(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String text = getCarsAsString();
        Car[] carArray = gson.fromJson(text , Car[].class);
        ArrayList<Car> carList = new ArrayList<>(Arrays.asList(carArray));

        return carList;

    }
}
