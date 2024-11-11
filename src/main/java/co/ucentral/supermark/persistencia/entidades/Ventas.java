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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_venta")
    private Date fecha;

    @Column(name = "total_venta")
    private double pago;

    @ManyToMany
    @JoinTable(
            name = "venta_producto",
            joinColumns = @JoinColumn(name = "venta_id"),
            inverseJoinColumns = @JoinColumn(name = "prd_producto")
    )
    private List<Producto> productos;

}
