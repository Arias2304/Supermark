package co.ucentral.supermark.persistencia.entidades;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Setter
@Getter
@Table(name = "proveedores")
@Entity
public class Proveedor {

    @Id
    @Column(name = "pro_nit")
    private String nit;
    @Column(name = "pro_nombre")
    public String nombre;
    @Column(name = "pro_telefono")
    public String telefono;

}