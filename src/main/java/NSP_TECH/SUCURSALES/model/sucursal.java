package NSP_TECH.SUCURSALES.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name="SUCURSALES")
@AllArgsConstructor
@NoArgsConstructor

public class sucursal {

@Id
@GeneratedValue(strategy= GenerationType.IDENTITY )
@Column(name="ID_SUCURSAL")
private Long ID_SUCURSAL;

@Column(name="DIRECCION",nullable= false, length=50)
private String DIRECCION;

@Column(name="CIUDAD",nullable= false, length=30)
private String CIUDAD;

@Column(name="TELEFONO", nullable=true, precision=9)
private int TELEFONO;

@Column(name="ESTADO",nullable= false, length=1)
private char ESTADO;
}
