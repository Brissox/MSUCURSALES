package NSP_TECH.SUCURSALES.Assambler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import NSP_TECH.SUCURSALES.controller.sucursalController;
import NSP_TECH.SUCURSALES.model.sucursal;


@Component
public class sucursalModelAssembler implements RepresentationModelAssembler<sucursal, EntityModel<sucursal>>{

@Override
public EntityModel<sucursal> toModel(sucursal s) {
    return EntityModel.of(
    s,
            linkTo(methodOn(sucursalController.class).BuscarProducto(s.getId_sucursal())).withRel("LINKS"),
            linkTo(methodOn(sucursalController.class).ListarSucursales()).withRel("todas-las-sucursales"),
            linkTo(methodOn(sucursalController.class).ActualizarSucursal(s.getId_sucursal(), s)).withRel("actualiza-una-sucursal")
    );
}

}
/* linkTo(methodOn(sucursalController.class).EliminarSucursal(s.getID_SUCURSAL())).withRel("elimina-una-sucursal"), */