package NSP_TECH.SUCURSALES.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import NSP_TECH.SUCURSALES.Assambler.sucursalModelAssembler;
import NSP_TECH.SUCURSALES.model.sucursal;
import NSP_TECH.SUCURSALES.services.sucursalServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@RestController
@RequestMapping("api/v1/Sucursales")

public class sucursalController {

    @Autowired
    private sucursalServices sServices;

    @Autowired
    private sucursalModelAssembler assambler;


    //ENDPOINT PARA BUSCAR TODAS LAS SUCURSALES
    @GetMapping
    @Operation(summary = "SUCURSALES", description = "Operacion que lista todas las Sucursales")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se listaron correctamente las sucursales", content = @Content(mediaType = "application/json", schema = @Schema(implementation = sucursal.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ninguna sucursal", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))


    })

    public ResponseEntity<?> ListarSucursales(){
        List<sucursal> sucursal = sServices.BuscarTodoSucursal();
        if (sucursal.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran datos");
        } else {
            return ResponseEntity.ok(assambler.toCollectionModel(sucursal));
        }
    }


// ENDPOINT PARA BUSCAR UNA SUCURSAL
    @GetMapping("/{ID_SUCURSAL}")

    @Operation(summary = "SUCURSAL", description = "Operacion que lista una sucursal")
    @Parameters (value = {
        @Parameter (name="ID_SUCURSAL", description= "ID de la sucursal que se buscara", in = ParameterIn.PATH, required= true)
    })
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente la sucursal ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = sucursal.class))), 
        @ApiResponse(responseCode = "404", description = "No se encontro ninguna sucursal", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    })
    public ResponseEntity<?> BuscarProducto(@PathVariable Long ID_SUCURSAL){

        try {
            sucursal sBuscado = sServices.BuscarUnaSucursal(ID_SUCURSAL);
            return ResponseEntity.ok(assambler.toModel(sBuscado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra niguna sucursal");
        }
    }

    // ENDPOINT PARA REGISTRAR UNA NUEVA SUCURSAL
    @PostMapping
    @Operation(summary = "ENDPOINT QUE REGISTRA UNA SUCURSAL", description = "ENDPOINT QUE REGISTRA UNA SUCURSAL",requestBody= @io.swagger.v3.oas.annotations.parameters.RequestBody(description="SUCURSAL QUE SE VA A REGISTRAR", required = true), Content = @Content(schema = @Schema(implementation = sucursal.class)))
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se registro correctamente la sucursal", content = @Content(mediaType = "application/json", schema = @Schema(implementation = sucursal.class))),
        @ApiResponse(responseCode = "500", description = "Indica que no se logro registrar la sucursal", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar la sucursal")))
    })

    public ResponseEntity<?> GuardarSucursal(@RequestBody sucursal sGuardar){
    try {
            sucursal sRegistrar = sServices.GuardarSucursal(sGuardar);
            return ResponseEntity.ok((assambler.toModel(sRegistrar)));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar la sucursal");
    }
    }


    // ENDPOINT PARA EDITAR UNA SUCURSAL

    @PutMapping("/{ID_SUCURSAL}") //SOLO PERMITE ACTUALIZAR ESCRIBIENDO TODOS LOS DATOS

    @Operation(summary = "ENDPOINT QUE EDITA UNA SUCURSAL", description = "ENDPOINT QUE EDITA UNA SUCURSAL", requestBody=@io.swagger.v3.oas.annotations.parameters.RequestBody(description="SUCURSAL QUE SE VA A ACTUALIZAR", required = true), Content = @Content(schema = @Schema(implementation = sucursal.class)))
    @Parameters (value = {
    @Parameter (name="ID_SUCURSAL", description= "ID de la sucursal que se editara", in = ParameterIn.PATH, required= true)})

    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se edito correctamente la sucursal", content = @Content(mediaType = "application/json", schema = @Schema(implementation = sucursal.class))),
        @ApiResponse(responseCode = "500", description = "Sucursal no registrada", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar la sucursal")))
    })


        
    public ResponseEntity<?> ActualizarSucursal(@PathVariable Long ID_SUCURSAL, @RequestBody sucursal sActualizar){
        try {
            sucursal sActualizado = sServices.BuscarUnaSucursal(ID_SUCURSAL);
            sActualizado.setID_SUCURSAL(sActualizar.getID_SUCURSAL());
            sActualizado.setDIRECCION(sActualizar.getDIRECCION());
            sActualizado.setCIUDAD(sActualizar.getCIUDAD());
            sActualizado.setTELEFONO(sActualizar.getTELEFONO());
            sActualizado.setESTADO(sActualizar.getESTADO());
            sServices.GuardarSucursal(sActualizado);
            return ResponseEntity.ok((assambler.toModel(sActualizado)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sucursal no registrada");
        }
    }
    /*
    @DeleteMapping("/{ID_SUCURSAL}")
        public ResponseEntity<String> EliminarSucursal(@PathVariable Long ID_SUCURSAL){
            try {
                sucursal sBuscado = sServices.BuscarUnaSucursal(ID_SUCURSAL);
                sServices.EliminarSucursal(ID_SUCURSAL);
                return ResponseEntity.status(HttpStatus.OK).body("Se elimina Sucursal");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sucursal no registrada");
            }
        }
     */

}
