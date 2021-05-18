/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package UC2.PSource;

import static UC2.PSource.readFile.getNumbers;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;


public class teste extends Thread{
 
    int SOCKET_PORT;
    int id;
    final String pathSensorsFile = "./src/Data/sensor.txt"; 
    JLabel Thread1;
    readFile t0;
    readFile t1;
    readFile t2;
    readFile t3;
    readFile t4;
    readFile t5;
    
    public teste(){        
         testes();
    }

    
    public void testes()
    {
         t0 = new readFile (0,7771);
         t1 = new readFile (1,7772);
         t2 = new readFile (2,7773);
         t3 = new readFile (3,7774);
         t4 = new readFile (4,7775);
         t5 = new readFile (5,7776);  
        t0.start();
     /*   t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
    
    @Override
    public void run()
    {      
        BufferedReader reader;

        System.out.println("Sending string to the ServerSocket");

        try{
            reader = new BufferedReader(new FileReader(pathSensorsFile));
            String line = reader.readLine();
            String chunkOfData = "";
            int count = 0;
            while(line != null)
            {
                int sensorID = getNumbers(line);
                if(sensorID==0)
                {
                  t0.teste123(line);
                }
                if(sensorID==1)
                {
                  t1.teste123(line);                    
                }
                if(sensorID==2)
                {
                  t2.teste123(line);                    
                }
                if(sensorID==3)
                {
                  t3.teste123(line);                    
                }
                if(sensorID==4)
                {
                  t4.teste123(line);  
                }
                if(sensorID==5)
                {
                  t5.teste123(line);       
                                     
                }
                
                line = reader.readLine();                       
            }
        }catch (IOException e ){
            e.printStackTrace();
        }

    }
    
    
    static int getNumbers(String s) {

    String[] n = s.split(""); //array of strings
    StringBuffer f = new StringBuffer(); // buffer to store numbers

    for (int i = 0; i < n.length; i++) {
        if((n[i].matches("[0-9]+"))) {// validating numbers
            f.append(n[i]); //appending
        }else {
            //parsing to int and returning value
            return Integer.parseInt(f.toString()); 
        }   
    }
    return 0;
 }
    
}
*/