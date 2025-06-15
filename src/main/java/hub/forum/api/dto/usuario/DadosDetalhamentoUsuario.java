package hub.forum.api.dto.usuario;

import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.perfil.DadosDetalhamentoPerfil;

public record DadosDetalhamentoUsuario(Long id,
                                       String nome,
                                       String email,
                                       String senha,
                                       String perfilNome) {

    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getPerfil().getNome()
        );
    }
}
