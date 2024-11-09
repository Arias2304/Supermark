package co.ucentral.supermark.persistencia.entidades;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "venta")
@Entity
public class Ventas {
    @Id
    @Column(name = "id-venta")
    private int id;
    @Column(name = "fecha-venta")
    public Date fecha;
    @Column(name = "total-venta")
    public double pago;
}
