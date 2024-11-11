package co.ucentral.supermark.persistencia.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "venta")
@Entity
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el ID
    @Column(name = "id_venta")
    private int id;

    @Column(name = "fecha_venta")
    private Date fecha;

    @Column(name = "total_venta")
    private double pago;

    // Relación con productos, asumiendo una relación de muchos a muchos
    @ManyToMany
    @JoinTable(
            name = "venta_producto",
            joinColumns = @JoinColumn(name = "venta_id"),
            inverseJoinColumns = @JoinColumn(name = "prd_producto")
    )
    private List<Producto> Productos;
}

