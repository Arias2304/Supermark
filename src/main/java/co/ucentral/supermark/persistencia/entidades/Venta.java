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
@Table(name = "ventas")
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vnt_id")
    private int id;

    @Column(name = "vnt_fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToMany
    @JoinTable(
            name = "venta_productos",
            joinColumns = @JoinColumn(name = "venta_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productosVendidos;

    @Column(name = "vnt_totalVenta")
    private Double totalVenta;


    // Método para establecer el cliente
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

}
