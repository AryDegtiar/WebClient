package com.sistemaactivos.webclient.ary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Costumer implements Serializable {

    Integer id;

    Integer dni;
    String firstName;
    Integer age;
    String datebirth;
    String email;
    String phone;

    //Date createdAt;
    //Date updatedAt;
}
