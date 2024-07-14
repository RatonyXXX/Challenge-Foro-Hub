package com.alurachallenge.forohub.domain.usuario;

import com.alurachallenge.forohub.domain.perfil.Perfil;
import com.alurachallenge.forohub.domain.topico.Topico;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Usuario")
@Table(name = "usuario")
//@Table(name = "usuario", uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"correo_electronico", "perfil_id"}) })
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "correo_electronico", nullable = false, unique = true)
    private String correoElectronico;

    @Column(nullable = false)
    private String contrasena;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
            name = "Usuario_perfil",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id") )
    @JsonIgnore
    private Set<Perfil> perfiles = new HashSet<>();

    @OneToMany(mappedBy = "autor")
    private List<Topico> topicosCreados;


    public Usuario(DatosUsuarioRegistro datosUsuario) {
        this.nombre = datosUsuario.nombre();
        this.correoElectronico = datosUsuario.correoElectronico();
        this.contrasena = datosUsuario.contrasena();
            }

    public void actualizarUsuario(DatosUsuarioActualizar datosUsuario) {
        if (datosUsuario.nombre() != null) {
            this.nombre = datosUsuario.nombre();
        }

        if (datosUsuario.contrasena() != null) {
            this.contrasena = datosUsuario.contrasena();
        }

    }

}