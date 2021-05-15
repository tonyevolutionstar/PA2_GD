/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UC4.PProducer;

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
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;


public class readSocket extends Thread{
 
    int SOCKET_PORT;  
    int id;
    JLabel Threadl;
    
    public readSocket(int id, int SOCKET_PORT, JLabel Thread){
        this.id = id;
        this.SOCKET_PORT = SOCKET_PORT;   
        this.Threadl = Thread;
    }

    public void run()
    {      
       ServerSocket sConsumer = null;
        try {
            sConsumer = new ServerSocket(SOCKET_PORT);
        } catch (IOException ex) {
            Logger.getLogger(readSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        Socket s = null;
        try {
            s = sConsumer.accept();
        } catch (IOException ex) {
            Logger.getLogger(readSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Client Connected");
        
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(s.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(readSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader bf = new BufferedReader(in);
        while(true)
        {
            String str = null;
           try {
               str = bf.readLine();
           } catch (IOException ex) {
               Logger.getLogger(readSocket.class.getName()).log(Level.SEVERE, null, ex);
           }
            System.out.println("Input-"+this.id+"-: "+str);  
            Threadl.setText("Data: "+str);
            if(str==null)
            {
                System.out.println("No more Incoming Data!");
                break;
            }
        }
    }
}
