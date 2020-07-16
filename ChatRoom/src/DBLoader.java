

import java.sql.*;

public class DBLoader {

static ResultSet executeQuery(String sqlstatement){    
try{
              Class.forName("com.mysql.jdbc.Driver");
             System.out.println("driver loaded successfully");
                   
            Connection conn =
            DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chatroom", "root", "system");
            System.out.println("connection built");
    
            
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            System.out.println("ststement created ");
                
            ResultSet rs = stmt.executeQuery(sqlstatement );
            System.out.println("resultset created");
            
            return rs;

}
catch(Exception e){
   
    e.printStackTrace();
      return null;
}
}
}
