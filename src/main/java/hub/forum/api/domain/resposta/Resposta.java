package hub.forum.api.domain.resposta;

import hub.forum.api.domain.topico.Topico;
import hub.forum.api.domain.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Table(name = "respostas")
@Entity(name = "Resposta")
public class Resposta {

    private Long id;
    private Topico topico;
    private LocalDateTime dataCriacao;
    private Usuario autor;
    private String solucao;
}
