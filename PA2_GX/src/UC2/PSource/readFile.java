/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UC2.PSource;

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


public class readFile extends Thread{
 
    int SOCKET_PORT;
    final String pathSensorsFile = "./src/Data/sensor.txt";      
    
    public readFile(int SOCKET_PORT){
        this.SOCKET_PORT = SOCKET_PORT;      
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
                dataOutputStream.writeUTF(line);
                dataOutputStream.flush(); // send the message
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
}
