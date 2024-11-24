package co.ucentral.supermark.persistencia.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "cupon")
@Entity
public class Cupon {

    @ManyToOne
    @JoinColumn(name = "prd_producto")
    private Producto producto;

    private double descuento;

    private double precioFinal;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

}
