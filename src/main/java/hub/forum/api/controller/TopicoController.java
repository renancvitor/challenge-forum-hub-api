package hub.forum.api.controller;

import hub.forum.api.dto.resposta.DadosAtualizacaoResposta;
import hub.forum.api.dto.resposta.DadosDetalhamentoResposta;
import hub.forum.api.dto.topico.DadosAtualizacaoTopico;
import hub.forum.api.dto.topico.DadosDetalhamentoTopico;
import hub.forum.api.service.RespostaService;
import hub.forum.api.dto.topico.DadosListagemTopico;
import hub.forum.api.service.TopicoService;
import hub.forum.api.dto.topico.DadosCadastroTopico;
import hub.forum.api.dto.topico.validar.DadosValidarResposta;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.naming.ldap.spi.LdapDnsProvider;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private RespostaService respostaService;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity criarTopico(@RequestBody @Valid DadosCadastroTopico dados,
                                      UriComponentsBuilder uriComponentsBuilder) {
        var topico = topicoService.criar(dados);

        var uri = uriComponentsBuilder.path("/topicos/{id}")
                .buildAndExpand(topico.id())
                .toUri();
        return ResponseEntity.created(uri).body(topico);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size = 10,
            sort = ("titulo")) Pageable paginacao) {
        var page = topicoRepository.findAllAtivos(paginacao)
                .map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping ("/{id}")
    public ResponseEntity listarById(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PostMapping("/{id}/responder")
    @Transactional
    public ResponseEntity registrarResposta(@PathVariable Long id) {
        topicoService.receberResposta(id);
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/{id}/validar")
    @Transactional
    public ResponseEntity validarResposta(@PathVariable Long id,
                                          @RequestBody @Valid DadosValidarResposta dados) {
        topicoService.validarResposta(id, dados.autorId());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados) {
        var topico = topicoRepository.getReferenceById(dados.id());
        topico.atualizarResposta(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{idTopico}/resposta/{idResposta}/solucao")
    @Transactional
    public ResponseEntity<?> marcarRespostaComoSolucao(
            @PathVariable Long idTopico,
            @PathVariable Long idResposta,
            @AuthenticationPrincipal Usuario usuarioLogado) {

        respostaService.marcarSolucao(idResposta, idTopico, usuarioLogado.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarTopico(@PathVariable Long id,
                                        @RequestBody @Valid Usuario usuario) {
        topicoService.deletarTopico(id, usuario);
        return ResponseEntity.noContent()
                .build();
    }
}
