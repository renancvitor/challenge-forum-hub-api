package hub.forum.api.controller;

import hub.forum.api.topico.CriarTopico;
import hub.forum.api.topico.DadosTopico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private CriarTopico criarTopico;

    @PostMapping
    public ResponseEntity enviarMensagem(@RequestBody @Valid DadosTopico dados) {
        var dto = criarTopico.criar(dados);
        return ResponseEntity.ok(dto);
    }
}
