

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class App {
    public static void main(String[] arg){
        String url = "jdbc:mysql://localhost:3306/poly_sports";
        String user = "root" ;
        String password = "" ;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection myConnection = DriverManager.getConnection( url , user, password );
            System.out.println("Connexion à la base de données résusie !");
            myConnection.close();



            Statement myStatement = myConnection.createStatement();
            ResultSet results = myStatement.executeQuery("select * from `sport`");
            while(results.next()) {
                final String name = results.getString("name");
                final int participant = results.getInt("required_participants");
                System.out.println(name + ": " + participant);
            }
        
        } catch (ClassNotFoundException e) {
            System.out.println("Driver  JDBC non trouve ");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données");
            e.printStackTrace();
        }

    }
}