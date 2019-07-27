package bd;

import Objetos.*;
import java.sql.*;
import java.util.ArrayList;

public class ConexionBD {
       private Connection con;
       //driver netbeans
       private static final String DRIVER = "com.mysql.jdbc.Driver";
       //driver para javac
       //private static final String DRIVER = "SQLdriver/mysql-connector-java-5.1.43-bin.jar";
       private static final String DBMS = "mysql";
       private static final String HOST = "127.0.0.1";
       private static final String PORT = "3306";
       //base de datos
       private static final String DATABASE = "base_tiendasmart";  //cortejamiento: utf8_spanish_ci
       private static final String USER = "usuario";
       private static final String PASSWORD = "SSKdDTx8GWVUcKaq";
       
    void Conexion(){}
    public void conectar ()throws Exception{
        try{
            Class.forName(DRIVER);
        }catch(ClassNotFoundException ce){}
            try{
                this.con = DriverManager.getConnection("jdbc:" + DBMS + "://" + HOST + ":" + PORT + "/" + DATABASE, USER, PASSWORD);                
                System.out.println("Conexión exitosa con la base de datos.");
            }catch(SQLException exception){
                System.out.println("Error – No se pudo conectar con la base de datos: "+exception);
            }             
    }
    
    public boolean desconectar(){
        try{
            this.con.close();
            return(true);
        }
        catch(SQLException e)
        {
            System.out.println("Error al Desconectar. "+e);
            return(false);
        }    
    }

    /* 
    public boolean ingresarOperador(operador o) {
        try{
            PreparedStatement st=null;
            st = con.prepareStatement("INSERT into operador (nombre,cedula,telefono,tipo) VALUES(?,?,?,?);");
            st.setString(1,o.getNombre());
            st.setString(2,o.getCedula());
            st.setString(3,o.getTelefono());
            st.setString(4,o.getTipo());
            
            st.executeUpdate();
            st.close();
            
            System.out.println("Se ingreso el operador exitosamente...");
            return true;
        }catch (SQLException ee){
            System.out.println("Error al ingresar el operador\n"+ee);
            return false;
        }
        
    }
    
    public boolean ingresarEmpresa(empresa e){
        try{
            PreparedStatement st=null;
            st = con.prepareStatement("INSERT into empresa (nombre,ruc,direccion,direccion_planta,telefono,correo,id_usuario) VALUES(?,?,?,?,?,?);");
            st.setString(1,e.getNombre());
            st.setString(2,e.getRuc());
            st.setString(3,e.getDireccion());
            st.setString(4,e.getDireccion_planta());
            st.setString(5,e.getTelefono());
            st.setString(6,e.getCorreo());
            st.setInt(7,e.getId_usuario());
            
            st.executeUpdate();
            st.close();
            
            System.out.println("Se ingreso la empresa exitosamente...");
            return true;
        }catch (SQLException ee){
            System.out.println("Error al ingresar la empresa\n"+ee);
            return false;
        }
    }
    
    public boolean ingresarNuevaClave(usuario u){
        try{
            PreparedStatement st=null;
            st = con.prepareStatement("INSERT INTO usuario (clave) VALUES(md5(?));");
            st.setString(1,u.getClave());
            st.executeUpdate();
            st.close();
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    
    public boolean ingresarUsuario(usuario u){
        try{
            PreparedStatement st=null;
            st = con.prepareStatement("INSERT INTO usuario (cuenta,clave,nombres,apellidos,cedula,edad,direccion,telefono,celular,correo,sexo,tipo,cargo,estado,fecha_inicio) VALUES(?,md5(?),?,?,?,?,?,?,?,?,?,?,?,?,?);");
            st.setString(1,u.getCuenta());
            st.setString(2,u.getClave());
            st.setString(3,u.getNombres());
            st.setString(4,u.getApellidos());
            st.setString(5,u.getCedula());
            st.setInt(6,u.getEdad());
            st.setString(7,u.getDireccion());
            st.setString(8,u.getTelefono());
            st.setString(9,u.getCelular());
            st.setString(10,u.getCorreo());
            st.setString(11,u.getSexo());
            st.setString(12,u.getTipo());
            st.setString(13,u.getCargo());
            st.setString(14,u.getEstado());
            st.setDate(15,u.getFecha_inicio()); //AAAA-MM-DD
            
            st.executeUpdate();
            st.close();
            
            System.out.println("Se ingreso el usuario exitosamente...");
            return true;
        }catch (SQLException e){
            System.out.println("Error al ingresar el usuario\n"+e);
            return false;
        }
    }
    
    public boolean esUsuarioValido(usuario u){        
        boolean resultado = false;
        ResultSet rs = null;
        PreparedStatement st = null;
        try{
            st = con.prepareStatement("SELECT * FROM usuario WHERE cuenta = ? AND clave = md5(?) AND estado = ?");
            //st = con.prepareStatement("SELECT * FROM usuario WHERE cuenta = ? AND clave = md5(?);");            
            st.setString(1,u.getCuenta());         
            st.setString(2,u.getClave());
            st.setString(3,"A");
            rs = st.executeQuery();            
            if(rs.next()){
                u.setTipo(rs.getString("estado"));
                resultado = true;
                System.out.println("usuario valido y activo...");
                System.out.println(u.getCuenta()+" - "+u.getTipo());
            }else{
                System.out.println("usuario despedido...");
            }
            rs.close();
            st.close();
        }
        catch(SQLException e){
            System.out.println("Error al consultar usuario. "+ e);
            resultado = false;
        }           
     return resultado; 
    }
    
    public boolean esEmpresaValida(String emp){        
        boolean resultado = false;
        ResultSet rs = null;
        PreparedStatement st = null;
        try{
            st = con.prepareStatement("SELECT nombre FROM empresa WHERE nombre = ?;");                
            st.setString(1,emp);
            rs = st.executeQuery();            
            if(rs.next()){
                resultado = true;
                System.out.println("Empresa valida:"+emp);
            }else{
                System.out.println("Empresa invalida...");
            }
            rs.close();
            st.close();
        }
        catch(SQLException e){
            System.out.println("Error al consultar empresa. "+ e);
            resultado = false;
        }           
     return resultado; 
    }
    
    //consultas
    //funcion para obtener obejtos usuarios desde cuenta
    public usuario obtenerDatosUsuario(String cuenta){
        usuario u = new usuario();
        ResultSet rs = null;                       
        PreparedStatement st = null;
        try{
            st = con.prepareStatement("SELECT * FROM usuario WHERE cuenta = ?;");            
            st.setString(1,cuenta);         
            rs = st.executeQuery();            
            if(rs.next()){
                u.setId(rs.getInt("id_usuario"));
                u.setCuenta(cuenta);
                u.setNombres(rs.getString("nombres"));
                u.setApellidos(rs.getString("apellidos"));
                u.setCedula(rs.getString("cedula"));
                u.setEdad(rs.getInt("edad"));
                u.setDireccion(rs.getString("direccion"));
                u.setTelefono(rs.getString("telefono"));
                u.setCelular(rs.getString("celular"));
                u.setCorreo(rs.getString("correo"));
                u.setSexo(rs.getString("sexo"));
                u.setTipo(rs.getString("tipo"));
                u.setCargo(rs.getString("cargo"));
                u.setEstado(rs.getString("estado"));
                u.setFecha_inicio(rs.getDate("fecha_inicio"));
                
                System.out.println("Datos de usuario obtenidos...");
            }
            rs.close();
            st.close();
        }catch(SQLException e){
            System.out.println(e);
        }           
        return u; 
    }
    
    //consulta de seleccion de empresa despues de autenticacion
    public ArrayList<empresa> cargarEmpresas(int id_usuario){        
        ArrayList<empresa> registroE = new ArrayList<empresa>();
        try{
            Statement st = this.con.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery("SELECT * FROM empresa where id_usuario = "+id_usuario+";");
            while (rs.next()){
                int id = rs.getInt("id_empresa");
                String nombre = rs.getString("nombre");
                empresa emp = new empresa(id, nombre,id_usuario);
                registroE.add(emp);
            }
            System.out.println("empresas consultadas.");
        }catch (Exception e){
            System.out.println("error en consulta de empresas."+e);
        }
        return registroE;
    }

    //obtener datos empresa
    public empresa obtenerDatosEmpresa(String nombre){
        empresa emp = new empresa();
        ResultSet rs = null;                       
        PreparedStatement st = null;
        try{
            st = con.prepareStatement("SELECT * FROM empresa WHERE nombre = ?;");            
            st.setString(1,nombre);         
            rs = st.executeQuery();            
            if(rs.next()){
                emp.setId_empresa(rs.getInt("id_empresa"));
                emp.setNombre(nombre);
                emp.setRuc(rs.getString("ruc"));
                emp.setDireccion(rs.getString("direccion"));
                emp.setDireccion_planta(rs.getString("direccion_planta"));
                emp.setTelefono(rs.getString("telefono"));
                emp.setCorreo(rs.getString("correo"));
                System.out.println("Datos de empresa obtenidos...");
            }
            rs.close();
            st.close();
        }catch(SQLException e){
            System.out.println(e);
        }           
        return emp; 
    }
    
    public ArrayList<empresa> consultarEmpresas(String busqueda, String tipo){
        ArrayList<empresa> registro = new ArrayList<empresa>();
        try{
            Statement st = this.con.createStatement();            
            ResultSet rs = null;
            
            if(tipo.equalsIgnoreCase("empresa")){
                rs = st.executeQuery("SELECT * FROM empresa;");
            }else{
                rs = st.executeQuery("SELECT * FROM empresa WHERE "+tipo+" LIKE '%"+busqueda+"%';");
            }
            
            while (rs.next()){
                int id = rs.getInt("id_empresa");
                String nom = rs.getString("nombre");
                String ruc = rs.getString("ruc");
                String dir = rs.getString("direccion");
                String dir_p = rs.getString("direccion_planta");
                String tel = rs.getString("telefono");
                String cor = rs.getString("correo");
                int id_u = rs.getInt("id_usuario");
                
                empresa emp = new empresa(id, nom, ruc, dir, dir_p, tel, cor, id_u);
                registro.add(emp);
            }
            System.out.println("empresas consultadas...");
        }catch (SQLException e){
            System.out.println("error en consulta de empresas."+e);
        }
        return registro;
    } 
    
    public ArrayList<usuario> consultarUsuarios(String busqueda, String tipo){
	ArrayList<usuario> registroU = new ArrayList<usuario>();
	try{
		Statement st = this.con.createStatement();		
		ResultSet rs = null;
		
		if(tipo.equalsIgnoreCase("usuario")){
			rs = st.executeQuery("SELECT * FROM usuario;");
		}else{
			rs = st.executeQuery("SELECT * FROM usuario WHERE "+tipo+" LIKE '%"+busqueda+"%';");
		}
		
		while (rs.next()){
                    int id           = rs.getInt("id_usuario");
                    String cuenta = rs.getString("cuenta");
                    String nombres   = rs.getString("nombres");
                    String apellidos = rs.getString("apellidos");
                    String cedula = rs.getString("cedula");
                    int edad      = rs.getInt("edad");
                    String direccion = rs.getString("direccion");
                    String telefono = rs.getString("telefono");
                    String celular = rs.getString("celular");
                    String correo = rs.getString("correo");
                    String sexo      = rs.getString("sexo");
                    String tipoU      = rs.getString("tipo");
                    String cargo     = rs.getString("cargo");
                    String estado = rs.getString("estado");
                    Date fecha_inicio = rs.getDate("fecha_inicio");
                    
                    usuario usr = new usuario(id, cuenta, cargo, nombres, apellidos, cedula, edad, direccion, telefono, celular, correo, sexo, tipoU, cargo, fecha_inicio, estado);
                    registroU.add(usr);
		}
		System.out.println("usuarios consultados.");
	}catch (SQLException e){
		System.out.println("error en consulta de usuarios."+e);
	}
	return registroU;
}

    public ArrayList<operador> consultarOperadores(String busqueda, String lista) {
        ArrayList<operador> registro = new ArrayList<operador>();
        try{
            Statement st = this.con.createStatement();            
            ResultSet rs = null;
            
            if(lista.equalsIgnoreCase("operador")){
                rs = st.executeQuery("SELECT * FROM operador;");
            }else{
                rs = st.executeQuery("SELECT * FROM operador WHERE "+lista+" LIKE '%"+busqueda+"%';");
            }
            
            while (rs.next()){
                int id_operador  = rs.getInt("id_operador");
                String nombre    = rs.getString("nombre");
                String cedula    = rs.getString("cedula");
                String telefono  = rs.getString("telefono");
                String tipo      = rs.getString("tipo");
                
                operador emp = new operador(id_operador,nombre,cedula,telefono,tipo);
                registro.add(emp);
            }
            System.out.println("operadores consultados...");
        }catch (SQLException e){
            System.out.println("error en consulta de operadores"+e);
        }
        return registro;
    }

    public boolean eliminarOperador(int id) {
        try{
            PreparedStatement st = null;
            st = con.prepareStatement("DELETE FROM operador WHERE id_operador = ?");                        
            st.setInt(1,id);         
            int n = st.executeUpdate();
            if(n==0){
                return false;
            }
            st.close();                        
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        } 
    }

    public boolean editarOperador(operador o) {
        try{
            PreparedStatement st = null;
            st = con.prepareStatement("UPDATE operador SET nombre=?, cedula=?, telefono=?, tipo=? WHERE id_operador =?");                        
            st.setString(1, o.getNombre());
            st.setString(2, o.getCedula());
            st.setString(3, o.getTelefono());
            st.setString(4, o.getTipo());
            st.executeUpdate();
            st.close();                        
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    /*
    public boolean ingresarProducto(producto p) {
        try{
            PreparedStatement st=null;
            st = con.prepareStatement("INSERT INTO usuario (cuenta,clave,nombres,apellidos,cedula,edad,direccion,telefono,celular,correo,sexo,tipo,cargo,estado,fecha_inicio) VALUES(?,md5(?),?,?,?,?,?,?,?,?,?,?,?,?,?);");
            st.setString(1,p.getNombre());
            st.setString(2,p.getNombre_cientifico());
            st.setString(3,p.getProveedor());
            st.setInt(4,p.getPeso_ideal());
            
            st.executeUpdate();
            st.close();
            
            System.out.println("Se ingreso el producto exitosamente...");
            return true;
        }catch (SQLException e){
            System.out.println("Error al ingresar el producto\n"+e);
            return false;
        }
    }

    public boolean eliminarProducto(int id) {
        try{
            PreparedStatement st = null;
            st = con.prepareStatement("DELETE FROM producto WHERE id_producto = ?");                        
            st.setInt(1,id);         
            int n = st.executeUpdate();
            if(n==0){
                return false;
            }
            st.close();                        
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        } 
    }

    public ArrayList<producto> consultarProductos(String busqueda, String producto) {
        ArrayList<producto> registro = new ArrayList<producto>();
        try{
            Statement st = this.con.createStatement();            
            ResultSet rs = null;
            
            if(producto.equalsIgnoreCase("producto")){
                rs = st.executeQuery("SELECT * FROM producto;");
            }else{
                rs = st.executeQuery("SELECT * FROM producto WHERE "+producto+" LIKE '%"+busqueda+"%';");
            }
            
            while (rs.next()){
                int id_producto             = rs.getInt("id_producto");
                String nombre               = rs.getString("nombre");
                String nombre_cientifico    = rs.getString("nombre_cientifico");
                String proveedor            = rs.getString("proveedor");
                int peso_ideal              = rs.getInt("peso_ideal");
                
                producto emp = new producto(id_producto,nombre,nombre_cientifico,proveedor,peso_ideal);
                registro.add(emp);
            }
            System.out.println("productos consultados...");
        }catch (SQLException e){
            System.out.println("error en consulta de productos"+e);
        }
        return registro;
    }

    public boolean editarProducto(producto o) {
        try{
            PreparedStatement st = null;
            st = con.prepareStatement("UPDATE producto SET nombre=?, nombre_cientifico=?, proveedor=?, peso_ideal=? WHERE id_producto =?");                        
            st.setString(1, o.getNombre());
            st.setString(2, o.getNombre_cientifico());
            st.setString(3, o.getProveedor());
            st.setInt(4, o.getPeso_ideal());
            st.executeUpdate();
            st.close();                        
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    public ArrayList<piscina> consultarPiscinas(String busqueda, String piscina) {
        ArrayList<piscina> registro = new ArrayList<piscina>();
        try{
            Statement st = this.con.createStatement();            
            ResultSet rs = null;
            
            if(piscina.equalsIgnoreCase("piscina")){
                rs = st.executeQuery("SELECT * FROM piscina;");
            }else{
                rs = st.executeQuery("SELECT * FROM piscina WHERE "+piscina+" LIKE '%"+busqueda+"%';");
            }
            
            while (rs.next()){
                int id_piscina   = rs.getInt("id_piscina");
                String ubicacion = rs.getString("ubicacion");
                
                piscina emp = new piscina(id_piscina,ubicacion);
                registro.add(emp);
            }
            System.out.println("piscinas consultadas...");
        }catch (SQLException e){
            System.out.println("error en consulta de piscnias"+e);
        }
        return registro;
    }
    */
}
