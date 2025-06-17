//package hub.forum.api.dto.resposta;
//
//import hub.forum.api.domain.resposta.Resposta;
//
//import java.time.LocalDateTime;
//
//public record DadosListagemUnicoResposta(Long id,
//                                         String mensagem,
//                                         String tituloTopico,
//                                         LocalDateTime dataCriacao,
//                                         String nomeAutor,
//                                         Boolean solucao) {
//
//    public DadosListagemUnicoResposta(Resposta resposta) {
//        this(
//                resposta.getId(),
//                resposta.getMensagem(),
//                resposta.getTopico().getTitulo(),
//                resposta.getDataCriacao(),
//                resposta.getAutor().getNome(),
//                resposta.getSolucao()
//        );
//    }
//}
