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
    public int codigo;
    @Column(name = "prd_nombre")
    public String nombre;
    @Column(name = "prd_categoria")
    public String categoria;
    @Column(name = "prd_cantidad")
    public int cantidad;
    @Column(name = "prd_fecvenci")
    public LocalDate venc;
    @Getter
    @Setter
    @Column(name = "prd_alerta")
    private boolean alerta;

    @ManyToOne
    @JoinColumn(name = "pro_nit", referencedColumnName = "pro_nit")
    private Proveedor proveedor;

}
