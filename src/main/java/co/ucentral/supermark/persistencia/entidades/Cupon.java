package co.ucentral.supermark.persistencia.entidades;

import co.ucentral.supermark.persistencia.entidades.Producto;
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
    @JoinColumn(name = "prd_id")
    private Producto producto;

    private double descuento;

    private double precioFinal;

    @Id
    private Long id;

}

