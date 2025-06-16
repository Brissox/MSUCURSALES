package NSP_TECH.SUCURSALES.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NSP_TECH.SUCURSALES.model.sucursal;
import NSP_TECH.SUCURSALES.repository.sucursalRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class sucursalServices {

    @Autowired
    private sucursalRepository sRepository;

    public List<sucursal> BuscarTodoSucursal(){
        return sRepository.findAll();
    }

    public sucursal BuscarUnaSucursal(Long ID_SUCURSAL){
        return sRepository.findById(ID_SUCURSAL).get();

    }

    public sucursal GuardarSucursal(sucursal sucursal){
        return sRepository.save(sucursal);

    }

    public void EliminarSucursal(Long ID_SUCURSAL){
        sRepository.deleteById(ID_SUCURSAL);
    }



}
