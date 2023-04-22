package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 16/11/16.
 */
public class GameManagerTest {
    private static Logger logger = Logger.getLogger(GameManagerTest.class);
    GameManager manager;

    @Before
    public void setUp() throws JuegoYaExisteException, UsuarioYaExisteException {
        this.manager = new GameManagerImpl();
        this.manager.addUsuario("1","Toni","Boté",100,25);
        this.manager.addUsuario("2","Agustín","Tapia",100,25);
        this.manager.addUsuario("3","Arturo","Coello",100,25);
        manager.addUsuario("4","Momo","González",100,25);

        this.manager.crearJuego("1", 1, 3);
        this.manager.crearJuego("2", 5, 10);
        this.manager.crearJuego("3", 10, 2);
    }
    @After
    public void tearDown() {
        this.manager = null;
    }
    @Test
    public void addUsuarioTest() throws UsuarioYaExisteException {
        manager.addUsuario("5","Sanyo","Gutiérrez",100,25);
        manager.addUsuario("6","Fernando","Belasteguín",100,25);
        Assert.assertEquals(6,manager.numUsuarios());;
    }

    @Test
    public void addProductoTest() throws ProductoYaExisteException {
        manager.addProducto("1","Pócima",15);
        manager.addProducto("2","Espada",50);
        Assert.assertEquals(2,manager.numProductos());
    }
   /** Para crearJuegoTest hay que poner un idJuego que no este creado **/
   @Test
   public void crearJuegoTest() throws UsuarioActivoException, JuegoNoExisteException, JuegoActivoException, JuegoYaExisteException {
       //manager.crearJuego("4",5,10);
       Assert.assertEquals("INICIADO_EN_PREPARACION",manager.crearJuego("4",5,10));

   }
    /** Para iniciarPartidaTest hay que poner un idJuego que ya este creado y un idUsuario que no este en una partida**/
   @Test
    public void iniciarPartidaTest() throws UsuarioActivoException, JuegoNoExisteException, JuegoActivoException {
        Assert.assertEquals("INICIADO_EN_FUNCIONAMIENTO",manager.iniciarPartida("1","1"));

    }
    @Test
    public void finalizarJuegoTest() throws JuegoNoExisteException{
       Assert.assertEquals("FINALIZADO",manager.finalizarJuego("1"));
    }
    @Test
    public void consultarVidaTest() throws UsuarioActivoException, JuegoNoExisteException, JuegoActivoException, UsuarioNoExisteException {
        manager.iniciarPartida("1","1");
        //manager.consultarVidaUsuario("1");
        Assert.assertEquals(100, manager.consultarVidaUsuario("1"));

    }
    /** @Test
    public void consultarVidaEquipoTest() throws EquipoNoExisteException, JuegoYaExisteException, UsuarioActivoException, JuegoNoExisteException, JuegoActivoException {
       //manager.crearJuego("4",1,3);
       manager.iniciarPartida("4","1");
       Assert.assertEquals(100,manager.consultarVidaEquipo(1));
    } **/
    /** Vida inicial usuarios = 100. **/
    @Test
    public void DañoTest() throws UsuarioNoExisteException, UsuarioActivoException, JuegoNoExisteException, JuegoActivoException {
       manager.iniciarPartida("1","1");
       manager.actualizarVida("1",25);
       Assert.assertEquals(75, manager.consultarVidaUsuario("1"));
    }

}