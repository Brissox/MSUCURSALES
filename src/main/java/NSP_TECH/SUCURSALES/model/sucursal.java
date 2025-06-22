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
private Long id_sucursal;

@Column(name="DIRECCION",nullable= false, length=50)
@Schema(description="")
private String direccion;

@Column(name="CIUDAD",nullable= false, length=30)
@Schema(description="")
private String ciudad;

@Column(name="TELEFONO", nullable=true, precision=9)
@Schema(description="")
private int telefono;

@Column(name="ESTADO",nullable= false, length=1)
@Schema(description="")
private char estado;
}
