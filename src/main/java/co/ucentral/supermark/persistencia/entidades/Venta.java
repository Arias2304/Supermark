package co.ucentral.supermark.persistencia.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "ventas")
@Getter
@Setter
@NoArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vent_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "clt_Id", referencedColumnName ="clt_Id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "prd_producto", referencedColumnName = "prd_producto")
    private Producto producto;

    @Column(name = "vent_cantidad")
    private int cantidad;

    @Column(name = "vent_precio")
    private BigDecimal precio;

    @Column(name = "vent_total")
    private BigDecimal total;

    @Column(name = "vent_fecha")
    private LocalDate fecha;
}