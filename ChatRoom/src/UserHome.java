
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class UserHome extends javax.swing.JFrame {

    ArrayList<AllCategories> al = new ArrayList<>();
    int w, h;

    public UserHome() {
        initComponents();
       
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.w = d.width;
        this.h = d.height;
        setSize(d);
        getContentPane().setBackground(Color.yellow);
        getnameandphoto();
        getallcategories();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbphoto = new javax.swing.JLabel();
        lbuser = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainpanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        lbphoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        getContentPane().add(lbphoto);
        lbphoto.setBounds(30, 30, 230, 190);

        lbuser.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        getContentPane().add(lbuser);
        lbuser.setBounds(290, 30, 840, 100);

        mainpanel.setLayout(null);
        jScrollPane1.setViewportView(mainpanel);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 280, 1340, 400);

        jButton1.setText("MyRooms");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(580, 180, 160, 70);

        jButton2.setText("Change Password");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(760, 180, 190, 70);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new myrooms().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         new ChangePassword().setVisible(true);
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
            java.util.logging.Logger.getLogger(UserHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbphoto;
    private javax.swing.JLabel lbuser;
    private javax.swing.JPanel mainpanel;
    // End of variables declaration//GEN-END:variables

    private void getnameandphoto() {
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String username = GlobalData.nameofuser;
            HttpResponse<String> response = Unirest.get(GlobalData.hostname + "/GetNameAndPhoto")
                    .queryString("username", username)
                    .asString();

            String ans = response.getBody();
            System.out.println(ans);
            StringTokenizer st = new StringTokenizer(ans, "~~");

            String DisplayName = st.nextToken();
            String Photo = st.nextToken();
            lbuser.setText("Welcome  " + DisplayName);
            BufferedImage bufferedImage, newimage;
            Icon icon;

            URL url = new URL(GlobalData.hostname + "/GetResource/" + Photo);
            System.out.println("url : " + url);
            bufferedImage = ImageIO.read(url);
            newimage = resizephoto(bufferedImage, lbphoto.getWidth(), lbphoto.getHeight());

            icon = new ImageIcon(newimage);
            lbphoto.setIcon(icon);

        } catch (Exception ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
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

    private void getallcategories() { 
        try {
            int x = 10, y = 10;
            HttpResponse<String> response = Unirest.get(GlobalData.hostname + "/FetchCategories")
                    .asString();
            al.clear();
            if (response.getStatus() == 200) {
                String ans = response.getBody().trim();
                System.out.println(ans);
                System.out.println(ans);
                StringTokenizer st = new StringTokenizer(ans, "**");
                int count = st.countTokens();
                while (st.hasMoreTokens()) {
                    String row = st.nextToken();
                    StringTokenizer col = new StringTokenizer(row, "~~");
                    String catname = col.nextToken();
                    String desc = col.nextToken();
                    al.add(new AllCategories(catname, desc));
                }
            }
            for (int i = 0; i < al.size(); i++) {
                categorypanel cp[] = new categorypanel[al.size()];
                cp[i] = new categorypanel();
                cp[i].jScrollPane1.setSize((w - 200), 300);
                cp[i].setBounds(x, y, w - 50, 400); //edit
//            cp[i].mainpanel.setPreferredSize(new Dimension(w - 50, 300));
                cp[i].lbcategoryname.setText(al.get(i).name);
                y = y + 420;

                try {
                    HttpResponse<String> httpResponse = Unirest.get(GlobalData.hostname + "/GetRooms")
                            .queryString("category", al.get(i).name)
                            .asString();
                    String ans = httpResponse.getBody().trim();
                    System.out.println(ans);
                    int j = 0, m = 10, n = 10;
                    StringTokenizer st = new StringTokenizer(ans, ";;");
                    int count = st.countTokens();
                    roomspanel rp[] = new roomspanel[count];

                    while (st.hasMoreTokens()) {
                        String row = st.nextToken();
                        System.out.println(row);
                        StringTokenizer stcol = new StringTokenizer(row, "~~");
                        String roomid = stcol.nextToken();
                        String roomname = stcol.nextToken();
                        String category = stcol.nextToken();
                        String photo = stcol.nextToken();
                        rp[j] = new roomspanel();
                        rp[j].lbroomname.setText(roomname);
                        BufferedImage bufferedImage, newimage = null;
                        ImageIcon icon = new ImageIcon("");
                        try {
                            URL url = new URL(GlobalData.hostname + "/GetResource/" + photo);
                            System.out.println("url : " + url);
                            bufferedImage = ImageIO.read(url);
                            newimage = resizephoto(bufferedImage, rp[j].lbpreview.getWidth(), rp[j].lbpreview.getHeight());
                            rp[j].setBounds(m, n, 200, 200);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        icon = new ImageIcon(newimage);
                        rp[j].lbpreview.setIcon(icon);
                        rp[j].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent me) {
                                if (me.getClickCount() == 2) {
                                    JoinRoom jg = new JoinRoom(Integer.parseInt(roomid));
                                    jg.setVisible(true);
                                }
                            }

                        });
//                    cp[i].jScrollPane1.setSize((j*250), 300);
                        cp[i].mainpanel.setPreferredSize(new Dimension((j * 250), 300));
                        cp[i].mainpanel.add(rp[j]);
                        mainpanel.add(cp[i]);
                        repaint();
                        mainpanel.repaint();
                        cp[i].repaint();
                        cp[i].mainpanel.repaint();
                        System.out.println(x);
                        System.out.println(y);

//                    y = y + 200;
                        x = 10;
                        m = m + 250;
                        j++;
                        mainpanel.setPreferredSize(new Dimension(w, y));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (UnirestException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
