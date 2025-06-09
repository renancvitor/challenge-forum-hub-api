package hub.forum.api.domain.topico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriarTopico {

    @Autowired
    private TopicoRepository repository;

    public DadosDetalhamentoTopico criar(DadosTopico dados) {
        Topico topico = repository.save(new Topico(dados));
        return new DadosDetalhamentoTopico(topico);
    }
}
