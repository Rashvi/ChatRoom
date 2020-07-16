
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Adjustable;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JoinRoom extends javax.swing.JFrame {

    int rid;
    File file, file2;
    String emojipics[] = new String[]{"src/emoji/1.jpg", "src/emoji/2.png", "src/emoji/3.jpg", "src/emoji/4.jpg", "src/emoji/5.jpg", "src/emoji/6.jpg", "src/emoji/7.jpg", "src/emoji/8.jpg", "src/emoji/9.jpg", "src/emoji/10.png"};

    String emojiSymbole[] = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    Timer timer;
    int w, h;

    public JoinRoom(int rid) {

        initComponents();
        this.rid = rid;
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.w = d.width;
        this.h = d.height;
        setSize(d);
        setVisible(true);
        timer = new Timer();
        getroomdetails(rid);
        checkroomjoined(rid);
        GetRoomMemeber(rid);
        setemojis();
        Thread t1 = new Thread(new MyClass());
        t1.start();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                new Thread(new MyClass()).start();
            }
        }, 5000, 5000);

        getContentPane().setBackground(Color.PINK);
    }

//    JoinRoom(String roomid) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btaddmsg = new javax.swing.JButton();
        lbPhoto = new javax.swing.JLabel();
        lbCategory = new javax.swing.JLabel();
        lbRoomName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        showmessage = new javax.swing.JEditorPane();
        tfMsg = new javax.swing.JTextField();
        btaddimage = new javax.swing.JButton();
        btaddfile = new javax.swing.JButton();
        joinbt = new javax.swing.JButton();
        mainpanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        btaddmsg.setText("Add");
        btaddmsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaddmsgActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        lbPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(lbPhoto);
        lbPhoto.setBounds(20, 30, 280, 140);

        lbCategory.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        getContentPane().add(lbCategory);
        lbCategory.setBounds(30, 260, 280, 40);

        lbRoomName.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        getContentPane().add(lbRoomName);
        lbRoomName.setBounds(20, 190, 280, 40);

        jScrollPane1.setViewportView(showmessage);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(320, 30, 1240, 460);

        tfMsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMsgActionPerformed(evt);
            }
        });
        getContentPane().add(tfMsg);
        tfMsg.setBounds(320, 510, 730, 50);

        btaddimage.setText("Select Image");
        btaddimage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaddimageActionPerformed(evt);
            }
        });
        getContentPane().add(btaddimage);
        btaddimage.setBounds(1220, 520, 130, 40);

        btaddfile.setText("Select File");
        btaddfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaddfileActionPerformed(evt);
            }
        });
        getContentPane().add(btaddfile);
        btaddfile.setBounds(1360, 520, 140, 40);

        joinbt.setText("Join Room");
        joinbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinbtActionPerformed(evt);
            }
        });
        getContentPane().add(joinbt);
        joinbt.setBounds(30, 320, 220, 40);

        mainpanel.setBackground(new java.awt.Color(204, 255, 255));
        getContentPane().add(mainpanel);
        mainpanel.setBounds(330, 590, 1170, 90);

        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(1070, 520, 140, 40);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("\n");
        jScrollPane2.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(30, 360, 220, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btaddmsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaddmsgActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_btaddmsgActionPerformed

    private void btaddfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaddfileActionPerformed
        // TODO add your handling code here:
        JFileChooser jfc2 = new JFileChooser("C:\\");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf", "epub", "txt", "docx", "doc");// to seclect only specific formate file
        jfc2.setFileFilter(filter);
        jfc2.setAcceptAllFileFilterUsed(false);  //tp set enable to other filter 

        int returnVal = jfc2.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                file2 = jfc2.getSelectedFile();

                HttpResponse<String> response = Unirest.post(GlobalData.hostname + "/AddFileMsg")
                        .queryString("Rid", rid)
                        .queryString("UserName", GlobalData.nameofuser)
                        .queryString("MsgType", "file")
                        .field("Msg", file2)
                        .asString();

                String ans = "";
                ans = response.getBody();
                JOptionPane.showMessageDialog(this, ans);

            } catch (UnirestException ex) {
                Logger.getLogger(JoinRoom.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_btaddfileActionPerformed

    private void tfMsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMsgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMsgActionPerformed

    private void btaddimageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaddimageActionPerformed
        // TODO add your handling code here:

        JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpeg", "jpg", "bmp", "png");// to seclect only specific formate file
        jfc.setFileFilter(filter);
        jfc.setAcceptAllFileFilterUsed(false);  //tp set enable to other filter 
        int returnVal = jfc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                file = jfc.getSelectedFile();
                HttpResponse<String> response = Unirest.post(GlobalData.hostname + "/AddFileMsg")
                        .queryString("Rid", rid)
                        .queryString("UserName", GlobalData.nameofuser)
                        .queryString("MsgType", "photo")
                        .field("Msg", file)
                        .asString();
                JOptionPane.showMessageDialog(this, response.getBody());
            } catch (UnirestException ex) {
                Logger.getLogger(JoinRoom.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_btaddimageActionPerformed

    private void joinbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinbtActionPerformed
        // TODO add your handling code here:
        String UserName = GlobalData.nameofuser;

        try {
            String ans = "";
            HttpResponse<String> response = Unirest.get(GlobalData.hostname + "/JoinRoom")
                    .queryString("UserName", UserName)
                    .queryString("Rid", rid)
                    .asString();
            ans = response.getBody();
            System.out.println("RESPONSE OF JOINROOM BUTTON" + ans);
            if (ans.equals("yes")) {
                joinbt.setEnabled(false);
                joinbt.setText("Joined");
                showmessage.setVisible(true);
                tfMsg.setVisible(true);
                btaddfile.setVisible(true);
                btaddimage.setVisible(true);
                btaddmsg.setVisible(true);
                jButton1.setVisible(true);
                jTextArea1.setVisible(true);
                mainpanel.setVisible(true);

            }
        } catch (UnirestException ex) {
            Logger.getLogger(JoinRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_joinbtActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String Msg = tfMsg.getText();
        String username = GlobalData.nameofuser;
        try {
            HttpResponse<String> response = Unirest.get(GlobalData.hostname + "/AddMsg")
                    .queryString("Msg", Msg)
                    .queryString("MsgType", "text")
                    .queryString("username", username)
                    .queryString("rid", rid)
                    .asString();

            JOptionPane.showMessageDialog(this, response.getBody());

        } catch (UnirestException ex) {
            Logger.getLogger(JoinRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(JoinRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JoinRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JoinRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JoinRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new JoinRoom().setVisible(true);
            }
        });
    }

    private void getroomdetails(int rid) {

        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            HttpResponse<String> response = Unirest.get(GlobalData.hostname + "/GetRoomDetails")
                    .queryString("rid", rid)
                    .asString();

            String ans = response.getBody();
            System.out.println(ans);
            StringTokenizer st = new StringTokenizer(ans, "~~");
            while (st.hasMoreTokens()) {
                String RoomName = st.nextToken();
                String Category = st.nextToken();
                String Photo = st.nextToken();
                lbRoomName.setText("Room Name:" + RoomName);
                lbCategory.setText("Category:" + Category);
                BufferedImage bufferedImage, newimage;
                Icon icon;
                URL url;
                try {
                    url = new URL(GlobalData.hostname + "/GetResource/" + Photo);
                    System.out.println("url : " + url);
                    bufferedImage = ImageIO.read(url);
                    newimage = resizephoto(bufferedImage, lbPhoto.getWidth(), lbPhoto.getHeight());

                    icon = new ImageIcon(newimage);
                    lbPhoto.setIcon(icon);
                } catch (Exception ex) {
                    Logger.getLogger(JoinRoom.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (UnirestException ex) {
            Logger.getLogger(JoinRoom.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btaddfile;
    private javax.swing.JButton btaddimage;
    private javax.swing.JButton btaddmsg;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton joinbt;
    private javax.swing.JLabel lbCategory;
    private javax.swing.JLabel lbPhoto;
    private javax.swing.JLabel lbRoomName;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JEditorPane showmessage;
    private javax.swing.JTextField tfMsg;
    // End of variables declaration//GEN-END:variables
 BufferedImage resizephoto(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }

    private void checkroomjoined(int rid) {
        System.out.println("chkroomjoined called");
        try {
            String username = GlobalData.nameofuser;
            HttpResponse<String> response = Unirest.get(GlobalData.hostname + "/CheckJoin")
                    .queryString("Rid", rid)
                    .queryString("UserName", username)
                    .asString();
            String ans = response.getBody();
            System.out.println("RESPONSE OF CHECK ROOM JOINED" + ans);
            if (ans.equals("yes")) {
                joinbt.setEnabled(false);
                joinbt.setText("Joined");
                showmessage.setVisible(true);
                tfMsg.setVisible(true);
                btaddfile.setVisible(true);
                btaddimage.setVisible(true);
                btaddmsg.setVisible(true);
                jButton1.setVisible(true);
                jTextArea1.setVisible(true);

            } else {
                showmessage.setVisible(false);
                tfMsg.setVisible(false);
                btaddfile.setVisible(false);
                btaddimage.setVisible(false);
                btaddmsg.setVisible(false);
                mainpanel.setVisible(false);
                jButton1.setVisible(false);
                jTextArea1.setVisible(false);

            }

        } catch (UnirestException ex) {
            Logger.getLogger(JoinRoom.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setemojis() {

        JLabel lb[] = new JLabel[emojiSymbole.length];
        for (int i = 0; i < emojiSymbole.length; i++) {
            final int j = i;
            try {
                lb[i] = new JLabel();
                BufferedImage img = ImageIO.read(new File(emojipics[i]));
                //Image newimg = img.getScaledInstance(lbpreview.getWidth(), lbpreview.getHeight(), Image.SCALE_SMOOTH);
                BufferedImage newimg = resizephoto(img, 50, 50);
                //lbfilename.setText(file.getName());
                lb[i].setIcon(new ImageIcon(newimg));
                String sm = emojiSymbole[i];
                mainpanel.add(lb[i]);
                lb[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        String username = GlobalData.nameofuser;
                        try {
                            HttpResponse<String> response = Unirest.get(GlobalData.hostname + "/AddMsg")
                                    .queryString("Msg", sm)
                                    .queryString("MsgType", "emoji")
                                    .queryString("username", username)
                                    .queryString("rid", rid)
                                    .asString();
                            JOptionPane.showMessageDialog(rootPane, response.getBody());

                        } catch (UnirestException ex) {
                            Logger.getLogger(JoinRoom.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                });
            } catch (IOException ex) {
                Logger.getLogger(JoinRoom.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void scrollToBottom(JScrollPane scrollPane) {
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }

        };
        verticalBar.addAdjustmentListener(downScroller);
    }

    private void GetRoomMemeber(int rid) {
        try {
            HttpResponse<String> response = Unirest.get(GlobalData.hostname + "/GetRoomMember")
                    .queryString("roomid", rid)
                    .asString();
            String row = "";
            String ans = response.getBody();
            StringTokenizer st = new StringTokenizer(ans, "~~");
            while (st.hasMoreTokens()) {
                row = row + st.nextToken() + "\n";
            }
            jTextArea1.setText(row);

        } catch (UnirestException ex) {
            Logger.getLogger(JoinRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class MyClass implements Runnable {

        @Override
        public void run() {
            try {
                HttpResponse<String> response = Unirest.get(GlobalData.hostname + "/fetchmessages")
                        .queryString("roomid", rid)
                        .asString();
                if (response.getStatus() == 200) {
                    String ans = response.getBody();
                    System.out.println("ans::" + ans);
                    StringTokenizer st = new StringTokenizer(ans, ";;");
                    String msg = "";
                    showmessage.setContentType("text/html");
                    while (st.hasMoreTokens()) {
                        String row = st.nextToken();
                        StringTokenizer col = new StringTokenizer(row, "~~");
                        String message = col.nextToken();
                        String postedby = col.nextToken();
                        String datetime = col.nextToken();
                        String dispname = col.nextToken();
                        String msgtype = col.nextToken();
//                    imgsrc = new File("passport.jpg").toURL().toExternalForm();
                        if (msgtype.equals("text")) {
                            if (postedby.equals(GlobalData.nameofuser)) {
                                msg += "<div style='text-align: right;background-color: #FFC0CB;margin-left:200px;margin-bottom: 10px;border: #000 solid medium'><h3><b>" + message + "</b></h3>";
                                msg += "<p>" + dispname + " " + datetime + "</p></div>";
                            } else {
                                msg += "<div style='text-align: left;background-color: #4FFFCE4;margin-right:200px;margin-bottom: 10px;border: #000 solid medium'><h3><b>" + message + "</b></h3>";
                                msg += "<p>" + dispname + " " + datetime + "</p></div>";
                            }
                        } else if (msgtype.equals("photo")) {
                            if (postedby.equals(GlobalData.nameofuser)) {
                                msg += "<div style='text-align: right;background-color: #FFC0CB;margin-left:200px;margin-bottom: 10px;border: #000 solid medium'><img src='file:" + message + "' width='200' height='200' />";
                                msg += "<p>" + dispname + " " + datetime + "</p></div>";
                            } else {
                                msg += "<div style='text-align: left;background-color: #4FFFCE4;margin-bottom: 10px;margin-right:200px;border: #000 solid medium'><img src='file:" + message + "' width='200' height='200' />";
                                msg += "<p>" + dispname + " " + datetime + "</p></div>";
                            }
                        } else if (msgtype.equals("emoji")) {
                            for (int i = 0; i < emojiSymbole.length; i++) {
                                if (message.equals(emojiSymbole[i])) {
                                    if (postedby.equals(GlobalData.nameofuser)) {
                                        msg += "<div style='text-align: right;background-color: #FFC0CB;margin-bottom: 10px;margin-left:200px;border: #000 solid medium'><img src='file:" + emojipics[i] + "' width='50' height='50' />";
                                        msg += "<p>" + dispname + " " + datetime + "</p></div>";
                                    } else {
                                        msg += "<div style='text-align: left;background-color: #4FFFCE4;margin-bottom: 10px;margin-right:200px;border: #000 solid medium'><img src='file:" + emojipics[i] + "' width='50' height='50' />";
                                        msg += "<p>" + dispname + " " + datetime + "</p></div>";
                                    }
                                }

                            }
                        } else if (msgtype.equals("file")) {

                            if (postedby.equals(GlobalData.nameofuser)) {
                                msg += "<div  style='text-align: right;background-color: #FFC0CB;margin-bottom: 10px;margin-left:200px;border: #000 solid medium'><a href=http://'" + message + "'> <img src='file:src/emojis/doc.jfif' width='70' height='70' /></a>";
                                msg += "<p>" + dispname + " " + datetime + "</p></div>";
                            } else {
                                msg += "<div  style='text-align: left;background-color: #4FFFCE4;margin-bottom: 10px;margin-right:200px;border: #000 solid medium'><a href=http://'" + message + "'><img src='file:src/emojis/doc.jfif' width='70' height='70' /></a>";
                                msg += "<p>" + dispname + " " + datetime + "</p></div>";

                            }
                        }
                    }
                    showmessage.setText(msg);
                    showmessage.setEditable(false);
                    scrollToBottom(jScrollPane1);

                    showmessage.addHyperlinkListener(new HyperlinkListener() {

                        @Override
                        public void hyperlinkUpdate(HyperlinkEvent he) {
                            if (he.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                                URL value = he.getURL();
                                String val = value.toString();
                                String extension = val.substring(val.indexOf("."));
                                String path = val.substring(val.indexOf("/") + 2);
                                System.out.println("Actual URL" + he.getURL());
                                int ans = JOptionPane.showConfirmDialog(rootPane, "do you want to download the file??");
                                if (ans == JOptionPane.YES_OPTION) {
                                    try {
                                        HttpResponse<InputStream> response = Unirest.get(GlobalData.hostname + "/GetResource/" + path)
                                                .asBinary();
                                        InputStream is = response.getBody();
                                        FileOutputStream fos;
                                        System.out.println("responsebody is" + is);
                                        String fname = new Date().getTime() + "" + extension;
                                        fos = new FileOutputStream(System.getProperty("user.home") + "/Downloads/" + fname);
                                        long contentlength = Integer.parseInt(response.getHeaders().getFirst("Content-Length"));
                                        byte b[] = new byte[10000];
                                        int r;
                                        long count = 0;
                                        while (true) {
                                            r = is.read(b, 0, 10000);
                                            fos.write(b, 0, r);
                                            count = count + r;
                                            System.out.println(count * 100 / contentlength + " %");
                                            if (count == contentlength) {
                                                break;
                                            }
                                        }
                                        fos.close();
                                        System.out.println("fikle downloaded");
                                        System.out.println("File Downloaded....");
                                        File f = new File(System.getProperty("user.home") + "\\Downloads\\" + fname);
                                        URI u = f.toURI();
                                        Desktop d = Desktop.getDesktop();
                                        d.browse(u);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        }

                    }
                    );

                }
            } catch (UnirestException ex) {
                ex.printStackTrace();
            }
        }
    }
}
