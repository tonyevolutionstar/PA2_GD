/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UC4.PProducer;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        ServerSocket sConsumer2 = null;
        try {
            sConsumer2 = new ServerSocket(SOCKET_PORT);
            sConsumer2.setReceiveBufferSize(50);
            sConsumer2.setPerformancePreferences(0, 1, 2);            
        } catch (IOException ex) {
            Logger.getLogger(UC4.PProducer.readSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        Socket s2 = null;
        try {
            s2 = sConsumer2.accept();
        } catch (IOException ex) {
            Logger.getLogger(UC4.PProducer.readSocket.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        
            System.out.println("Server Connected");
        
        InputStream inputStream2 = null;
        try {
            inputStream2 = s2.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(UC4.PProducer.readSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        DataInputStream dataInputStream2 = new DataInputStream(inputStream2);
        while(true)
        {
            try {  
                String str = dataInputStream2.readUTF();
                if(str.isEmpty())
                {
                    System.out.println("No more Incoming Data!");
                    sConsumer2.close();
                    break;
                }
                else
                {
                    String[] arrOfStr = str.split(";",-2);
                    for(String a: arrOfStr)
                    {
                        if(!a.isBlank())
                        {
                            System.out.println("Input-"+this.id+"-: "+a);
                            Threadl.setText(a);     
                        }                      
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(UC4.PProducer.readSocket.class.getName()).log(Level.INFO, null, ex);
                try {
                    s2.close();
                    Threadl.setText("Lost Connection!");
                } catch (IOException ex1) {
                    Logger.getLogger(UC4.PProducer.readSocket.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }  
    }
}
