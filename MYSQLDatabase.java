import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class MYSQLDatabase {

      private final String host ;
      private final int port ;
      private final String databaseName ;
      private final String user ;
      private final String password ;
      
      private Connection connection ;

      private static boolean driverLoaded = false;

      public MYSQLDatabase(String host , int port , String databaseName, String user , String password){
        this.host = host;
        this.port = port ;
        this.databaseName = databaseName;
        this.user = user ;
        this.password = password;
        this.connection = null;
      }

      private static void loadDriver() throws ClassNotFoundException{
        if (!driverLoaded) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            driverLoaded = true;
        }
      }

       public void connect() throws SQLException, ClassNotFoundException{
        loadDriver();
        if(this.connection == null || this.connection.isClosed())
        {
            String connectionUrl = String.format("jdc:mysql://%s:%d:%s", this.host , this.port, this.databaseName);
            this.connection = DriverManager.getConnection(connectionUrl, this.user, this.password);
         }
       }

       public Statement createStrStatement() throws SQLException {
          if(this.connection != null && !this.connection.isClosed()) {
             return this.connection.createStatement();
          } else {
              throw new SQLException("Connexion non etablie ou ferme");
          }
       }
      
 

}
