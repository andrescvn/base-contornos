/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basecontorno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author acomesanavila
 */
public class InterfazSQLite {

    ResultSet rs;
    private String url;
    Connection c = null;
    Statement stmt = null;
    int cuenta;

    /**
     * create or connect to the database with default name
     */
    public InterfazSQLite() {
        this.url = "jdbc:sqlite:default.db";
    }

    /**
     * create or connect to the database with user's database name
     *
     * @param url name of the rute
     */
    public InterfazSQLite(String url) {
        this.url = url;
    }
/**
 * make the url
 * @return the url to use
 */
    public static String CrearUrl() {
        return (JOptionPane.showInputDialog("Driver") + ":"
                + JOptionPane.showInputDialog("Tipo base") + ":"
                + JOptionPane.showInputDialog("Nombre base") + ".db");
    }
/**
 * conect to the dtb
 * @return if the connection is done or not
 */
    public boolean conectar() {
        boolean conectado;
        try {
            c = DriverManager.getConnection(url);
            conectado = true;
        } catch (Exception ex) {
            conectado = false;
        }
        return conectado;
    }
/**
 * make a table
 */
    public void CrearTabla() {
        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE trabajadores "
                    + "(ID INT PRIMARY KEY     NOT NULL,"
                    + " Nombre          TEXT    NOT NULL, "
                    + " Años           INT     NOT NULL, "
                    + " Direcion        CHAR(50), "
                    + " Salario         REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Fallo al crear la tabla");
            System.exit(0);
        }
        System.out.println("Tabla creada");
    }
/**
 * insert a row
 * @param ID 
 * @param Nombre
 * @param Edad
 * @param Direccion
 * @param Salario 
 */
    public void insertaDatos(int ID, String Nombre, int Edad, String Direccion, Float Salario) {

        try {
            PreparedStatement inserta = c.prepareStatement("INSERT INTO Trabajadores (ID,Nombre,Edad,Direccion,Salario)"
                    + "VALUES(?,?,?,?,?)");
            cuentaFilas();
            inserta.setInt(1, ID);
            inserta.setString(2, Nombre);
            inserta.setInt(3, Edad);
            inserta.setString(4, Direccion);
            inserta.setFloat(5, Salario);
            inserta.execute();
            int cuenta = inserta.getUpdateCount();
            System.out.println("Filas insertadas : " + cuenta);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }

    }
/**
 * count the row that the program use
 */
    public void cuentaFilas() {

        try {
            Statement st = c.createStatement();
            ResultSet resultado = st.executeQuery("Select count (*) as rowcount from Trabajadores");
            resultado.next();
            cuenta = resultado.getInt("rowcount");
            System.out.println("Filas totales : " + cuenta);
        } catch (SQLException ex) {
            System.out.println("Error de conexión : " + ex.getMessage());
        }
    }

    /**
     * remove a row
     *
     * @param ID the primary key that you want to remove
     */
    public void borrar(int ID) {
        try {
            stmt = c.createStatement();
            stmt.execute("DELETE from Trabajadores where ID =" + "'" + ID + "'");
            cuenta = stmt.getUpdateCount();
            System.out.println("Se han borrado : " + cuenta + " filas");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Displays all fields in the created database
     */
    public void muestraDatosTabla() {
        PreparedStatement mostrar;
        try {
            mostrar = c.prepareStatement("select * from Trabajadores");

            rs = mostrar.executeQuery();

            while (rs.next()) {
                System.out.println("ID : " + rs.getInt("ID"));
                System.out.println("Nombre : " + rs.getString("Nombre"));
                System.out.println("Edad : " + rs.getInt("Edad"));
                System.out.println("Direccion :" + rs.getString("Direccion"));
                System.out.println("Salario :" + rs.getFloat("Salario"));

                cuentaFilas();
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfazSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/**
 * change a row
 * @param ID
 * @param Nombre
 * @param Edad
 * @param Direccion
 * @param Salario 
 */
    public void modificar(int ID, String Nombre, int Edad, String Direccion, Float Salario) {

        try {
            PreparedStatement actualiza = c.prepareStatement("update Trabajadores set Nombre ='" + Nombre + "',Edad ='" + Edad + "',Direccion ='" + Direccion + "',Salario ='" + Salario + "' where ID=" + ID);
            actualiza.execute();

            cuenta = actualiza.getUpdateCount();
            System.out.println(" Se han actualizado :" + cuenta + " Registros");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
