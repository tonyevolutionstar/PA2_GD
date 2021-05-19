/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UC3.PSource;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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
    JLabel Thread1;
    
    public readFile(int id, int SOCKET_PORT,JLabel Thread1){
        this.id = id;
        this.SOCKET_PORT = SOCKET_PORT;      
        this.Thread1 = Thread1;
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
        String chunkOfData = "";
        System.out.println("Sending string to the ServerSocket");

        try{
            reader = new BufferedReader(new FileReader(pathSensorsFile));
            String line = reader.readLine();
            int count = 0;
            while(line != null)
            {
                int sensorID = getNumbers(line);
                if(sensorID==this.id)
                {
                    Thread1.setText(line);
                    chunkOfData += line+"; ";
                    count ++;
                    if(count == 1000)
                    {
                        dataOutputStream.writeUTF(chunkOfData);
                        dataOutputStream.flush();  
                        count = 0;
                        chunkOfData = "";
                    }            
                }   
                line = reader.readLine();                       
            }
        }catch (IOException e ){
            e.printStackTrace();
        }
        try {
            dataOutputStream.writeUTF(chunkOfData);
            dataOutputStream.writeUTF("Acabou;");
            dataOutputStream.close(); // close the output stream when we're done.
            Thread1.setText("WorkDone!");            
            System.out.println("Closing socket"); 
            s.close();
        } catch (IOException ex) {
            Thread1.setText("Lost Connection!");  
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
