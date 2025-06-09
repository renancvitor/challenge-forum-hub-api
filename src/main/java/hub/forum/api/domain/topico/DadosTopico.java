package hub.forum.api.domain.topico;

import hub.forum.api.domain.curso.DadosCurso;
import hub.forum.api.domain.resposta.DadosResposta;
import hub.forum.api.domain.usuario.DadosUsuario;

import java.time.LocalDateTime;
import java.util.List;

public record DadosTopico(String titulo,
                          String mensagem,
                          StatusTopico status,
                          DadosUsuario autor,
                          DadosCurso curso,
                          List<DadosResposta> respostas) {
}
