package hub.forum.api.dto.usuario;

import hub.forum.api.domain.usuario.Usuario;

public record DadosListagemUsuario(Long id,
                                   String nome,
                                   String email,
                                   String nomePerfil) {

    public DadosListagemUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil().getNome()
        );
    }
}
