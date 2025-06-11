package hub.forum.api.controller;

import hub.forum.api.domain.resposta.*;
import hub.forum.api.domain.topico.Topico;
import hub.forum.api.repository.TopicoRepository;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.repository.UsuarioRepository;
import hub.forum.api.repository.RespostaRepository;
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
@RequestMapping("respostas")
public class RespostaController {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RespostaService respostaService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoResposta> cadastrar(@RequestBody @Valid DadosCadastroResposta dados,
                                                               UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoRepository.getReferenceById(dados.topicoId());
        Usuario autor = usuarioRepository.getReferenceById(dados.autorId());

        var resposta = new Resposta(dados, topico, autor);
        respostaRepository.save(resposta);

        var uri = uriComponentsBuilder.path("/respostas/{id}")
                .buildAndExpand(resposta.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoResposta(resposta));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemResposta>> listar(@PageableDefault(size = 10,
            sort = ("topico.titulo")) Pageable paginacao) {
        var page = respostaRepository.findAll(paginacao)
                .map(DadosListagemResposta::new);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarResposta(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
        respostaService.deletarResposta(id, usuario);
        return ResponseEntity.noContent()
                .build();
    }
}
