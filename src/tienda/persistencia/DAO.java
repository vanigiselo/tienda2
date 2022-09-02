/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *
 * @author VANNI
 */
public abstract class DAO {
   protected Connection conexion;
    protected ResultSet resultado;
    protected Statement sentencia;  
    
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "tienda";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    
    public void ConectarBase () throws ClassNotFoundException, SQLException{
        try{    
            Class.forName(DRIVER);
            String urlBaseDeDatos = "jdbc:mysql://localhost:3306/" + DATABASE + "?useSSL=false";
            conexion = DriverManager.getConnection(urlBaseDeDatos,USER, PASSWORD);
            
            
        }catch (ClassNotFoundException | SQLException ex){
            throw ex;
        }
           
    }
    
    public void DesconectarBase () throws SQLException{
        try {
        if (resultado != null) {
            resultado.close();
            }
        
        if (conexion != null){
            conexion.close();
        }
        
        if (sentencia != null){
            sentencia.close();
        }
        } catch (SQLException e) {
            throw e;
        }
        
    }
    
    protected void InstalarModificarEliminar (String sql) throws Exception{
        try {
            ConectarBase ();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex ) {
             conexion.rollback();
          throw ex;
           
        } finally {
            DesconectarBase ();
        }
    }
         protected void consultarBase(String sql) throws Exception {
        try {
            ConectarBase();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }
    }


