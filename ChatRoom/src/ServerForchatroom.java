

import com.vmm.JHTTPServer;
import com.vmm.NanoHTTPD;
import static com.vmm.NanoHTTPD.HTTP_OK;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

class ServerForchatroom extends JHTTPServer {

    public ServerForchatroom(int port) throws IOException {

        super(port);
    }
    
    @Override
    public Response serve(String uri, String method, Properties header,
            Properties parms, Properties files) {
        Response res = null;

        System.out.println("URI " + uri);

        if (uri.contains("/GetResource")) {
            uri = uri.substring(1);
            uri = uri.substring(uri.indexOf("/") + 1);
            System.out.println("********* " + uri + "**********");
            res = sendCompleteFile(uri);
        } else if (uri.contains("/AdminLogin")) {
            String answer = null;
            String username = parms.getProperty("username");
            String password = parms.getProperty("password");

            ResultSet rs = DBLoader.executeQuery("select * from AdminLogin where username = \'" + username + "\' and password = \'" + password + "\'");

            try {
                if (rs.next()) {
                    answer = "loggin successfully!!!!!!!!!!";

                } else {
                    answer = "Login Faild !!!!!!!!!!!!!";

                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);
            }
            res = new Response(HTTP_OK, "text/plain", answer);
        } else if (uri.contains("/Addcategories")) {
            String answer = null;
            System.out.println("entered in addcategories");
            String name = parms.getProperty("name");
            String descrip = parms.getProperty("des");
            System.out.println(name + ",,," + descrip);
            ResultSet rs = DBLoader.executeQuery("select * from category where categoryName = \'" + name + "\'");

            try {
                if (rs.next()) {
                    System.out.println("cat exist");
                    answer = "Category Already Exit";

                } else {
                    System.out.println("adding row");
                    rs.moveToInsertRow();

                    rs.updateString("categoryName", name);
                    rs.updateString("description", descrip);

                    rs.insertRow();

                    answer = "Category Added";

                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);
            }

            res = new Response(HTTP_OK, "text/plain", answer);
        } else if (uri.contains("/FetchCategories")) {

            String answer = "";
            ResultSet rs = DBLoader.executeQuery("select * from category ");

            try {
                while (rs.next()) {
                    String name = rs.getString("categoryname");
                    String des = rs.getString("description");

                    answer = answer + name + "~~" + des + "**";

                }

                res = new Response(HTTP_OK, "text/plain", answer);

            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.contains("/deletecategory")) {
            String answer = "";
            String category = parms.getProperty("categoryname");
            System.out.println("''''''''''''''''''''''" + category);
            ResultSet rs = DBLoader.executeQuery("select * from category where categoryName = \'" + category + "\'");

            try {
                if (rs.next()) {
                    rs.deleteRow();
                    answer = "row deleted!!!!";
                    res = new Response(HTTP_OK, "text/plain", answer);
                } else {
                    answer = "selected row doesnot exist";
                    res = new Response(HTTP_OK, "text/plain", answer);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("/AddRooms")) {
            String roomname = parms.getProperty("roomName");
            String category = parms.getProperty("category");
            String filename = saveFileOnServerWithRandomName(files, parms, "photo", "src/pics");   //return name of the file 

            ResultSet rs = DBLoader.executeQuery("select * from rooms ");

            try {
                String ans;
                rs.next();
                rs.moveToInsertRow();
                rs.updateString("roomName", roomname);
                rs.updateString("category", category);
                rs.updateString("photo", "src/pics/" + filename);

                rs.insertRow();
                ans = "data successfully added into database";
                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("/FetchAllRooms")) {

            ResultSet rs = DBLoader.executeQuery("select * from rooms");
            String ans = "";
            try {
                while (rs.next()) {
                    String rid = rs.getString("rid");
                    String roomname = rs.getString("roomName");
                    String photo = rs.getString("photo");
                    String category = rs.getString("category");

                    ans = ans + rid + "~~" + roomname + "~~" + photo + "~~" + category + "**";

                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);
            }

            res = new Response(HTTP_OK, "text/plain", ans);

        } else if (uri.contains("/deleterooms")) {
            String rid = parms.getProperty("rid");

            String answer = "";

            System.out.println("''''''''''''''''''''''" + rid);
            ResultSet rs = DBLoader.executeQuery("select * from rooms where rid = \'" + rid + "\'");

            try {
                if (rs.next()) {
                    rs.deleteRow();
                    answer = "row deleted!!!!";
                    res = new Response(HTTP_OK, "text/plain", answer);
                } else {
                    answer = "selected row doesnot exist";
                    res = new Response(HTTP_OK, "text/plain", answer);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.contains("/AddUser")) {
            String UserName = parms.getProperty("un");
            String Password = parms.getProperty("ps");
            String DisplayName = parms.getProperty("dn");
            String Gender = parms.getProperty("gen");
            String Email = parms.getProperty("e");
            String Phone = parms.getProperty("pn");
            String filename = saveFileOnServerWithRandomName(files, parms, "Photo", "src/pics"); //return name of the file
            String answer = "";
            System.out.println(UserName + " " + Password + " " + DisplayName + " " + Gender + " " + filename + " " + Email + " " + " " + Phone + " ");

            ResultSet rs = DBLoader.executeQuery("select * from usersignup where UserName =\'" + UserName + "\'");

            try {
                if (rs.next()) {
                    answer = "userName already Taken!!!!!!!!!!!";
                    res = new Response(HTTP_OK, "text/plain", answer);
                } else {
                    rs.moveToInsertRow();

                    rs.updateString("Username", UserName);
                    rs.updateString("Password", Password);
                    rs.updateString("DisplayName", DisplayName);
                    rs.updateString("Gender", Gender);
                    rs.updateString("Email", Email);
                    rs.updateString("Photo", "src/pics/" + filename);
                    rs.updateString("Mobile", Phone);

                    rs.insertRow();
                    answer = "SignUp successfully !!!!!!!!!!!";
                    res = new Response(HTTP_OK, "text/plain", answer);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("/UserLogin")) {
            String answer = "";
            String UserName = parms.getProperty("username");
            String Password = parms.getProperty("password");
            ResultSet rs = DBLoader.executeQuery("select * from usersignup where UserName = \'" + UserName + "\' and Password = \'" + Password + "\'");

            try {
                if (rs.next()) {

                    answer = "Login successfully !!!!!!!!!!!";
                    res = new Response(HTTP_OK, "text/plain", answer);

                } else {
                    answer = "login faild!!!!!!!!!!!!";
                    res = new Response(HTTP_OK, "text/plain", answer);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("/GetNameAndPhoto")) {
            String ans = "";
            String UserName = parms.getProperty("username");

            ResultSet rs = DBLoader.executeQuery("select * from usersignup where UserName =  \'" + UserName + "\'");

            try {
                rs.next();
                String DisplayName = rs.getString("DisplayName");
                String Photo = rs.getString("Photo");
                ans = ans + DisplayName + "~~" + Photo;
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("/GetRoomDetails")) {
            String ans = "";
            String Rid = parms.getProperty("rid");

            ResultSet rs = DBLoader.executeQuery("select * from rooms where Rid =" + Rid);
            try {
                if (rs.next()) {
                    String RoomName = rs.getString("roomName");
                    String Category = rs.getString("category");
                    String Photo = rs.getString("photo");

                    ans = ans + RoomName + "~~" + Category + "~~" + Photo;

                    res = new Response(HTTP_OK, "text/plain", ans);
                }

            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("/AddMsg")) {
            String Msg = parms.getProperty("Msg");
            String RoomId = parms.getProperty("rid");
            String PostedBy = parms.getProperty("username");
            String MsgType = parms.getProperty("MsgType");

            ResultSet rs = DBLoader.executeQuery("select * from message ");

            String ans = "";
            try {
                rs.moveToInsertRow();
                rs.updateString("Message", Msg);
                rs.updateString("RoomId", RoomId);
                rs.updateString("Postedby", PostedBy);
                rs.updateString("MessageType", MsgType);
                rs.insertRow();

                ans = " Data Added Successfully ";
                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);

            }

        } else if (uri.contains("/checkLogin")) {
            String rid = parms.getProperty("rid");
            String username = parms.getProperty("username");

            ResultSet rs = DBLoader.executeQuery("select *from myrooms  where username = \'" + username + "\' and roomid = \'" + rid + "\'");
        } else if (uri.contains("/GetRooms")) {
            String category = parms.getProperty("category");
            String ans = "";
            try {
                ResultSet rs = DBLoader.executeQuery("select * from rooms where category='" + category + "'");
                while (rs.next()) {
                    String rid = rs.getString("rid");
                    String roomname = rs.getString("roomname");
                    String catname = rs.getString("category");
                    String photo = rs.getString("photo");
                    ans = ans + rid + "~~" + roomname + "~~" + catname + "~~" + photo + ";;";
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (uri.contains("/CheckJoin")) {

            String UserName = parms.getProperty("UserName");
            String Rid = parms.getProperty("Rid");
            String ans = "";
            ResultSet rs = DBLoader.executeQuery("select * from myrooms where username = \'" + UserName + "\'  and roomid = " + Rid);

            try {
                if (rs.next()) {

                    ans = "yes";

                } else {
                    ans = "no";
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.contains("/JoinRoom")) {
            String Username = parms.getProperty("UserName");
            String Rid = parms.getProperty("Rid");

            ResultSet rs = DBLoader.executeQuery("select * from myrooms where username = '" + Username + "' and roomid = " + Rid);
            String ans = "";
            try {

                rs.moveToInsertRow();
                rs.updateString("username", Username);
                rs.updateString("roomid", Rid);

                rs.insertRow();
                ans = "yes";
            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);
            }
            res = new Response(HTTP_OK, "text/plain", ans);

        } else if (uri.contains("/GetMyRooms")) {
            String ans = "";
            String UserName = parms.getProperty("UserName");

            ResultSet rs = DBLoader.executeQuery("select * from myrooms  where username =\'" + UserName + "\'");

            try {

                while (rs.next()) {
                    String Rid = rs.getString("roomid");
                    ResultSet rs2 = DBLoader.executeQuery("select * from rooms  where Rid = " + Rid);
                    if (rs2.next()) {
                        String RoomName = rs2.getString("roomName");
                        String Photo = rs2.getString("photo");
                        String Category = rs2.getString("category");
                        System.out.println(RoomName);
                        System.out.println(Photo);
                        System.out.println(Category);
                        ans = ans + Rid + "~~" + RoomName + "~~" + Photo + "~~" + Category + ";;";

                    }
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("/AddFileMsg")) {

            String Rid = parms.getProperty("Rid");
            String UserName = parms.getProperty("UserName");
            String MsgType = parms.getProperty("MsgType");

            String filename = saveFileOnServerWithRandomName(files, parms, "Msg", "src/pics");

            String ans = "";
            ResultSet rs = DBLoader.executeQuery("select * from message");
            try {
                rs.next();
                rs.moveToInsertRow();
                rs.updateString("RoomId", Rid);
                rs.updateString("Postedby", UserName);
                rs.updateString("MessageType", MsgType);
                rs.updateString("Message", "src/pics/" + filename);

                rs.insertRow();
                ans = "msg save ";

            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);
            }
            res = new Response(HTTP_OK, "text/plain", ans);

        } else if (uri.contains("/fetchmessages")) {
            String ans = "";
            try {
                String rid = parms.getProperty("roomid");
                ResultSet rs = DBLoader.executeQuery("select * from message where RoomId='" + rid + "'");

                while (rs.next()) {

                    String message = rs.getString("Message");
                    String postedby = rs.getString("Postedby");
                    String time = rs.getString("Date");
                    String type = rs.getString("MessageType");
                    ResultSet rs2 = DBLoader.executeQuery("select * from usersignup where UserName='" + postedby + "'");
                    if (rs2.next()) {
                        String displayname = rs2.getString("DisplayName");
                        ans = ans + message + "~~" + postedby + "~~" + time + "~~" + displayname + "~~" + type + ";;";

                    }
                }
                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (uri.contains("/UserChangePassword")) {
            String UserName = parms.getProperty("Username");
            String OldPassword = parms.getProperty("OldPassword");
            String NewPassword = parms.getProperty("NewPassword");

            String ans = "";
            ResultSet rs = DBLoader.executeQuery("select * from usersignup where UserName= \'" + UserName + "' and Password = \'" + OldPassword + "\'");
            System.out.println("Username " + UserName + " and password is " + OldPassword);

            try {
                if (rs.next()) {
                    rs.updateString("Password", NewPassword);
                    rs.updateRow();
                    System.out.println(NewPassword);
                    ans = "successfully Update!!!!!!!!!!!!!!!";
                } else {
                    ans = "username or password is invalid !!!!!!!!!";
                    System.out.println("username or passworld is invalid!!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);
            }

            res = new Response(HTTP_OK, "text/plain", ans);
        } else if (uri.contains("/GetRoomMember")) {
            String rid = parms.getProperty("roomid");
            System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ" + rid);
            String ans = "";
            ResultSet rs = DBLoader.executeQuery("select * from myrooms where roomid = " + rid);

            try {
                while (rs.next()) {
                    String UserName = rs.getString("UserName");
                    System.out.println("YYYYYYYYYYYYYYYYYYYY" + UserName);
                    ResultSet rs1 = DBLoader.executeQuery("select * from usersignup where  UserName ='" + UserName + "'");
                    System.out.println("somthing");
                    rs1.next();
                    String Dispname = rs1.getString("DisplayName");
                    System.out.println("WWWWWWWWWWWWWWWWWW" + Dispname);
                    ans = ans + Dispname + "~~";
                    System.out.println("ANS" + ans);
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (SQLException ex) {
                Logger.getLogger(ServerForchatroom.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            res = new Response(HTTP_OK, "text/plain", "Server Running");
        }

        return res;

    }

}
