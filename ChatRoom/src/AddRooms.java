
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddRooms extends javax.swing.JFrame {

    File file;

    /**
     * Creates new form AddRooms
     */
    public AddRooms() {
        initComponents();
  
       
        setLocation(100, 100);
        setSize(700, 600);
        
        getContentPane().setBackground(Color.PINK);
        fetchcategories(); 
        
        
    }
    private void fetchcategories() {
        try {   
            HttpResponse<String> response = Unirest.get(GlobalData.hostname + "/FetchCategories")
                    .asString();
                    if (response.getStatus() == 200) {
              cb1.removeAllItems();
                String ans = response.getBody();
                System.out.println(ans);
                StringTokenizer st = new StringTokenizer(ans, "**");
                int count = st.countTokens();
                while (st.hasMoreTokens()) {
                    String row = st.nextToken();
                    System.out.println("single row"+row);
                    StringTokenizer col = new StringTokenizer(row, "~~");
                    String catname = col.nextToken();
                    System.out.println("catname"+catname);
                  cb1.addItem(catname);
                  
                }
            }
            
        } catch (UnirestException ex) {
            Logger.getLogger(AddRooms.class.getName()).log(Level.SEVERE, null, ex);
        }

     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfroomname = new javax.swing.JTextField();
        lbpreview = new javax.swing.JLabel();
        cb1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("Room Name");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(60, 40, 190, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel2.setText("Photo");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 140, 160, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel3.setText("category");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 260, 140, 40);

        tfroomname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfroomnameActionPerformed(evt);
            }
        });
        getContentPane().add(tfroomname);
        tfroomname.setBounds(260, 40, 270, 40);

        lbpreview.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(lbpreview);
        lbpreview.setBounds(270, 110, 150, 120);

        cb1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb1ActionPerformed(evt);
            }
        });
        getContentPane().add(cb1);
        cb1.setBounds(270, 260, 130, 30);

        jButton1.setText("browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(430, 150, 160, 40);

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(140, 380, 170, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb1ActionPerformed

    private void tfroomnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfroomnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfroomnameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        JFileChooser jfc = new JFileChooser("C:\\");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpeg", "jpg", "bmp", "png");// to seclect only specific formate file
        jfc.setFileFilter(filter);
        jfc.setAcceptAllFileFilterUsed(false);  //tp set enable to other filter 
        int returnVal = jfc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = jfc.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(file);
                //Image newimg = img.getScaledInstance(lbpreview.getWidth(), lbpreview.getHeight(), Image.SCALE_SMOOTH);
                BufferedImage newimg = resizephoto(img, lbpreview.getWidth(), lbpreview.getHeight());
//                lbfilename.setText(file.getName());
                lbpreview.setIcon(new ImageIcon(newimg));
            } catch (Exception ex) {
                System.out.println("problem accessing file" + file.getAbsolutePath());
            }
        } else {
            System.out.println("File access cancelled by user.");
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String Rm  =   tfroomname.getText();
        String Cg = (String) cb1.getSelectedItem();
        
        try {
            HttpResponse<String> response = Unirest.post(GlobalData.hostname+"/AddRooms")
                    .queryString("roomName",Rm)
                    .queryString("category",Cg)
                    .field("photo",file )
                    .asString();
            
            JOptionPane.showMessageDialog(this, response.getBody());
        } catch (UnirestException ex) {
            Logger.getLogger(AddRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
    }//GEN-LAST:event_jButton2ActionPerformed

   
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
            java.util.logging.Logger.getLogger(AddRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddRooms().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbpreview;
    private javax.swing.JTextField tfroomname;
    // End of variables declaration//GEN-END:variables
BufferedImage resizephoto(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }

}
