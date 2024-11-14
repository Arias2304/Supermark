package co.ucentral.supermark.persistencia.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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
    private int codigo;

    @Column(name = "prd_nombre")
    private String nombre;

    @Column(name = "prd_categoria")
    private String categoria;

    @Column(name = "prd_cantidad")
    private int cantidad;

    @Column(name = "prd_fecvenci")
    private LocalDate venc;

    @Column(name = "prd_precio")
    private BigDecimal precio;

    @Column(name = "prd_alerta")
    private boolean alerta;

    @ManyToOne
    @JoinColumn(name = "pro_nit", referencedColumnName = "pro_nit")
    private Proveedor proveedor;
}

