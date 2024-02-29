package excel;

import Conexion.*;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SP {
    
    public String Stored(String Tel,JTable jTabla)//, String B,JTable jTablaDatos)
    {
     DefaultTableModel TablaModelo = new DefaultTableModel();
     jTabla.setModel(TablaModelo);
     Connection conn = null;
     CallableStatement proc; 
     ResultSet rs=null;
     String resultado=null;
     String BD="springDB";
     String U="sa",P="SYSM@n@g3rSQLDEV";
     String consulta="SpringDB..TRAFICO ?";

     Object [] fila = new Object[3]; // Hay tres columnas en la tabla
       try { 
              conn=Conexion.GetConection(BD,U,P);
              proc = conn.prepareCall(consulta);
              proc.setString(1, Tel);//Tipo String
              rs = proc.executeQuery();
              TablaModelo.addColumn("TELEFONO");
              TablaModelo.addColumn("DESTINO");
              TablaModelo.addColumn("FECHA");
       while(rs.next())
                {
                    for (int i=0;i<3;i++)
                    fila[i] = rs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
                    TablaModelo.addRow(fila);
//                    fila = null;
//                    System.out.println("Telefono "+ rs.getString(1)+ "Destino " + rs.getString(1));
                } 
       rs.close();
       } 
       catch (SQLException e) {                  
            System.out.println("ERROR EN CATCH " + e);
       }
       return resultado;
   }
}   

     