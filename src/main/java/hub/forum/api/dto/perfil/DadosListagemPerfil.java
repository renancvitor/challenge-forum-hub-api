package hub.forum.api.dto.perfil;

import hub.forum.api.domain.perfil.Perfil;

public record DadosListagemPerfil(Long id,
                                  String nome) {

    public DadosListagemPerfil(Perfil perfil) {
        this(
                perfil.getId(),
                perfil.getNome()
        );
    }
}
