package hub.forum.api.controller;

import hub.forum.api.domain.curso.Curso;
import hub.forum.api.domain.curso.DadosCadastroCurso;
import hub.forum.api.domain.curso.DadosDetalhamentoCurso;
import hub.forum.api.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
