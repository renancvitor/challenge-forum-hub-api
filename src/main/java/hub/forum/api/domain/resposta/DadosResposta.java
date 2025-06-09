package hub.forum.api.domain.resposta;

import hub.forum.api.domain.topico.DadosTopico;
import hub.forum.api.domain.usuario.DadosUsuario;

import java.time.LocalDateTime;

public record DadosResposta(String mensagem,
                            DadosTopico topico,
                            DadosUsuario autor,
                            Boolean solucao) {
}
