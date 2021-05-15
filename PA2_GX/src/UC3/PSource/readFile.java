/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UC3.PSource;

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


public class readFile extends Thread{
 
    int SOCKET_PORT;
    int id;
    final String pathSensorsFile = "./src/Data/sensor.txt";     
    JLabel Threadl;
    
    public readFile(int id, int SOCKET_PORT, JLabel threadl){
        this.id = id;
        this.SOCKET_PORT = SOCKET_PORT;   
        this.Threadl = threadl;
    }

    
    public void run()
    {      
        Socket s = null;
        try {
            s = new Socket("localhost",SOCKET_PORT);
        } catch (IOException ex) {
            Logger.getLogger(readFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader reader;
        
        // get the output stream from the socket.
        OutputStream outputStream = null;
        try {
            outputStream = s.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(readFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        // create a data output stream from the output stream so we can send data through it
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        System.out.println("Sending string to the ServerSocket");

        try{
            reader = new BufferedReader(new FileReader(pathSensorsFile));
            String line = reader.readLine();
            while(line != null)
            {
                int sensorID = getNumbers(line);
                if(sensorID==this.id)
                {
                    Threadl.setText("Data: "+line);
                    dataOutputStream.writeUTF(line);
                    dataOutputStream.flush(); // send the message              
                }   
                line = reader.readLine();                       
            }
        }catch (IOException e ){
            e.printStackTrace();
        }
        try {
            dataOutputStream.close(); // close the output stream when we're done.
        } catch (IOException ex) {
            Logger.getLogger(readFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println("Closing socket");            
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(readFile.class.getName()).log(Level.SEVERE, null, ex);
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
