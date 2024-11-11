package co.ucentral.supermark.persistencia.entidades;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "productos")
@Entity
public class Producto {

    @Id
    @Column(name = "prd_producto")
    public String codigo;
    @Column(name = "prd_nombre")
    public String nombre;
    @Column(name = "prd_categoria")
    public String categoria;
    @Column(name = "prd_cantidad")
    public int cantidad;
    @Column(name = "prd_fecvenci")
    private LocalDate venc;
    @Column(name = "prd_precio")
    private double precio;



    @ManyToOne
    @JoinColumn(name = "pro_nit", referencedColumnName = "pro_nit")
    private Proveedor proveedor;

}
