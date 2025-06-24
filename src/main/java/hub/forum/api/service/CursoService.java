package hub.forum.api.service;

import hub.forum.api.domain.curso.Curso;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.curso.DadosCadastroCurso;
import hub.forum.api.dto.curso.DadosDetalhamentoCurso;
import hub.forum.api.dto.curso.DadosListagemCurso;
import hub.forum.api.infra.exception.AutorizacaoException;
import hub.forum.api.infra.exception.ValidacaoException;
import hub.forum.api.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    @Transactional
    public DadosDetalhamentoCurso cadastrar(DadosCadastroCurso dados,
                                            Usuario usuarioLogado) {
        String nomePerfil = usuarioLogado.getPerfil().getNome();
        if (!nomePerfil.equals("ADMIN")) {
            throw new AutorizacaoException("Apenas ADMIN pode cadastrar um novo perfil");
        }
        var curso = new Curso(dados);
        cursoRepository.save(curso);
        return new DadosDetalhamentoCurso(curso);
    }

    public Page<DadosListagemCurso> listar(Pageable paginacao) {
        return cursoRepository.findAll(paginacao).map(DadosListagemCurso::new);
    }
}
