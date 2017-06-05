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
          int menu;
        do {

            menu = Integer.parseInt(JOptionPane.showInputDialog(null, "Selección \n 1. Insertar Trabajador \n 2.Mostrar Trabajadores  \n 3.Borrar Trabajador \n 4.Modificar Trabajador \n 5 .Salir"));
            switch (menu) {

                case 1:
                    base.insertaDatos(Integer.parseInt(JOptionPane.showInputDialog("ID")), JOptionPane.showInputDialog("Nombre"), Integer.parseInt(JOptionPane.showInputDialog("Edad")), JOptionPane.showInputDialog("Direccion"),Float.parseFloat(JOptionPane.showInputDialog("Salario")));
                    break;
                case 2:
                    base.muestraDatosTabla();
                    break;
                case 3:
                    base.borrar(Integer.parseInt(JOptionPane.showInputDialog("Inserta el ID a borrar")));
                    break;
                case 4:
                    base.modificar(Integer.parseInt(JOptionPane.showInputDialog("ID")), JOptionPane.showInputDialog("Nombre"), Integer.parseInt(JOptionPane.showInputDialog("Edad")), JOptionPane.showInputDialog("Direccion"),Float.parseFloat(JOptionPane.showInputDialog("Salario")));
                    break;

            }

        } while (menu !=5);
    }

}
