/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author alexander
 */
public class conexion_consulta {
    static Connection conexion=null;
    static Statement sentencia;
    static ResultSet resultado;
    static ResultSetMetaData resultadometa;
    public static void conectar(){
        String ruta="jdbc:mysql://localhost/Prueba";
        String user="root";
        String pass="182411";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion=DriverManager.getConnection(ruta,user,pass); 
            sentencia= conexion.createStatement();
            System.out.println("Conectado");
        } catch (Exception e) {
            System.out.println("No conectado");
        }
    }
    public static void guardar(variables x){
        String q = "INSERT INTO Datos VALUES('"+x.getNombre()+"','"+x.getApellido()+"')";
        ejecutar(q);
    }
    public static variables buscar_reg(String Nombre){
        variables r = null;
        String q = "SELECT * FROM Datos" + " WHERE Nombre='"+Nombre+"'";
        try {
            resultado = sentencia.executeQuery(q);
            System.out.println("Correcto");
        } catch (Exception e) {
            System.out.println(" No Correcto");
        }
        r= asignar();
        return r;
                
    }
    public static variables asignar(){
      variables r = null;
      String Nombre;
      String Apellido;
        try {
            if(resultado.last()){
                Nombre= resultado.getString("Nombre");
                Apellido= resultado.getString("Apellido");
                r= new variables (Nombre, Apellido);
            }
        } catch (Exception e) {
        }
      
      return r;
                
    }
    public static void eliminar_reg(String Nombre){
        String q= "DELETE FROM Datos WHERE Nombre='"+Nombre+"'";
        ejecutar(q);
    }
    public static void actualizar_reg(variables r, String Nombre){
        String q = "UPDATE Datos SET Nombre='"+r.getNombre()+ "',"+"Apellido='"+r.getApellido()+"'"+ "WHERE Nombre='"+Nombre+"'";
        ejecutar(q);
    }
    public static void ejecutar(String q){
        try {
            sentencia.executeUpdate(q);
            System.out.println("Correcto");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    public static ArrayList<String> llenar_combo(){
        ArrayList<String> lista = new ArrayList<String>();
        String q = "SELECT * FROM Datos";
        try {
            resultado = sentencia.executeQuery(q);
            System.out.println("Correcto");
        } catch (Exception e) {
            System.out.println("No Correcto");
        }
        try {
            while(resultado.next()){
                lista.add(resultado.getString("Apellido"));
            }
        } catch (Exception e) {
        }
        return lista;
    }
    public static ArrayList<Object[]> llenar_tabla(){
        ArrayList<Object[]> datos = new ArrayList<Object[]>();
        String q = "SELECT * FROM Datos";
        try {
            resultado=sentencia.executeQuery(q);
            resultadometa= resultado.getMetaData();
            System.out.println("Correcto");
        } catch (Exception e) {
            System.out.println("No Correcto");
        }
        try {
            while(resultado.next()){
                Object[] filas = new Object[resultadometa.getColumnCount()];
                for(int i = 0;i<resultadometa.getColumnCount();i++){
                    filas[i]= resultado.getObject(i+1);
                }
                datos.add(filas);
            }
        } catch (Exception e) {
        }
        return datos;
    
    }
    public static ArrayList<Object[]> buscar_tabla(String Nombre){
        ArrayList<Object[]> datos = new ArrayList<Object[]>();
        String q = "SELECT * FROM Datos" + " WHERE Nombre='"+Nombre+"'";
        try {
            resultado=sentencia.executeQuery(q);
            resultadometa= resultado.getMetaData();
            System.out.println("Correcto");
        } catch (Exception e) {
            System.out.println("No Correcto");
        }
        try {
            while(resultado.next()){
                Object[] filas = new Object[resultadometa.getColumnCount()];
                for(int i = 0;i<resultadometa.getColumnCount();i++){
                    filas[i]= resultado.getObject(i+1);
                }
                datos.add(filas);
            }
        } catch (Exception e) {
        }
        return datos;
    
    }
    public static ArrayList<Object[]> buscar_caracter_tabla(String Nombre){
        ArrayList<Object[]> datos = new ArrayList<Object[]>();
        String q = "SELECT * FROM Datos WHERE Nombre LIKE '"+Nombre+"%'";
        try {
            resultado=sentencia.executeQuery(q);
            resultadometa= resultado.getMetaData();
            System.out.println("Correcto");
        } catch (Exception e) {
            System.out.println("No Correcto");
        }
        try {
            while(resultado.next()){
                Object[] filas = new Object[resultadometa.getColumnCount()];
                for(int i = 0;i<resultadometa.getColumnCount();i++){
                    filas[i]= resultado.getObject(i+1);
                }
                datos.add(filas);
            }
        } catch (Exception e) {
        }
        return datos;
    
    }
}
