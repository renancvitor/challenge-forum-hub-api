package hub.forum.api.domain.resposta;

import hub.forum.api.domain.topico.DadosTopico;
import hub.forum.api.domain.usuario.DadosUsuario;

public record DadosResposta(String mensagem,
                            DadosTopico topico,
                            String dataCriacao,
                            DadosUsuario autor,
                            String solucao) {
}
