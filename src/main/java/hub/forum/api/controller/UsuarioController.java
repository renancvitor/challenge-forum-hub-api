package hub.forum.api.controller;

import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.dto.usuario.DadosCadastroUsuario;
import hub.forum.api.dto.usuario.DadosDetalhamentoUsuario;
import hub.forum.api.dto.usuario.DadosListagemUsuario;
import hub.forum.api.repository.PerfilRepository;
import hub.forum.api.domain.usuario.*;
import hub.forum.api.repository.UsuarioRepository;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        Perfil perfil = perfilRepository.findById(dados.perfilId())
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        var usuario = new Usuario(dados, perfil);
        usuarioRepository.save(usuario);

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
