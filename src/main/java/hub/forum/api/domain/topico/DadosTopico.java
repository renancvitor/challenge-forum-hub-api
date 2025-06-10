package hub.forum.api.domain.topico;

import hub.forum.api.domain.curso.DadosCurso;
import hub.forum.api.domain.resposta.DadosResposta;
import hub.forum.api.domain.usuario.DadosCadastroUsuario;

import java.util.List;

public record DadosTopico(String titulo,
                          String mensagem,
                          StatusTopico status,
                          Long autorId,
                          Long cursoId,
                          List<DadosResposta> respostas) {
}
