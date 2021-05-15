/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UC4.PSource;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.nio.channels.Channels;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;


public class readFile extends Thread{
 
    int SOCKET_PORT;
    int id;
    final String pathSensorsFile = "./src/Data/sensor.txt";      
    
    public readFile(int id, int SOCKET_PORT){
        this.id = id;
        this.SOCKET_PORT = SOCKET_PORT;      
    }

    
    public void run()
    {      
        try {
            Socket s = new Socket("localhost",SOCKET_PORT);
            OutputStream outPutStream = null;              
            RandomAccessFile file = new RandomAccessFile(pathSensorsFile,"r");
            file.seek(((85000000/6)-5)*id);  
            byte[] bytesToRead = new byte[(85000000/6)-5]; 
            InputStream is = Channels.newInputStream(file.getChannel());
            is.read(bytesToRead);
            outPutStream = s.getOutputStream();
            outPutStream.write(bytesToRead,0,bytesToRead.length);
            outPutStream.flush();
            file.close();
            System.out.println(id+"------");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(readFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(readFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
