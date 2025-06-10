package hub.forum.api.domain.resposta;

import hub.forum.api.domain.topico.DadosTopico;
import hub.forum.api.domain.usuario.DadosCadastroUsuario;

public record DadosResposta(String mensagem,
                            Long topicoId,
                            Long autorId,
                            Boolean solucao) {
}
