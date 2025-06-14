package hub.forum.api.controller;

import hub.forum.api.dto.perfil.DadosDetalhamentoPerfil;
import hub.forum.api.dto.perfil.DadosCadastroPerfil;
import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.dto.perfil.DadosListagemPerfil;
import hub.forum.api.repository.PerfilRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/perfis")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {

    @Autowired
    private PerfilRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPerfil> cadastrar(@RequestBody @Valid DadosCadastroPerfil dados,
                                    UriComponentsBuilder uriComponentsBuilder) {
        var perfil = new Perfil(dados);
        repository.save(perfil);

        var uri = uriComponentsBuilder.path("/perfis/{id}")
                .buildAndExpand(perfil.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPerfil(perfil));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPerfil>> listar(@PageableDefault(size = 10,
            sort = ("nome")) Pageable paginacao) {
        var page = repository.findAll(paginacao)
                .map(DadosListagemPerfil::new);
        return ResponseEntity.ok(page);
    }
}
