
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.*;

public class ViewCategories extends javax.swing.JFrame {
int w,h;
    ArrayList<AllCategories> al;

    mytablemodel tm;
    String columName[] = {"Categories", "Description"};

    public ViewCategories() {
        initComponents();
        
//        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//        this.w = d.width;
//        this.h = d.height;
        setSize(800,800);

        setLocation(100, 100);
        al = new ArrayList<>();
        tm = new mytablemodel();
        jTable1.setModel(tm);
        fetchcategories();
        getContentPane().setBackground(Color.PINK);

    }

    private void fetchcategories() {
//        jTable1.removeAll();
       
       try {
            HttpResponse<String> response = Unirest.get(GlobalData.hostname + "/FetchCategories")
                    .asString();
            if (response.getStatus() == 200) {
                 al.clear();
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
                    String desc = col.nextToken();
                    System.out.println("desc"+desc);
                    al.add(new AllCategories(catname, desc));
                }
            }
            tm.fireTableDataChanged();

        } catch (UnirestException ex) {
            Logger.getLogger(ViewCategories.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    class mytablemodel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return al.size();

        }

        @Override
        public int getColumnCount() {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return 2;
        }

        @Override
        public Object getValueAt(int i, int j) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            if (j == 0) {
                return al.get(i).name;
            } else if (j == 1) {
                return al.get(i).description;
            }
            return "null";

        }

        @Override
        public String getColumnName(int col) {
            return columName[col];
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

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
        jScrollPane1.setBounds(90, 110, 520, 360);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("categories ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(220, 30, 230, 60);

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(270, 500, 180, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(jTable1.getSelectedRow()==-1){
           JOptionPane.showMessageDialog(this, "please select a row to delete");
       }else{
           int ans=JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to delete");
           if(ans==JOptionPane.OK_OPTION){
               try {
                   String catname = al.get(jTable1.getSelectedRow()).name;
                   HttpResponse<String> response1 = Unirest.get(GlobalData.hostname+"/deletecategory")
                           .queryString("categoryname",catname)
                           .asString();
                   JOptionPane.showMessageDialog(rootPane, response1.getBody());
                   fetchcategories();

                  
               } catch (UnirestException ex) {
                   Logger.getLogger(ViewCategories.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
           
       }
       
       
        
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(ViewCategories.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCategories.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCategories.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCategories.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewCategories().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
