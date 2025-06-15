package hub.forum.api.controller;

import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.token.DadosTokenJWT;
import hub.forum.api.dto.usuario.DadosLogin;
import hub.forum.api.service.AutenticacaoService;
import hub.forum.api.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosLogin dados) {
        var tokenJWT = autenticacaoService.autenticar(dados, manager);
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
