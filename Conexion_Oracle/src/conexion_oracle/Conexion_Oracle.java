/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion_oracle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author alumno04
 */
public class Conexion_Oracle {
    
    public Connection AbrirConexion()
    {
        Connection cn = ConexionDB.conectar();
        return cn;
    }
    
    
    public static void main(String[] args) {
        
        Conexion_Oracle co = new Conexion_Oracle();
        
        if (ConexionDB.conectar() == null) {
            System.out.println("La conexión falló");
            
        } else {
            System.out.println("Conexión exitosa");
            
        }
        
        //metodo que inserta un registro en la base de dsatos
 
        //co.insert();
//        System.out.println("Se inserto Correcttamente");
        //metodo que muestra los registros de la base de datos
     co.select();
        System.out.println(":) :) :D :D ");
        
        //metodo que elimina regristro mediante un id
        //co.delete(1);
        
        //metodo que actualiza el regidtro mediante un id y sus campos
        //co.update(2, "Carlitos", "Bustamante", "Sanchez", 71269029);
        
    }
    
    public void insert()
    {
        Statement st = null;
        String query = "INSERT INTO persona VALUES(3,'Ulices','Julca','Huancas',71673623)";
        try {
           st = AbrirConexion().createStatement(); //aqui  hago el proceso transaccion
           st.executeUpdate(query);
           AbrirConexion().commit();
           AbrirConexion().close();
        } catch (Exception e) {
            e.printStackTrace();/// esto no debe faltar, para saber que esta mal
            try {
                AbrirConexion().rollback();
                AbrirConexion().close();
            } catch (Exception x) {
            }
            
        }
        
    }
    
    public void select()
    {
        Statement st = null;
        ResultSet rs = null;
        String query = "SELECT * FROM persona";
        
        try {
            st = AbrirConexion().createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {                
                
                System.out.println("ID:"+ rs.getInt("idpersona")+
                        ", Nombre:"+rs.getString("nombre")+
                        ", Apellido Paterno:"+rs.getString("apellido_pat")+
                        ", Apellido Materno:"+rs.getString("apellido_mat")+
                        ", DNI:"+rs.getInt("dni"));
            }
            AbrirConexion().close();
            
        } catch (Exception e) {
            e.printStackTrace();
            try {
                AbrirConexion().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public void delete(int id)
    {
        Statement st = null;
        String query = "DELETE FROM persona WHERE idpersona="+id;
        
        try {
            st = AbrirConexion().createStatement();
            st.executeUpdate(query);
            AbrirConexion().commit();
            AbrirConexion().close();
            System.out.println("Se elimino correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                AbrirConexion().rollback();
                AbrirConexion().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public void update(int id, String nombre, String ap_pat, String ap_mat, int dni)
    {
         Statement st = null;
         String query = "UPDATE persona SET nombre='"+nombre+"', apellido_pat='"+ap_pat+"', "
                 + "apellido_mat='"+ap_mat+"', dni="+dni+" where idpersona="+id;
         
         try {
            st = AbrirConexion().createStatement();
            st.executeUpdate(query);
            AbrirConexion().commit();
            AbrirConexion().close();
             System.out.println("se actu7alizo correctamente");
        } catch (Exception e) {
            e.printStackTrace();
             try {
                 AbrirConexion().rollback();
                 AbrirConexion().close();
             } catch (Exception ex) {
             }
        }
    }
}
