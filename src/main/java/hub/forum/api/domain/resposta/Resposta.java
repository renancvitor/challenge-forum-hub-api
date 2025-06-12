package hub.forum.api.domain.resposta;

import hub.forum.api.domain.topico.Topico;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.resposta.DadosAtualizacaoResposta;
import hub.forum.api.dto.resposta.DadosCadastroResposta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "respostas")
@Entity(name = "Resposta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
    private Boolean solucao;

    @Column(nullable = false)
    private Boolean ativo = true;

    public Resposta(DadosCadastroResposta dados, Topico topico, Usuario autor) {
        this.mensagem = dados.mensagem();
        this.topico = topico;
        this.dataCriacao = LocalDateTime.now();
        this.autor = autor;
        this.solucao = false;
    }

    public void marcarComoSolucao() {
        this.solucao = true;
    }

    public void atualizarResposta(DadosAtualizacaoResposta dados) {
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
    }
}
