/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UC_2;

import java.io.IOException;

/**
 *
 * @author tony
 */
public class PSOURCE {
    public static void main(String args[]){
        try {
            // create a new process
            System.out.println("Creating Process");

            ProcessBuilder builder = new ProcessBuilder();
            Process pro = builder.start();

            // wait 10 seconds
            System.out.println("Waiting");
            Thread.sleep(10000);


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
