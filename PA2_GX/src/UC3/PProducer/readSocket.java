/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UC3.PProducer;

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
            sConsumer2.setReceiveBufferSize(50);           
        } catch (IOException ex) {
            Logger.getLogger(readSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        Socket s2 = null;
        try {
            s2 = sConsumer2.accept();
        } catch (IOException ex) {
            Logger.getLogger(readSocket.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
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
                    String[] arrOfStr = str.split(";",-2);
                    for(String a: arrOfStr)
                    {
                        if(!a.isBlank())
                        {
                            if(a.equals("Acabou"))
                            {
                                Thread1.setText("Work done!");   
                                return;
                            }
                            System.out.println("Input-"+this.id+"-: "+a);
                            Thread1.setText(a);   
                        }                        
                    }
                
            } catch (IOException ex) {
                Logger.getLogger(readSocket.class.getName()).log(Level.INFO, null, ex);
                try {
                    s2.close();
                    Thread1.setText("Lost Connection!");
                } catch (IOException ex1) {
                    Logger.getLogger(readSocket.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }  
    }
}
