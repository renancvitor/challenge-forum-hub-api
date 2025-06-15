package hub.forum.api.controller;

import hub.forum.api.dto.curso.DadosCadastroCurso;
import hub.forum.api.dto.curso.DadosDetalhamentoCurso;
import hub.forum.api.dto.curso.DadosListagemCurso;
import hub.forum.api.repository.CursoRepository;
import hub.forum.api.service.CursoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoCurso> cadastrar(@RequestBody @Valid DadosCadastroCurso dados,
                                                            UriComponentsBuilder uriComponentsBuilder) {
        var curso = cursoService.cadastrar(dados);
        var uri = uriComponentsBuilder.path("/curso/{id}")
                .buildAndExpand(curso.id())
                .toUri();

        return ResponseEntity.created(uri).body(curso);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCurso>> listar(@PageableDefault(size = 10,
            sort = ("nome")) Pageable paginacao) {
        var page = cursoService.listar(paginacao);
        return ResponseEntity.ok(page);
    }
}
