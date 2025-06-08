package hub.forum.api.domain.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "perfis")
@Entity(name = "Perfil")
public class Perfil {

    private Long id;
    private String nome;
}
