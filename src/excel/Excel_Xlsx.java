package excel;

import Conexion.Conexion;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Xlsx {

    public Excel_Xlsx() {
    }

    public void crear(String imsi) throws ParseException {
        ArrayList resultado = new ArrayList();
        Connection conn = null;
        CallableStatement proc;
        ResultSet rs = null;
        String BD = "TDRGT";
        String U = "sa", P = "SYSM@n@g3rSQLDEV";
        String consulta = "EXEC TDRGT..SP_TRAFICO ?";

        Workbook libro = new XSSFWorkbook();
        Sheet hoja = libro.createSheet("Java");

        String[] encabezados = {"TEL_A", "IMSI_A", "PAIS", "INSERSION","REPORTADO","FECHA_CARGA","TIPO_CARGA","ESTADO"};

        Font estiloFuente = libro.createFont();
        estiloFuente.setBold(true);
        estiloFuente.setFontHeightInPoints((short) 12);
        estiloFuente.setColor(IndexedColors.WHITE.index);

        CellStyle estiloCelda = libro.createCellStyle();
        estiloCelda.setFont(estiloFuente);
        estiloCelda.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        estiloCelda.setFillForegroundColor(IndexedColors.BLUE_GREY.index);

        Row fila = hoja.createRow(0);
        for (int i = 0; i < encabezados.length; i++) {
            Cell celda = fila.createCell(i);
            celda.setCellValue(encabezados[i]);
            celda.setCellStyle(estiloCelda);
        }

        try {
            conn = Conexion.GetConection(BD, U, P);
            proc = conn.prepareCall(consulta);
            proc.setString(1, imsi);//Tipo String
            rs = proc.executeQuery();
            int rownum = 1;
            while (rs.next()) {
//                for (int i = 1; i < rs.getRow(); i++) {
                Row row = hoja.createRow(rownum++);
                row.createCell(0).setCellValue(rs.getLong(1));
                row.createCell(1).setCellValue(rs.getLong(2));
                row.createCell(2).setCellValue(rs.getLong(3));
                row.createCell(3).setCellValue(rs.getDate(4));
                row.createCell(4).setCellValue(rs.getInt(5));
                row.createCell(5).setCellValue(rs.getDate(6));
                row.createCell(6).setCellValue(rs.getInt(7));
                row.createCell(7).setCellValue(rs.getInt(8));
//                }

                System.out.println(rs.getString(1) + rs.getString(2));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("ERROR EN CATCH " + e);
        }

//        ArrayList<Invoices> a = llenarData();
//        CreationHelper creationHelper = libro.getCreationHelper();
//        CellStyle dateStyle = libro.createCellStyle();
//        dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("MM/dd/yyyy"));
//        int rownum = 1;
//        for (Invoices i : a) {
        //System.out.println("rownum-before"+(rownum));
//            Row row = hoja.createRow(rownum++);
        //System.out.println("rownum-after"+(rownum));
//            row.createCell(0).setCellValue(i.getItemId());
//            row.createCell(1).setCellValue(i.getItemName());
//            row.createCell(2).setCellValue(i.getItemQty());
//            row.createCell(3).setCellValue(i.getTotalPrice());
//            Cell dateCell = row.createCell(4);
//            dateCell.setCellValue(i.getItemSoldDate());
//            dateCell.setCellStyle(dateStyle);
//        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        var fecha = dtf.format(LocalDateTime.now());

        DateTimeFormatter dtfe = DateTimeFormatter.ofPattern("yyyyMMdd");
        var fechae = dtfe.format(LocalDateTime.now().minusDays(1));
        System.out.println(fechae);

        File fichero = new File("Reporte" + fechae + ".xlsx");
        System.out.println(fichero);
        eliminarArchivo(fichero);
        try {
            FileOutputStream archivo = new FileOutputStream(new File("Reporte" + fecha + ".xlsx"));
            libro.write(archivo);
            archivo.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e);
        }
    }

//    private static ArrayList<Invoices> llenarData() throws ParseException {
//        ArrayList<Invoices> a = new ArrayList();
//        a.add(new Invoices(1, "Book", 2, 10.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
//        a.add(new Invoices(2, "Table", 1, 50.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/02/2020")));
//        a.add(new Invoices(3, "Lamp", 5, 100.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
//        a.add(new Invoices(4, "Pen", 100, 20.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/02/2020")));
//        a.add(new Invoices(5, "Book", 2, 10.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
//        a.add(new Invoices(6, "Table", 1, 50.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/02/2020")));
//        a.add(new Invoices(7, "Lamp", 5, 100.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
//        a.add(new Invoices(8, "Pen", 100, 20.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/02/2020")));
//        a.add(new Invoices(9, "Book", 2, 10.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
//        a.add(new Invoices(10, "Table", 1, 50.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/02/2020")));
//        a.add(new Invoices(11, "Lamp", 5, 100.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
//        a.add(new Invoices(12, "Pen", 100, 20.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/02/2020")));
//        a.add(new Invoices(13, "Book", 2, 10.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
//        a.add(new Invoices(14, "Table", 1, 50.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/02/2020")));
//        a.add(new Invoices(15, "Lamp", 5, 100.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
//        return a;
//    }
//    public ArrayList Stored(String Tel) {
//        ArrayList resultado = new ArrayList();
//        Connection conn = null;
//        CallableStatement proc;
//        ResultSet rs = null;
//        String BD = "springDB";
//        String U = "sa", P = "SYSM@n@g3rSQLDEV";
//        String consulta = "SpringDB..TRAFICO ?";
//
//        Object[] fila = new Object[3]; // Hay tres columnas en la tabla
//        try {
//            conn = Conexion.GetConection(BD, U, P);
//            proc = conn.prepareCall(consulta);
//            proc.setString(1, Tel);//Tipo String
//            rs = proc.executeQuery();
//            while (rs.next()) {
////                for (int i = 0; i < 3; i++) {
////                    fila[i] = rs.getObject(i + 1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
////                }
//                System.out.println(rs.getString(1) + rs.getString(1));
//            }
//            rs.close();
//        } catch (SQLException e) {
//            System.out.println("ERROR EN CATCH " + e);
//        }
//        return resultado;
//    }
    public static void eliminarArchivo(File fichero) {

        if (!fichero.exists()) {
            System.out.println("Archivo no existe.");
        } else {
            fichero.delete();
            System.out.println("Limpieza de Archivos Realizado...");
        }

    }
}
