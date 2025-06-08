package hub.forum.api.domain.topico;

import hub.forum.api.domain.curso.DadosCurso;
import hub.forum.api.domain.resposta.DadosResposta;
import hub.forum.api.domain.usuario.DadosUsuario;

public record DadosTopico(String titulo,
                          String mensagem,
                          String dataCriacao,
                          StatusTopico status,
                          DadosUsuario autor,
                          DadosCurso curso,
                          DadosResposta respostas) {
}
