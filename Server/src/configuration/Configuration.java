/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class Configuration {
    private static Configuration instance;
    private Properties konfiguracija;

    private Configuration() {
        konfiguracija = new Properties();
        try {
            konfiguracija.load(new FileInputStream("config/dbconfig.properties"));
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Configuration getInstance(){
        if(instance == null){
            instance = new Configuration();
        }
        return instance;
    }
    
    public String getProperty(String key) {
        return konfiguracija.getProperty(key, "n/a");
    }
    public void setProperty(String key, String value) {
        konfiguracija.setProperty(key, value);
    }
    
    //trajno cuvanje izmena, najcesce iz GUI formi
    public void sacuvajIzmene() throws IOException {
        try {
            konfiguracija.store(new FileOutputStream("config/dbconfig.properties"), null);
        } catch (FileNotFoundException ex) {
            System.getLogger(Configuration.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
