package hub.forum.api.dto.usuario;

import hub.forum.api.domain.usuario.Usuario;

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
