package hub.forum.api.domain.usuario;

import hub.forum.api.domain.perfil.Perfil;

public record DadosListagemUsuario(Long id,
                                   String nome,
                                   String email,
                                   Perfil perfil) {

    public DadosListagemUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil()
        );
    }
}
