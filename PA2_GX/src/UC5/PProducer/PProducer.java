/*
 * ver package-info.java
 */
package UC5.PProducer;

import UC3.PProducer.readSocket;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author omp
 */
public class PProducer extends javax.swing.JFrame {

    /**
     * Creates new form PProducer
     */
    public PProducer() throws IOException {
        initComponents();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Thread1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("PProducer");

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        jLabel5.setText("UC5");

        jLabel6.setText("Main Thread Status:");

        Thread1.setText("Idle");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Thread1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 143, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(1, 1, 1)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel6)
                    .addComponent(Thread1))
                .addGap(129, 129, 129))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          Thread1.setText("Sending Data (Port:7777)");
          SwingWorker worker = new SwingWorker<Boolean, Integer>() {
          @Override
          protected Boolean doInBackground() throws Exception {
            ServerSocket sConsumer2 = null;
                try {
                    sConsumer2 = new ServerSocket(7777);
                    sConsumer2.setReceiveBufferSize(200);           
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
                                        return true;
                                    }
                                    Thread1.setText(a);   
                                    System.out.println(a);
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

          protected void process(Integer chunks) {
          }

          @Override
          protected void done() {
            Thread1.setText("No More Incoming Data!");  
          }
        };
        worker.execute();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PProducer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PProducer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PProducer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PProducer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PProducer().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(PProducer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Thread1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
