package com.alurachallenge.forohub.domain.perfil;

import com.alurachallenge.forohub.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Perfil")
@Table(name = "perfil")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @ManyToMany(mappedBy = "perfiles")
    @JsonIgnore
    private Set<Usuario> usuarios = new HashSet<>();

    public Perfil(DatosPerfilRegistro datosPerfil) {

        this.nombre = datosPerfil.nombre();
    }

    public void actualizarPerfil(DatosPerfilActualizar datosPerfil) {
        if (datosPerfil.nombre() != null) {
            this.nombre = datosPerfil.nombre();
        }

    }

}