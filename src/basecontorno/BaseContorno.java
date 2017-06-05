/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basecontorno;

import javax.swing.JOptionPane;

/**
 *
 * @author acomesanavila
 */
public class BaseContorno {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        InterfazSQLite base= new InterfazSQLite(InterfazSQLite.CrearUrl());
        if (base.conectar()) {
            System.out.println("Conectado");
        } else {
            System.out.println("fallo");
        }
        base.CrearTabla();
        base.insertaDatos(Integer.parseInt(JOptionPane.showInputDialog("ID")),
                JOptionPane.showInputDialog("Nombre"), 
                Integer.parseInt(JOptionPane.showInputDialog("EDAD"))
                , JOptionPane.showInputDialog("Direccion"), 
                Float.parseFloat(JOptionPane.showInputDialog("Salario")));
    }

}
