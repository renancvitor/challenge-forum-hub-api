package hub.forum.api.controller;

import hub.forum.api.domain.resposta.RespostaService;
import hub.forum.api.domain.topico.TopicoService;
import hub.forum.api.domain.topico.DadosCriarTopico;
import hub.forum.api.domain.topico.validar.DadosValidarResposta;
import hub.forum.api.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private RespostaService respostaService;

    @PostMapping
    @Transactional
    public ResponseEntity criarTopico(@RequestBody @Valid DadosCriarTopico dados) {
        var dto = topicoService.criar(dados);
        return ResponseEntity.ok(dto);
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

    @PutMapping("/{idTopico}/resposta/{idResposta}/solucao")
    public ResponseEntity<?> marcarRespostaComoSolucao(
            @PathVariable Long idTopico,
            @PathVariable Long idResposta,
            @RequestParam Long autorId) {

        respostaService.marcarSolucao(idResposta, autorId, idTopico);
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
