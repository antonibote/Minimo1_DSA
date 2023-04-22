package edu.upc.dsa.services;


import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.Juego;
import edu.upc.dsa.models.Partida;
import edu.upc.dsa.transferencia.Vida;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/Game", description = "Endpoint to Game Service")
@Path("/Game")
public class GameService {

    private GameManager manager;

    public GameService() throws UsuarioYaExisteException, JuegoYaExisteException, ProductoYaExisteException {
        this.manager = GameManagerImpl.getInstance();
        if (manager.size()==0) {
            this.manager.addUsuario("1","Toni","Boté",100,25);
            this.manager.addUsuario("2","Agustín","Tapia",100,25);
            this.manager.addUsuario("3","Arturo","Coello",100,25);
            this.manager.addUsuario("4","Momo","González",100,25);

            this.manager.addProducto("1","Pócima de salud",15);
            this.manager.addProducto("2","Espada",30);

            this.manager.crearJuego("1",1,4);
            this.manager.crearJuego("2", 4,4);
            this.manager.crearJuego("3", 2,2);
        }
    }
    @POST
    @ApiOperation(value = "Crea un Juego", notes = "Crear Juego")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Juego creado!"),
            @ApiResponse(code = 404, message = "Este juego ya existe")
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearJuego(Juego juego) {
        try{
            this.manager.crearJuego(juego.getId(), juego.getN_Equipos(), juego.getP_personas());
        } catch (JuegoYaExisteException e) {
            return Response.status(409).entity(juego).build();
        }
        return Response.status(201).entity(juego).build();
    }
    @PUT
    @ApiOperation(value = "Iniciar Partida", notes = "Iniciar Partida")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Partida iniciada!"),
            @ApiResponse(code = 404, message = "Partida no encontrada"),
            @ApiResponse(code = 409, message = "Partida ya existente")
    })
    @Path("/")
    public Response iniciarPartida(Partida partida) {
        try {
            this.manager.iniciarPartida(partida.getIdJuego(), partida.getIdUsuario());
        } catch(UsuarioActivoException | JuegoActivoException e) {
            return Response.status(409).build();
        } catch(JuegoNoExisteException e) {
            return Response.status(404).build();
        }
        return Response.status(201).build();
    }
    @PUT
    @ApiOperation(value = "Finalizar el juego", notes = "Finalizar Juego")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Juego finalizado!"),
            @ApiResponse(code = 404, message = "Juego no encontrado")
    })
    @Path("/juego/{idJuego}/finalizarJuego")
    @Produces(MediaType.APPLICATION_JSON)
    public Response endGame(@PathParam("idJuego") String idJuego) {
        try {
            this.manager.finalizarJuego(idJuego);
        } catch(JuegoNoExisteException e) {
            return Response.status(404).build();
        }
        return Response.status(201).build();
    }
    @PUT
    @ApiOperation(value = "Actualizar vida usuario", notes = "Usuario sufre un daño de -25")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Vida actualizada!"),
            @ApiResponse(code = 404, message = "Usuario no existente")
    })
    @Path("/usuario/{idUsuario}/{daño}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarVida(@PathParam("idUsuario") String idUsuario, @PathParam("daño") int daño) {
        Integer vida;
        try{
            vida = this.manager.actualizarVida(idUsuario,daño);
        } catch (UsuarioNoExisteException  e ){
            return Response.status(404).build();
        }
        GenericEntity<Vida> entity = new GenericEntity<Vida>(new Vida(vida)) {};
        return Response.status(200).entity(entity).build();
    }
    @POST
    @ApiOperation(value = "Comprar un producto de la tienda", notes = "Comprar producto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Producto comprado!"),
            @ApiResponse(code = 404, message = "Producto no existente"),
            @ApiResponse(code = 409, message = "Dinero insuficiente")
    })
    @Path("/usuario/{idProducto}/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response comprarProducto(@PathParam("idProducto") String idProducto, @PathParam("idUsuario") String idUsuario) {
        try{
           this.manager.comprarProducto(idProducto, idUsuario);
        } catch (ProductoNoExisteException  e ){
            return Response.status(404).build();
        } catch (DineroInsuficienteException e){
            return Response.status(409).build();
        }
        return Response.status(200).build();
    }
    @GET
    @ApiOperation(value = "Consultar vida usuario", notes = "Consultar vida usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Vida consultada!"),
            @ApiResponse(code = 404, message = "Usuario no existente")
    })
    @Path("/usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarVidaUsuario(@PathParam("idUsuario") String idUsuario) {
        Integer vida;
        try{
            vida = this.manager.consultarVidaUsuario(idUsuario);
        } catch (UsuarioNoExisteException  e ){
            return Response.status(404).build();
        }
        GenericEntity<Vida> entity = new GenericEntity<Vida>(new Vida(vida)) {};
        return Response.status(200).entity(entity).build();
    }
}