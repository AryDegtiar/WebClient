package com.sistemaactivos.webclient.ary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Benefits implements Serializable {

    Integer id;

    Integer numBeneficio;
    String nombre;
    String descripcion;
    String tipo;

}
