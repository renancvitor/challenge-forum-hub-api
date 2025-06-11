package hub.forum.api.dto.perfil;

import hub.forum.api.domain.perfil.Perfil;

public record DadosDetalhamentoPerfil(Long id,
                                      String nome) {

    public DadosDetalhamentoPerfil(Perfil perfil) {
        this(
                perfil.getId(),
                perfil.getNome()
        );
    }
}
