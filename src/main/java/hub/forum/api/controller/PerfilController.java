package hub.forum.api.controller;

import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.perfil.DadosCadastroPerfil;
import hub.forum.api.dto.perfil.DadosDetalhamentoPerfil;
import hub.forum.api.dto.perfil.DadosListagemPerfil;
import hub.forum.api.service.PerfilService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/perfis")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoPerfil> cadastrar(@RequestBody @Valid DadosCadastroPerfil dados,
                                                             @AuthenticationPrincipal Usuario usuarioLogado,
                                                             UriComponentsBuilder uriComponentsBuilder) {
        var perfil = perfilService.cadastrar(dados, usuarioLogado);

        var uri = uriComponentsBuilder.path("/perfis/{id}")
                .buildAndExpand(perfil.id())
                .toUri();

        return ResponseEntity.created(uri).body(perfil);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPerfil>> listar(@PageableDefault(size = 10,
            sort = ("nome")) Pageable paginacao) {
        var page = perfilService.listar(paginacao);
        return ResponseEntity.ok(page);
    }
}
