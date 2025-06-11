package hub.forum.api.controller;

import hub.forum.api.domain.curso.Curso;
import hub.forum.api.domain.curso.DadosCadastroCurso;
import hub.forum.api.domain.curso.DadosDetalhamentoCurso;
import hub.forum.api.domain.curso.DadosListagemCurso;
import hub.forum.api.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    CursoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCurso> cadastrar(@RequestBody @Valid DadosCadastroCurso dados,
                                                            UriComponentsBuilder uriComponentsBuilder) {
        var curso = new Curso(dados);
        repository.save(curso);

        var uri = uriComponentsBuilder.path("/curso/{id}")
                .buildAndExpand(curso.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCurso(curso));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCurso>> listar(@PageableDefault(size = 10,
            sort = ("nome")) Pageable paginacao) {
        var page = repository.findAll(paginacao)
                .map(DadosListagemCurso::new);
        return ResponseEntity.ok(page);
    }
}
