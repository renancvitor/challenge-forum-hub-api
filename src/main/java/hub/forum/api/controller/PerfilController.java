package hub.forum.api.controller;

import hub.forum.api.domain.usuario.DadosDetalhamentoPerfil;
import hub.forum.api.domain.usuario.DadosPerfil;
import hub.forum.api.domain.usuario.Perfil;
import hub.forum.api.domain.usuario.PerfilRepository;
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
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPerfil> cadastrar(@RequestBody @Valid DadosPerfil dados,
                                    UriComponentsBuilder uriComponentsBuilder) {
        var perfil = new Perfil(dados);
        repository.save(perfil);

        var uri = uriComponentsBuilder.path("/perfis/{id}")
                .buildAndExpand(perfil.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPerfil(perfil));
    }
}
