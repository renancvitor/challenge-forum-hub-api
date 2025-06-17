package hub.forum.api.controller;

import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.resposta.DadosAtualizacaoResposta;
import hub.forum.api.dto.resposta.DadosCadastroResposta;
import hub.forum.api.dto.resposta.DadosDetalhamentoResposta;
import hub.forum.api.dto.resposta.DadosListagemTotalResposta;
import hub.forum.api.service.RespostaService;
import hub.forum.api.service.UsuarioLogadoService;
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
@RequestMapping("respostas")
@SecurityRequirement(name = "bearer-key")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoResposta> cadastrar(@RequestBody @Valid DadosCadastroResposta dados,
                                                               UriComponentsBuilder uriComponentsBuilder) {
        var resposta = respostaService.cadastrar(dados);

        var uri = uriComponentsBuilder.path("/respostas/{id}")
                .buildAndExpand(resposta.id())
                .toUri();

        return ResponseEntity.created(uri).body(resposta);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTotalResposta>> listar(@PageableDefault(size = 10,
            sort = ("topico.titulo")) Pageable paginacao) {
        var page = respostaService.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoResposta dados) {
        var resposta = respostaService.atualizar(id, dados);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarResposta(@PathVariable Long id) {
        Usuario usuario = usuarioLogadoService.getUsuario();
        respostaService.deletarResposta(id, usuario);
        return ResponseEntity.noContent()
                .build();
    }
}
