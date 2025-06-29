package NSP_TECH.SUCURSALES.Service;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import NSP_TECH.SUCURSALES.model.sucursal;
import NSP_TECH.SUCURSALES.repository.sucursalRepository;
import NSP_TECH.SUCURSALES.services.sucursalServices;

public class sucursalesServiceTest {

    @Mock
    private sucursalRepository sucursalrepository;
    
    @InjectMocks
    private sucursalServices sucursalservices;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }



    
    @Test
    public void testBuscarTodo(){
    java.util.List<sucursal> lista = new  ArrayList<>();

    sucursal suc1 = new sucursal();
    sucursal suc2 = new sucursal();

    suc1.setId_sucursal(11L);
    suc1.setEstado('A');
    suc1.setDireccion("La moneda 123");
    suc1.setCiudad("Santiago");
    suc1.setTelefono(123456);

    suc2.setId_sucursal(12L);
    suc2.setEstado('P');
    suc2.setDireccion("Rusia 323");
    suc2.setCiudad("Moscu");
    suc2.setTelefono(123456);


    lista.add(suc1);
    lista.add(suc2);

    when(sucursalrepository.findAll()).thenReturn(lista);

    java.util.List<sucursal> resultadoBusqueda = sucursalservices.BuscarTodoSucursal();

    assertEquals(2,resultadoBusqueda.size());
    verify(sucursalrepository, times(1)).findAll();

}

    @Test
    public void testBuscarUnaSucursal(){
    sucursal suc1 = new sucursal();

    suc1.setId_sucursal(11L);
    suc1.setEstado('A');
    suc1.setDireccion("La moneda 123");
    suc1.setCiudad("Santiago");
    suc1.setTelefono(123456);

    when(sucursalrepository.findById(11L)).thenReturn(Optional.of(suc1));

    sucursal sucBuscado = sucursalservices.BuscarUnaSucursal(11L);
    assertEquals(11L,sucBuscado.getId_sucursal());
    verify(sucursalrepository, times(1)).findById(11L);

    }



    @Test
    public void testGuardarSucursal(){
        sucursal s = new sucursal();
        s.setId_sucursal(11L);
        s.setEstado('A');
        s.setDireccion("La moneda 123");
        s.setCiudad("Santiago");
        s.setTelefono(123456);
        
        when(sucursalrepository.save(any())).thenReturn(s);

        sucursal sucGuardados = sucursalservices.GuardarSucursal(s);
        assertEquals('A', sucGuardados.getEstado());
        verify(sucursalrepository, times(1)).save(s);

    }

    @Test
    public void testEditarSucursal(){

        sucursal sucOriginal = new sucursal();
        sucOriginal.setId_sucursal(11L);
        sucOriginal.setCiudad("Santiago");
        sucOriginal.setDireccion("Av. Siempre viva 123");

        sucursal sucEditado = new sucursal();
        sucEditado.setId_sucursal(11L);
        sucEditado.setCiudad("Springfield");
        sucEditado.setDireccion("Av. Siempre viva 123");

        when(sucursalrepository.save(any(sucursal.class))).thenReturn(sucEditado);
        when(sucursalrepository.existsById(11L)).thenReturn(true);
        sucursal resultado = sucursalservices.GuardarSucursal(sucEditado);

        assertNotNull(resultado);
        assertEquals(11L, resultado.getId_sucursal());
        assertEquals("Springfield", resultado.getCiudad());
        assertEquals("Av. Siempre viva 123", resultado.getDireccion());

        verify(sucursalrepository, times(1)).save(sucEditado);
    }

    @Test
    public void testEliminarSucursal(){
        Long id = 11L;
        doNothing().when(sucursalrepository).deleteById(id);

        sucursalservices.EliminarSucursal(11L);

        verify(sucursalrepository, times(1)).deleteById(id);

    }

}

