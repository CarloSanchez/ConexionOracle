package conexionoracle;

import java.sql.ResultSet;
import java.sql.Statement;

public class ConexionOracle {

    public static void main(String[] args) {
       if(Conexion.conectar()==null){
           System.out.println("La conexion Fallo!!!!!.");
       }else{
           System.out.println("La conexion fue Exitosa!!!!!.");
           try {
               Statement st=Conexion.conectar().createStatement();
               ResultSet rs=st.executeQuery("SELECT * FROM persona");
               while (rs.next()) {                   
                   System.out.println("ID:"+rs.getInt("idpersona")+
                   " NOMBRE:"+rs.getString("nombre")+
                   " APELLIDOS PATERNO: "+rs.getString("apellido_pat")+
                   " APELLIDOS MATERNO: "+rs.getString("apellido_mat")+
                   " DNI: "+rs.getString("dni"));
               }
           } catch (Exception e) {
           }
           
       }
    }
    
}
