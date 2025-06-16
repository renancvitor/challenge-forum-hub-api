package hub.forum.api.controller;

import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.topico.DadosAtualizacaoTopico;
import hub.forum.api.dto.topico.DadosCadastroTopico;
import hub.forum.api.dto.topico.DadosListagemTotalTopico;
import hub.forum.api.dto.topico.validar.DadosValidarResposta;
import hub.forum.api.service.RespostaService;
import hub.forum.api.service.TopicoService;
import hub.forum.api.service.UsuarioLogadoService;
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
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private RespostaService respostaService;

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @PostMapping
    public ResponseEntity criar(@RequestBody @Valid DadosCadastroTopico dados,
                                      UriComponentsBuilder uriComponentsBuilder) {
        var topico = topicoService.criar(dados);
        var uri = uriComponentsBuilder.path("/topicos/{id}")
                .buildAndExpand(topico.id())
                .toUri();
        return ResponseEntity.created(uri).body(topico);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTotalTopico>> listar(@PageableDefault(size = 10,
            sort = ("titulo")) Pageable paginacao) {
        var page = topicoService.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping ("/{id}")
    public ResponseEntity listarById(@PathVariable Long id) {
        var topico = topicoService.listarById(id);
        return ResponseEntity.ok(topico);
    }

    @PostMapping("/{id}/responder")
    public ResponseEntity receberResposta(@PathVariable Long id) {
        topicoService.receberResposta(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/validar")
    public ResponseEntity validarResposta(@PathVariable Long id,
                                          @RequestBody @Valid DadosValidarResposta dados) {
        topicoService.validarResposta(id, dados.autorId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        var topico = topicoService.atualizar(id, dados);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{idTopico}/resposta/{idResposta}/solucao")
    public ResponseEntity<?> marcarRespostaComoSolucao(
            @PathVariable Long idTopico,
            @PathVariable Long idResposta,
            @AuthenticationPrincipal Usuario usuarioLogado) {

        respostaService.marcarSolucao(idResposta, idTopico, usuarioLogado.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTopico(@PathVariable Long id) {
        Usuario usuario = usuarioLogadoService.getUsuario();
        topicoService.deletarTopico(id, usuario);
        return ResponseEntity.noContent().build();
    }
}
