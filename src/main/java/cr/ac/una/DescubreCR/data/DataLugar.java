/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.DescubreCR.data;

import cr.ac.una.DescubreCR.domain.Lugar;
import cr.ac.una.DescubreCR.domain.Ubicacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author josue
 */
public class DataLugar extends ConectarDB{
    private static final String TBLUGARES = "tb_lugares";
    private static final String TBUBICACION = "tb_ubicacion";
    private static final String CODIGO = "codigo";
    private static final String NOMBRE = "nombre";
    private static final String DESCRIPCION = "descripcion";
    private static final String CATEGORIA = "categoria";
    private static final String DIAS_HORARIO = "dias_horario";
    private static final String HORA_APERTURA = "hora_apertura";
    private static final String HORA_CIERRE = "hora_cierre";
    private static final String PRECIO_ENTRADA = "precio_entrada";
    private static final String CALIDAD_RECEP = "calidad_recepcion_telefonica";
    private static final String IMAGENES = "imagen";
    private static final String UBICACION = "ubicacion";
    
    public boolean insertar(Lugar lugar) throws SQLException{
         String sql = "INSERT INTO " + TBLUGARES + "(" + CODIGO + "," + NOMBRE + "," + DESCRIPCION + "," + CATEGORIA
                + "," + DIAS_HORARIO + "," + HORA_APERTURA + "," + HORA_CIERRE + "," + PRECIO_ENTRADA 
                + "," + CALIDAD_RECEP + "," + IMAGENES
                +") VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        String sql_ubicacion = "SELECT LAST_INSERT_ID() as id_lugar";
         
        Connection conexion = conectar();
        
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, lugar.getCodigo());
        statement.setString(2, lugar.getNombre());
        statement.setString(3, lugar.getDescripcion());
        statement.setString(4, lugar.getCategoria());
        statement.setString(5, lugar.getDias_horario());
        statement.setTime(6, Time.valueOf(lugar.getHora_apertura()));
        statement.setTime(7, Time.valueOf(lugar.getHora_cierre()));
        statement.setDouble(8, lugar.getPrecio_entrada());
        statement.setString(9, lugar.getCalidad_recepcion_telefonica());
        statement.setString(10, lugar.getImagen());
        //statement.setString(11, getId_ubicacion());
        
        int resultado = statement.executeUpdate();
       
        ResultSet rs = statement.executeQuery(sql_ubicacion);
        if (rs.next()) {
            setId_lugar(rs.getString("id_lugar"));
            System.out.println("ultimo id Lugar insertado: " + getId_lugar());
        }
        
        statement.close();
        conexion.close();
        
        return resultado==1;
    }
    
    public int getUltimoLugar(String ultimo_id_ubicacion){
        String sql_ubicacion = "SELECT u.lugar FROM tb_ubicacion u WHERE u.id = ?";
        Connection conexion = conectar();
        
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(sql_ubicacion);
            
            statement.setString(1, ultimo_id_ubicacion);
            
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                setId_lugar(rs.getString("lugar"));
                //System.out.println("ultimo id Lugar insertado: " + getId_lugar());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return Integer.parseInt(getId_lugar());
    }   
    public LinkedList<Lugar> listar() throws SQLException{
        LinkedList<Lugar> lugares = new LinkedList();
        String sql = "SELECT * FROM " + TBLUGARES + ";";
        Connection conexion = conectar();
        PreparedStatement statement = conexion.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            Lugar lugar = new Lugar();
            lugar.setCodigo(rs.getString(CODIGO));
            lugar.setNombre(rs.getString(NOMBRE));
            lugar.setDescripcion(rs.getString(DESCRIPCION));
            lugar.setCategoria(rs.getString(CATEGORIA));
            lugar.setDias_horario(rs.getString(DIAS_HORARIO));
            lugar.setHora_apertura(rs.getTime(HORA_APERTURA).toLocalTime());
            lugar.setHora_cierre(rs.getTime(HORA_CIERRE).toLocalTime());
            lugar.setPrecio_entrada(rs.getDouble(PRECIO_ENTRADA));
            lugar.setCalidad_recepcion_telefonica(rs.getString(CALIDAD_RECEP));
            lugar.setImagen(rs.getString(IMAGENES));
 
            lugares.add(lugar);
        }
        
        return lugares;
    }
    
    public Page<Lugar> listarAdmin(Pageable pageable) throws SQLException {
        
    List<Lugar> lugares = new ArrayList<>();
    String countSql = "SELECT COUNT(*) FROM " + TBLUGARES;
    String selectSql = "SELECT * FROM " + TBLUGARES + " LIMIT ? OFFSET ?";
    Connection conexion = conectar();
    PreparedStatement countStatement = conexion.prepareStatement(countSql);
    ResultSet countRs = countStatement.executeQuery();
    countRs.next();
    int total = countRs.getInt(1);
    
    PreparedStatement selectStatement = conexion.prepareStatement(selectSql);
    selectStatement.setInt(1, pageable.getPageSize());
    selectStatement.setInt(2, (int) pageable.getOffset());

    ResultSet rs = selectStatement.executeQuery();
    
    while (rs.next()) {
        Lugar lugar = new Lugar();
        lugar.setCodigo(rs.getString(CODIGO));
        lugar.setNombre(rs.getString(NOMBRE));
        lugar.setDescripcion(rs.getString(DESCRIPCION));
        lugar.setCategoria(rs.getString(CATEGORIA));
        lugar.setDias_horario(rs.getString(DIAS_HORARIO));
        lugar.setHora_apertura(rs.getTime(HORA_APERTURA).toLocalTime());
        lugar.setHora_cierre(rs.getTime(HORA_CIERRE).toLocalTime());
        lugar.setPrecio_entrada(rs.getDouble(PRECIO_ENTRADA));
        lugar.setCalidad_recepcion_telefonica(rs.getString(CALIDAD_RECEP));
        lugar.setImagen(rs.getString(IMAGENES));
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setId(rs.getInt("ubicacion"));
        lugar.setUbicacion(ubicacion);
        
        lugares.add(lugar);
    }
    
    return new PageImpl<>(lugares, pageable, total);
}

    
    public boolean actualiarForanea(String codigo) throws SQLException{
        String sqlUpdate = "UPDATE " + TBLUGARES + " SET " + UBICACION + " = ? " + "WHERE "+ CODIGO +" = ?";
        Connection conexion = conectar();
        PreparedStatement statement = conexion.prepareStatement(sqlUpdate);
        
        statement.setString(1, null);
        statement.setString(2, codigo);
        int resultadoUpdate = statement.executeUpdate();
        
        System.out.println("resultado update: "  + resultadoUpdate);
        
        return resultadoUpdate==1;
    }

    public boolean eliminar(String codigo) throws SQLException{
        String sql = "DELETE FROM "+ TBLUGARES +" WHERE "+ CODIGO +" = ?;";
        Connection conexion = conectar();
        PreparedStatement statement = conexion.prepareStatement(sql);
        
        statement = conexion.prepareStatement(sql);
        statement.setString(1, codigo);
        int resultado = statement.executeUpdate();
        
        statement.close();
        conexion.close();
        
        return (resultado==1);
    }
    
    public Lugar consultarEspPorCodigo(String codigo) throws SQLException{
        Lugar lugar = new Lugar();
        Ubicacion ubicacion = new Ubicacion();
        
        String sql = "SELECT "
                + " L.id,L.codigo,L.nombre,L.descripcion,L.categoria,L.dias_horario,"
                + " L.hora_apertura,L.hora_cierre,L.precio_entrada,L.calidad_recepcion_telefonica,"
                + " L.imagen,L.ubicacion,U.canton,U.distrito,U.nombre_provincia,U.direccion"
                + " FROM " + TBLUGARES + " L" 
                + " INNER JOIN " + TBUBICACION + " U ON L.ubicacion = U.id"
                + " WHERE " + CODIGO + " = ?";
        Connection conexion = conectar();
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, codigo);
        
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            lugar.setCodigo(rs.getString(CODIGO));
            lugar.setNombre(rs.getString(NOMBRE));
            lugar.setDescripcion(rs.getString(DESCRIPCION));
            lugar.setCategoria(rs.getString(CATEGORIA));
            lugar.setDias_horario(rs.getString(DIAS_HORARIO));
            lugar.setHora_apertura(rs.getTime(HORA_APERTURA).toLocalTime());
            lugar.setHora_cierre(rs.getTime(HORA_CIERRE).toLocalTime());
            lugar.setPrecio_entrada(rs.getDouble(PRECIO_ENTRADA));
            lugar.setCalidad_recepcion_telefonica(rs.getString(CALIDAD_RECEP));
            lugar.setImagen(rs.getString(IMAGENES));
            // Datos de la ubicacion ---
            
            ubicacion.setId(rs.getInt("ubicacion"));
            ubicacion.setNombreProvincia(rs.getString("nombre_provincia"));
            ubicacion.setCanton(rs.getString("canton"));
            ubicacion.setDistrito(rs.getString("distrito"));
            ubicacion.setDireccion(rs.getString("direccion"));
            
            lugar.setUbicacion(ubicacion);
            lugar.setId(rs.getInt("id"));
        }
        
        return lugar;
    }
    
    public Lugar consultarEspPorNombre(String nombre) throws SQLException{
        Lugar lugar = new Lugar();
        String sql = "SELECT * FROM " + TBLUGARES + " WHERE " + NOMBRE + " LIKE ?";
        Connection conexion = conectar();
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, "%" + nombre + "%");
        
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            lugar.setCodigo(rs.getString(CODIGO));
            lugar.setNombre(rs.getString(NOMBRE));
            lugar.setDescripcion(rs.getString(DESCRIPCION));
            lugar.setCategoria(rs.getString(CATEGORIA));
            lugar.setDias_horario(rs.getString(DIAS_HORARIO));
            lugar.setHora_apertura(rs.getTime(HORA_APERTURA).toLocalTime());
            lugar.setHora_cierre(rs.getTime(HORA_CIERRE).toLocalTime());
            lugar.setPrecio_entrada(rs.getDouble(PRECIO_ENTRADA));
            lugar.setCalidad_recepcion_telefonica(rs.getString(CALIDAD_RECEP));
            lugar.setImagen(rs.getString(IMAGENES));
            //lugar.setUbicacion((Ubicacion)rs.getObject(UBICACION));
        }
        
        return lugar;
    }
    
    public Page<Lugar> consultarPorNombre(Pageable pageable, String nombre) throws SQLException{
    List<Lugar> lugares = new ArrayList<>();
    String countSql = "SELECT COUNT(*) FROM " + TBLUGARES + " WHERE nombre LIKE ?";
    String selectSql = "SELECT * FROM " + TBLUGARES + " WHERE nombre LIKE ? LIMIT ? OFFSET ?;";
    
    Connection conexion = conectar();
    PreparedStatement countStatement = conexion.prepareStatement(countSql);
    countStatement.setString(1, "%" + nombre + "%");
    ResultSet countRs = countStatement.executeQuery();
    countRs.next();
    int total = countRs.getInt(1);
    
    PreparedStatement selectStatement = conexion.prepareStatement(selectSql);
    selectStatement.setInt(2, pageable.getPageSize());
    selectStatement.setInt(3, (int) pageable.getOffset());
    selectStatement.setString(1, "%" + nombre + "%");

    ResultSet rs = selectStatement.executeQuery();
    
    while (rs.next()) {
        Lugar lugar = new Lugar();
        lugar.setCodigo(rs.getString(CODIGO));
        lugar.setNombre(rs.getString(NOMBRE));
        lugar.setDescripcion(rs.getString(DESCRIPCION));
        lugar.setCategoria(rs.getString(CATEGORIA));
        lugar.setDias_horario(rs.getString(DIAS_HORARIO));
        lugar.setHora_apertura(rs.getTime(HORA_APERTURA).toLocalTime());
        lugar.setHora_cierre(rs.getTime(HORA_CIERRE).toLocalTime());
        lugar.setPrecio_entrada(rs.getDouble(PRECIO_ENTRADA));
        lugar.setCalidad_recepcion_telefonica(rs.getString(CALIDAD_RECEP));
        lugar.setImagen(rs.getString(IMAGENES));
        lugar.setUbicacion((Ubicacion)rs.getObject(UBICACION));
        lugares.add(lugar);
    }
    
    return new PageImpl<>(lugares, pageable, total);
}
    
    public boolean actualizarPorID(Lugar lugar) throws SQLException {
        String sql = "UPDATE " + TBLUGARES + " SET " +
                     NOMBRE + " = ?," +
                     DESCRIPCION + " = ?," +
                     CATEGORIA + " = ?," +
                     DIAS_HORARIO + " = ?," +
                     HORA_APERTURA + " = ?," +
                     HORA_CIERRE + " = ?," +
                     PRECIO_ENTRADA + " = ?," +
                     CALIDAD_RECEP + " = ?," +
                     IMAGENES + " = ?, " +
                     UBICACION + " = ?, " +
                     CODIGO + " = ? " +
                     "WHERE id = ?";

        Connection conexion = conectar();
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, lugar.getNombre());
        statement.setString(2, lugar.getDescripcion());
        statement.setString(3, lugar.getCategoria());
        statement.setString(4, lugar.getDias_horario());
        statement.setTime(5, Time.valueOf(lugar.getHora_apertura()));
        statement.setTime(6, Time.valueOf(lugar.getHora_cierre()));
        statement.setDouble(7, lugar.getPrecio_entrada());
        statement.setString(8, lugar.getCalidad_recepcion_telefonica());
        statement.setString(9, lugar.getImagen());
        statement.setString(10, String.valueOf(lugar.getUbicacion().getId()));
        statement.setString(11, String.valueOf(lugar.getCodigo()));
        statement.setString(12, String.valueOf(lugar.getId()));
        
        int resultado = statement.executeUpdate();
        statement.close();
        conexion.close();

        return resultado==1;
    }   
    
    public boolean actualizar(Lugar lugar) throws SQLException {
        String sql = "UPDATE " + TBLUGARES + " SET " +
                     NOMBRE + " = ?," +
                     DESCRIPCION + " = ?," +
                     CATEGORIA + " = ?," +
                     DIAS_HORARIO + " = ?," +
                     HORA_APERTURA + " = ?," +
                     HORA_CIERRE + " = ?," +
                     PRECIO_ENTRADA + " = ?," +
                     CALIDAD_RECEP + " = ?," +
                     IMAGENES + " = ? " +
                     "WHERE " + CODIGO + " = ?";

        Connection conexion = conectar();
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, lugar.getNombre());
        statement.setString(2, lugar.getDescripcion());
        statement.setString(3, lugar.getCategoria());
        statement.setString(4, lugar.getDias_horario());
        statement.setTime(5, Time.valueOf(lugar.getHora_apertura()));
        statement.setTime(6, Time.valueOf(lugar.getHora_cierre()));
        statement.setDouble(7, lugar.getPrecio_entrada());
        statement.setString(8, lugar.getCalidad_recepcion_telefonica());
        statement.setString(9, lugar.getImagen());
        statement.setString(10, lugar.getCodigo());
        
        int resultado = statement.executeUpdate();
        statement.close();
        conexion.close();

        return resultado==1;
    }       

    public LinkedList<String> getCategorias(){
        LinkedList<String> categorias = new LinkedList();
        
        categorias.add("Restaurante");
        categorias.add("Playa");
        categorias.add("Catarata");
        categorias.add("Río");
        categorias.add("Piscina");
        categorias.add("Canopy");
        categorias.add("Estadio");
        categorias.add("Museo");
        
        return categorias;
    }
    
    public LinkedList<String> getProvincias(){
        LinkedList<String> provincias = new LinkedList();
        
        provincias.add("San José");
        provincias.add("Alajuela");
        provincias.add("Cartago");
        provincias.add("Heredia");
        provincias.add("Guanacaste");
        provincias.add("Puntarenas");
        provincias.add("Limón");
        
        return provincias;
    }
    
    
}
