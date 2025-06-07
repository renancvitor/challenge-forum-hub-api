package hub.forum.api.domain.topico;

import hub.forum.api.domain.curso.Curso;
import hub.forum.api.domain.resposta.Resposta;
import hub.forum.api.domain.usuario.Usuario;

public record DadosTopico(String titulo,
                          String mensagem,
                          String dataCriacao,
                          String status,
                          Usuario autor,
                          Curso curso,
                          Resposta respostas) {
}
