package Conexion;

import java.sql.*;
import javax.swing.*;

public class Conexion {

  public static Connection MiConexion;
  PreparedStatement SQL;
  ResultSet Datos;
  
  public static Connection GetConection(String BD, String User, String Pass)
  {
   try
   {
       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
       String Servidor = "jdbc:sqlserver://localhost\\MSSQLSERVER2022;"+"databaseName="+BD ;

       MiConexion = DriverManager.getConnection(Servidor,User,Pass);
       JOptionPane.showMessageDialog(null, "Conectado");
   }catch(ClassNotFoundException e)
   {
       JOptionPane.showMessageDialog(null, "Mensaje", "Tituto De Mensaje", JOptionPane.WARNING_MESSAGE);
       MiConexion = null;
   }
   catch(SQLException ex)
   {
       JOptionPane.showMessageDialog(null,"Error al Conectarse a la BD" + ex);
       MiConexion = null;
   }  
   return MiConexion;
  }

  public void Desconectar()
  {
      try
      {
          MiConexion.close();
          Datos.close();
          Datos=null;
          System.out.println("BD Desconectado.........");
      }catch(Exception e)
              {
              }
  }   
}
