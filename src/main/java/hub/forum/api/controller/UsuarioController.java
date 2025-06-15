package hub.forum.api.controller;

import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.dto.usuario.DadosCadastroUsuario;
import hub.forum.api.dto.usuario.DadosDetalhamentoUsuario;
import hub.forum.api.dto.usuario.DadosListagemUsuario;
import hub.forum.api.repository.PerfilRepository;
import hub.forum.api.domain.usuario.*;
import hub.forum.api.repository.UsuarioRepository;
import hub.forum.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioService.cadastrar(dados);

        var uri = uriComponentsBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listar(@PageableDefault(size = 10,
    sort = ("nome"))Pageable paginacao) {
        var page = usuarioRepository.findAll(paginacao)
                .map(DadosListagemUsuario::new);
        return ResponseEntity.ok(page);
    }
}
