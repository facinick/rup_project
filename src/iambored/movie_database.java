/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iambored;

import java.sql.*;

/**
 *
 * @author nic
 */
public class movie_database {
    
    String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
    String DB_URL = "jdbc:sqlite:/Users/nic/Desktop/movies.db";
    Connection conn = null;
    Statement stmt = null;
    
    movie_database(double X[][], double Y[][])
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

      String sql = "select * from movie_metadata";
      ResultSet rs = stmt.executeQuery(sql);
      
      int results=0;
      
      while(rs.next()){
          
          
          X[results][0] = 1;
          X[results][1] = rs.getDouble("duration")/100;
          X[results][2] = rs.getDouble("director")/10000;
          X[results][3] = rs.getDouble("actor3")/10000;
          X[results][4] = rs.getDouble("actor1")/10000;
          X[results][5] = rs.getDouble("faces");
          X[results][6] = rs.getDouble("budget") / 1000000000;
          X[results][7] = rs.getDouble("year") / 1000;
          X[results][8] = rs.getDouble("actor2")/10000;
          
          Y[results][0] = rs.getDouble("imdb");
          
          results++;
      }
      rs.close();
      
      System.out.println("data loaded successfully rows : "+results);
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
    
    public void load(){
    
    
}

}
