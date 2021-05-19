package UC4.PSource;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;


public class readFile extends Thread{
 
    int SOCKET_PORT;
    int id;
    final String pathSensorsFile = "./src/Data/sensor.txt"; 
    final int numberOfRecords = 1000;
    JLabel Thread11;    
    
    public readFile(int id, int SOCKET_PORT, JLabel Thread11){
        this.id = id;
        this.SOCKET_PORT = SOCKET_PORT;   
        this.Thread11 = Thread11;
    }


    
    public void run()
    {     
        String chunkOfData = "";
        DataOutputStream dataOutputStream = null;
        Socket s = null;
        try {
            s = new Socket("localhost",SOCKET_PORT);         
            dataOutputStream = new DataOutputStream(s.getOutputStream());
            RandomAccessFile file = new RandomAccessFile(pathSensorsFile,"r");
            file.seek(((85000000/6)-5)*id);  
            String line = file.readLine();
            int count = 0;
            while(line != null)
            {
                    chunkOfData += line+";";
                    count ++;
                    Thread11.setText(line);                    
                    if(count == numberOfRecords)
                    {
                        dataOutputStream.writeUTF(chunkOfData);
                        dataOutputStream.flush();
                        count = 0;
                        chunkOfData = "";
                    } // send the message              
                 
                line = file.readLine();
        }
        } catch (FileNotFoundException ex) {
            try {
                dataOutputStream.writeUTF(chunkOfData);
            } catch (IOException ex1) {
                Logger.getLogger(readFile.class.getName()).log(Level.SEVERE, null, ex1);
            }
            try {
                dataOutputStream.writeUTF("Acabou;");
            } catch (IOException ex1) {
                Logger.getLogger(readFile.class.getName()).log(Level.SEVERE, null, ex1);
            }
            try {
                dataOutputStream.close(); // close the output stream when we're done.
            } catch (IOException ex1) {
                Logger.getLogger(readFile.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Thread11.setText("WorkDone!");            
            System.out.println("Closing socket"); 
            try {
                s.close();
            } catch (IOException ex1) {
                Logger.getLogger(readFile.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Thread11.setText("Lost Connection!"); 
        }       
    }
    
}
