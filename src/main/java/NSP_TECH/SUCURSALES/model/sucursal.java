package NSP_TECH.SUCURSALES.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name="SUCURSALES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Todas las sucursales registradas en la empresa")

public class sucursal {

@Id
@GeneratedValue(strategy= GenerationType.IDENTITY )
@Column(name="ID_SUCURSAL")
@Schema(description="identificador de la sucursal", example="1")
private Long id_sucursal;

@Column(name="DIRECCION",nullable= false, length=50)
@Schema(description="direccion donde se encuentra sucursal",example="siempre viva 321")
private String direccion;

@Column(name="CIUDAD",nullable= false, length=30)
@Schema(description="ciudad donde se encuentra la sucursal",example="santiago")
private String ciudad;

@Column(name="TELEFONO", nullable=true, precision=9)
@Schema(description="telefono de contacto de la sucursal",example="999999999")
private int telefono;

@Column(name="ESTADO",nullable= false, length=1)
@Schema(description="estado de la sucursal", example="A=Activa / I=Inactiva")
private char estado;
}
