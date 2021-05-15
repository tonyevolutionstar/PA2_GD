/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UC2.PProducer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;


public class readSocket extends Thread{
 
    int SOCKET_PORT;  
    int id;
    JLabel Thread1;
    
    public readSocket(int id, int SOCKET_PORT,JLabel Thread1){
        this.id = id;
        this.SOCKET_PORT = SOCKET_PORT;      
        this.Thread1=Thread1;
    }


    
    public void run()
    {      
        ServerSocket sConsumer2 = null;
        try {
            sConsumer2 = new ServerSocket(SOCKET_PORT);
        } catch (IOException ex) {
            Logger.getLogger(readSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        Socket s2 = null;
        try {
            s2 = sConsumer2.accept();
        } catch (IOException ex) {
            Logger.getLogger(readSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            System.out.println("Server Connected");
        
        InputStream inputStream2 = null;
        try {
            inputStream2 = s2.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(readSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        DataInputStream dataInputStream2 = new DataInputStream(inputStream2);
        while(true)
        {
            try {  
                String str = dataInputStream2.readUTF();
                Thread1.setText(str);
                System.out.println("Input-"+this.id+"-: "+str);
            } catch (IOException ex) {
                Logger.getLogger(readSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(dataInputStream2.readUTF().isEmpty())
                {
                    System.out.println("No more Incoming Data!");
                    sConsumer2.close();
                    break;
                }
            } catch (IOException ex) {
                Logger.getLogger(readSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
}
