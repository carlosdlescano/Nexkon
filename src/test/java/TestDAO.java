
import com.minegocio.DAO.ArticuloDAO;
import com.minegocio.DaoImpl.ArticuloDAOImpl;
import com.minegocio.model.Articulo;
import com.minegocio.util.Conexion;
import java.sql.Connection;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author POS
 */
public class TestDAO {

    public static void main(String[] args) {

        /*//Prueba de conexion
        Connection con = Conexion.getConexion();

        if (con != null) {
            System.out.println("Conexión exitosa!");
        } else {
            System.out.println("****Fallo la conexion.****");
        }*/

        /* //Prueba listar Articulos
        ArticuloDAO dao = new ArticuloDAOImpl();
        
        int cont = 0;
        // Ejecutar búsqueda
        ArrayList<Articulo> resultados = (ArrayList<Articulo>) dao.listarTodos();

        // Mostrar resultados
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron artículos.");
        } else {
            for (Articulo art : resultados) {
                cont++;
                System.out.println("Código: " + art.getCodigo());
                System.out.println("Marca: " + art.getMarca());
                System.out.println("Descripción: " + art.getDescripcion());
                System.out.println("Departamento: " + art.getCodDepartamento());
                System.out.println("Rubro: " + art.getCodRubro());
                System.out.println("Familia: " + art.getCodFamilia());
                System.out.println("Stock: " + art.getStock());
                System.out.println("Precio Costo: " + art.getPrecioCosto());
                System.out.println("Margen: " + art.getMargen());
                System.out.println("Precio Venta: " + art.getPrecioVenta());
                System.out.println("Código de Barra: " + art.getCodigoBarra());
                System.out.println("-----------------------------");
            }
        }
        System.out.println("La cantidad de articulo es: "+ cont);*/
    }

}
