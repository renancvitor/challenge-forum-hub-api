package hub.forum.api.domain.usuario;

import hub.forum.api.domain.perfil.Perfil;

public record DadosDetalhamentoUsuario(Long id,
                                       String nome,
                                       String email,
                                       String senha,
                                       Perfil perfil) {

    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getPerfil()
        );
    }
}
