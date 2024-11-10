package co.ucentral.supermark.persistencia.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
@Table(name = "cliente")
@Entity
public class Cliente {


    @Id
    @Column(name = "clt_Id")
    private int ID;
    @Column(name = "clt_nombre")
    public String nombre;
    @Column(name = "clt_correo")
    public String correo;
    @Column(name = "clt_telefono")
    public String telefono;

}