
package iambored;
import java.sql.*;

public class database_connect {
    String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
    String DB_URL = "jdbc:sqlite:/Users/nic/Desktop/users.db";
    Connection conn = null;
    Statement stmt = null;
    
    public void signup(String input_username, String input_password)
    {
        
        try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL);
      System.out.println("Connected database successfully...");
      
      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();

      String sql = "Insert into customers ( username, password ) values ( '"+input_username+"','"+ input_password +"')" ;
      stmt.executeUpdate(sql);
    
      System.out.println("user : "+input_username+" created successfully!");
   
    }
        catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
        }    
        catch(Exception e){
      //Handle errors for Class.forName
        e.printStackTrace();
        }    
        finally{
      //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }     
            catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
            conn.close();
            }     
            catch(SQLException se){
            se.printStackTrace();
            }//end finally try
        }
        
        
        
    }
    public int signin(String input_username, String input_password){
    
    int results=0;
    int id=-1, data;
    String password, username;
    try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL);
      System.out.println("Connected database successfully...");
      
      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();

      String sql = "SELECT * FROM customers WHERE username = '"+input_username+ "' and password = '"+input_password+"'";
      ResultSet rs = stmt.executeQuery(sql);
      
   
      
      while(rs.next()){
          results++;
          
          username  = rs.getString("username");
          password = rs.getString("password");
          data = rs.getInt("data");
          id = rs.getInt("id");
          
          
          System.out.println("logged in to "+username);
          System.out.println("data: " + data);
          
          
      }
      rs.close();
      
   
    }
        catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
        }    
        catch(Exception e){
      //Handle errors for Class.forName
        e.printStackTrace();
        }    
        finally{
      //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }     
            catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
            conn.close();
            }     
            catch(SQLException se){
            se.printStackTrace();
            }//end finally try
        }
      if( results == 0 ) {System.out.println("login failed"); return -1;}
      else return id;
    }

}
