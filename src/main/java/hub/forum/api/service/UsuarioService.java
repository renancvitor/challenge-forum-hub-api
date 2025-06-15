package hub.forum.api.service;

import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.usuario.DadosCadastroUsuario;
import hub.forum.api.repository.PerfilRepository;
import hub.forum.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario cadastrar(DadosCadastroUsuario dados) {
        var senhaCriptografada = passwordEncoder.encode(dados.senha());
        var perfil = perfilRepository.getReferenceById(dados.perfilId());

        var dadosComSenhaCriptografada = new DadosCadastroUsuario(
                dados.nome(),
                dados.email(),
                senhaCriptografada,
                dados.perfilId()
        );

        Usuario usuario = new Usuario(dadosComSenhaCriptografada, perfil);

        return usuarioRepository.save(usuario);
    }
}
