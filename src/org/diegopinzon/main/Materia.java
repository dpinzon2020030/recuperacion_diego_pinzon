/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.diegopinzon.main;

/**
 *
 * @author Diego Pinzon
 */
public class Materia {

    /*
    id int not null,
    nombre varchar(45),
    ciclo_escolar year,
    horario_inicio time,
    horario_final time,
    catedratico varchar(45),
    salon varchar(45),
    cupo_maximo int,
    cupo_minimo int,
     */
    private int id;
    private String nombre;
    private int ciclo_escolar;
    private String horario_inicio;
    private String horario_final;
    private String catedratico;
    private String salon;
    private int cupo_maximo;
    private int cupo_minimo;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCiclo_escolar() {
        return ciclo_escolar;
    }

    public String getHorario_inicio() {
        return horario_inicio;
    }

    public String getHorario_final() {
        return horario_final;
    }

    public String getCatedratico() {
        return catedratico;
    }

    public String getSalon() {
        return salon;
    }

    public int getCupo_maximo() {
        return cupo_maximo;
    }

    public int getCupo_minimo() {
        return cupo_minimo;
    }

    public Materia(int id, String nombre, int ciclo_escolar, String horario_inicio, String horario_final, String catedratico, String salon, int cupo_maximo, int cupo_minimo) {
        this.id = id;
        this.nombre = nombre;
        this.ciclo_escolar = ciclo_escolar;
        this.horario_inicio = horario_inicio;
        this.horario_final = horario_final;
        this.catedratico = catedratico;
        this.salon = salon;
        this.cupo_maximo = cupo_maximo;
        this.cupo_minimo = cupo_minimo;
    }

}
