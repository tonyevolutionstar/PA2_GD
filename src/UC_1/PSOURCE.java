/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UC_1;

import java.io.*;

/**
 *
 * @author tony
 */
public class PSOURCE {
    public static void main(String args[]){
        try {
            // create a new process
            System.out.println("Creating Process");


            ProcessBuilder builder = new ProcessBuilder("cat", "PA2_GD/sensor.txt");
            Process pro = builder.start();

            // wait 10 seconds
            System.out.println("Waiting");
            Thread.sleep(10000);
            //InputStream inputStream = new I
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(pro.getInputStream(),"UTF-8"));
            //System.out.println(pro.info());
            System.out.println(pro.getInputStream());

            //System.out.println(in.readLine());
            String line;
            while ((line = in.readLine()) != null)
                System.out.println(line);

            // kill the process
            pro.destroy();
            System.out.println("Process destroyed");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
