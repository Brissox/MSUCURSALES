package NSP_TECH.SUCURSALES.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import NSP_TECH.SUCURSALES.model.sucursal;
import NSP_TECH.SUCURSALES.services.sucursalServices;



@RestController
@RequestMapping("api/v1/Sucursales")

public class sucursalController {

    @Autowired
    private sucursalServices sServices;

    @GetMapping
    public ResponseEntity<?> ListarSucursales(){
        List<sucursal> sucursal = sServices.BuscarTodoSucursal();
        if (sucursal.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran datos");
        } else {
            return ResponseEntity.ok(sucursal);
        }
    }

    @GetMapping("/{ID_SUCURSAL}")
    public ResponseEntity<?> BuscarProducto(@PathVariable Long ID_SUCURSAL){

        try {
            sucursal sBuscado = sServices.BuscarUnaSucursal(ID_SUCURSAL);
            return ResponseEntity.ok(sBuscado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran Producto");
        }
    }

    @PostMapping
    public ResponseEntity<?> GuardarProducto(@RequestBody sucursal sGuardar){
    try {
            sucursal sRegistrar = sServices.GuardarSucursal(sGuardar);
            return ResponseEntity.ok(sRegistrar);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar el Producto");
    }
    }

    @DeleteMapping("/{ID_SUCURSAL}")
        public ResponseEntity<String> EliminarProducto(@PathVariable Long ID_SUCURSAL){
            try {
                sucursal sBuscado = sServices.BuscarUnaSucursal(ID_SUCURSAL);
                sServices.EliminarSucursal(ID_SUCURSAL);
                return ResponseEntity.status(HttpStatus.OK).body("Se elimina Sucursal");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sucursal no registrada");
            }
        }

        @PutMapping("/{ID_SUCURSAL}") //SOLO PERMITE ACTUALIZAR ESCRIBIENDO TODOS LOS DATOS
        
    public ResponseEntity<?> ActualizarProducto(@PathVariable Long ID_SUCURSAL, @RequestBody sucursal sActualizar){
        try {
            sucursal sActualizado = sServices.BuscarUnaSucursal(ID_SUCURSAL);
            sActualizado.setID_SUCURSAL(sActualizar.getID_SUCURSAL());
            sActualizado.setDIRECCION(sActualizar.getDIRECCION());
            sActualizado.setCIUDAD(sActualizar.getCIUDAD());
            sActualizado.setTELEFONO(sActualizar.getTELEFONO());
            sActualizado.setESTADO(sActualizar.getESTADO());
            sServices.GuardarSucursal(sActualizado);
            return ResponseEntity.ok(sActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sucursal no registrada");
        }
    }
    

}
