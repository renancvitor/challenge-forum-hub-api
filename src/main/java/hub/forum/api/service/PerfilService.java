package hub.forum.api.service;

import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.perfil.DadosCadastroPerfil;
import hub.forum.api.dto.perfil.DadosDetalhamentoPerfil;
import hub.forum.api.dto.perfil.DadosListagemPerfil;
import hub.forum.api.infra.exception.ValidacaoException;
import hub.forum.api.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerfilService {

    @Autowired
    PerfilRepository perfilRepository;

    @Transactional
    public DadosDetalhamentoPerfil cadastrar(DadosCadastroPerfil dados,
                                             Usuario usuarioLogado) {
        String nomePerfil = usuarioLogado.getPerfil().getNome();
        if (!nomePerfil.equals("ADMIN")) {
            throw new ValidacaoException("Apenas ADMIN pode cadastrar um novo perfil");
        }
        var perfil = new Perfil(dados);
        perfilRepository.save(perfil);
        return new DadosDetalhamentoPerfil(perfil);
    }

    public Page<DadosListagemPerfil> listar(Pageable paginacao) {
        return perfilRepository.findAll(paginacao).map(DadosListagemPerfil::new);
    }
}
