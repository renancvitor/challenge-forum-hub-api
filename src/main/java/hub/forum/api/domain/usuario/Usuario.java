package hub.forum.api.domain.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "usuarios")
@Entity(name = "Usuario")
public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Perfil perfil;
}
