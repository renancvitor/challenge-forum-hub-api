package hub.forum.api.service;

import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.usuario.DadosCadastroUsuario;
import hub.forum.api.dto.usuario.DadosListagemUsuario;
import hub.forum.api.infra.exception.ValidacaoException;
import hub.forum.api.repository.PerfilRepository;
import hub.forum.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        Perfil perfil = perfilRepository.findByNome(dados.perfilNome())
                .orElseThrow(() -> new EntityNotFoundException("Perfil não encontrado."));

        var dadosComSenhaCriptografada = new DadosCadastroUsuario(
                dados.nome(),
                dados.email(),
                senhaCriptografada,
                dados.perfilNome()
        );

        Usuario usuario = new Usuario(dadosComSenhaCriptografada, perfil);
        return usuarioRepository.save(usuario);
    }

    public Page<DadosListagemUsuario> listar(Pageable paginacao) {
        return usuarioRepository.findAll(paginacao).map(DadosListagemUsuario::new);
    }
}
