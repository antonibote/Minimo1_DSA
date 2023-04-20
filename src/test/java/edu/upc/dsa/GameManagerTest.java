package edu.upc.dsa;

import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by juan on 16/11/16.
 */
public class GameManagerTest {
    private static Logger logger = Logger.getLogger(GameManagerTest.class);
    GameManager manager = null;
    @Test
    public void addUsuarioTest(){
        manager.addUsuario("1","Toni","Boté");
        manager.addUsuario("2","Berta","Castellana");
        Assert.assertEquals(2,manager.numUsuarios());;
    }

    @Test
    public void addProductoTest(){
        manager.addProducto("1","Pócima",15);
        manager.addProducto("2","Espada",50);
        Assert.assertEquals(2,manager.numProductos());
    }
    @Test
    public void consultarEstadoTest(){
        manager.consultarEstado("1");
        Assert.assertEquals("INICIADO_EN_FUNCIONAMIENTO",manager.consultarEstado("1"));

    }
}