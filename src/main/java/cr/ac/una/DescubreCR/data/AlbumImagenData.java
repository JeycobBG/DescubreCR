/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.DescubreCR.data;

import cr.ac.una.DescubreCR.domain.Imagen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JEYCOB
 */
public class AlbumImagenData extends ConectarDB{
    public static final String TBALBUM_IMAGEN = "album_imagen";
    
    
    // insertar id_album & id_imagen de sus últimos registros
    public boolean insertar(){
        int resultado = -1;
        String sql = "INSERT INTO " + TBALBUM_IMAGEN + " (id_album,id_imagen) VALUES (?,?)";
        
        try {
            Connection connection = conectar();
            PreparedStatement sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, getId_album());
            sentencia.setString(2, getId_imagen());
            
            resultado = sentencia.executeUpdate();
            System.out.println("resultado insertar Album-Imagen = " + resultado);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado==1;
    }
    
    
    // listar imagenes de un album por ID
    public ArrayList <Imagen> getImagenesDeAlbumById(String id_album){
        ArrayList <Imagen> imagenes = new ArrayList<>(); 
        
        try {
            Connection connection = conectar();
            PreparedStatement sentencia = connection.prepareStatement(
                    " SELECT I.id,I.src,I.fecha_ingreso FROM " + TBALBUM_IMAGEN + " U "
                    + "INNER JOIN imagen I ON U.id_imagen = I.id "
                    + "WHERE U.id_album = ?");
            sentencia.setString(1, id_album);
            ResultSet rs = sentencia.executeQuery();
            
            
            while(rs.next()){
                Imagen img = new Imagen();
                img.setId(rs.getInt("id"));
                img.setSrc(rs.getString("src"));
                img.setFecha(rs.getDate("fecha_ingreso").toLocalDate());
                imagenes.add(img);
            }
            sentencia.close();
            closeConnection(connection);
        } catch (SQLException e) {
            imagenes = null;
        }
        return imagenes;
    }
}
