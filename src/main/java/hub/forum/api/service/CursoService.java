package hub.forum.api.service;

import hub.forum.api.domain.curso.Curso;
import hub.forum.api.dto.curso.DadosCadastroCurso;
import hub.forum.api.dto.curso.DadosDetalhamentoCurso;
import hub.forum.api.dto.curso.DadosListagemCurso;
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
    public DadosDetalhamentoCurso cadastrar(DadosCadastroCurso dados) {
        var curso = new Curso(dados);
        cursoRepository.save(curso);
        return new DadosDetalhamentoCurso(curso);
    }

    public Page<DadosListagemCurso> listar(Pageable paginacao) {
        return cursoRepository.findAll(paginacao).map(DadosListagemCurso::new);
    }
}
