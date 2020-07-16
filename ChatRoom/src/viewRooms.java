
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class viewRooms extends javax.swing.JFrame {

    ArrayList<room> al;
    mytablemodel tm;
    String columnname[] = {"Rid", "roomName", "Category", "Photo"};
int w,h;
    public viewRooms() {
        initComponents();
//          Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//        this.w = d.width;
//        this.h = d.height;
        setSize(700,700);

        al = new ArrayList<room>();
        tm = new mytablemodel();

        jTable1.setModel(tm);

      
        getContentPane().setBackground(Color.PINK);
        setLocation(100, 100);

        fetchcategories();

    }

    class mytablemodel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return al.size();

        }

        @Override
        public int getColumnCount() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return 4;
        }

        @Override
        public Object getValueAt(int i, int j) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            switch (j) {
                case 0:
                    return al.get(i).rid;
                case 1:
                    return al.get(i).roomname;
                case 2:
                    return al.get(i).category;
                case 3:
                    return al.get(i).photo;
            }
            return null;
        }

        @Override
        public String getColumnName(int i) {
            return columnname[i];
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btdelet = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        jLabel1.setText("ROOMS");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(270, 30, 120, 40);

        btdelet.setText("delete");
        btdelet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdeletActionPerformed(evt);
            }
        });
        getContentPane().add(btdelet);
        btdelet.setBounds(290, 490, 110, 40);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(80, 80, 540, 370);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btdeletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdeletActionPerformed
        // TODO add your handling code here:
       
        
         if(jTable1.getSelectedRow()==-1){
           JOptionPane.showMessageDialog(this, "please select a row to delete");
       }else{
           int ans=JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to delete");
           if(ans==JOptionPane.OK_OPTION){
               try {
                   String rid = al.get(jTable1.getSelectedRow()).rid;
                   HttpResponse<String> response1 = Unirest.get(GlobalData.hostname+"/deleterooms")
                           .queryString("rid",rid)
                           .asString();
                   JOptionPane.showMessageDialog(rootPane, response1.getBody());
                   fetchcategories();

                  
               } catch (UnirestException ex) {
                   Logger.getLogger(ViewCategories.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
           
       }
    }//GEN-LAST:event_btdeletActionPerformed

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
            java.util.logging.Logger.getLogger(viewRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewRooms().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btdelet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void fetchcategories() {
         al.clear();
        try {
            //hrow new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            HttpResponse<String> response = Unirest.get(GlobalData.hostname + "/FetchAllRooms")
                    .asString();
            String ans = response.getBody();
            StringTokenizer st = new StringTokenizer(ans, "**");
            while (st.hasMoreTokens()) {
                String row = st.nextToken();
                System.out.println("single row" + row);
                StringTokenizer col = new StringTokenizer(row, "~~");
                String rid = col.nextToken();
                System.out.println("Rid" + rid);
                String roomname = col.nextToken();
                System.out.println("roomname" + roomname);
                String photo = col.nextToken();
                String category = col.nextToken();
                al.add(new room(rid, roomname, category, photo));

            }
            jTable1.getColumnModel().getColumn(3).setCellRenderer(new ImageRenderer());
            jTable1.setRowHeight(100);
            tm.fireTableDataChanged();

        } catch (UnirestException ex) {
            Logger.getLogger(viewRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class ImageRenderer extends DefaultTableCellRenderer {

        JLabel lbl = new JLabel();

        ImageIcon icon = new ImageIcon("");
        BufferedImage bufferedImage, newimage;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            try {
                URL url = new URL(GlobalData.hostname + "/GetResource/" + al.get(row).photo);
                System.out.println("url : " + url);
                bufferedImage = ImageIO.read(url);
                newimage = resizephoto(bufferedImage, 100, 100);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            icon = new ImageIcon(newimage);
            lbl.setIcon(icon);
            lbl.setBounds(0, 0, 100, 100);
            return lbl;
        }

    }

    BufferedImage resizephoto(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }
}
