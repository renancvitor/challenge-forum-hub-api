package hub.forum.api.dto.perfil;

import hub.forum.api.domain.perfil.Perfil;

public record DadosListagemPerfil(String nome) {

    public DadosListagemPerfil(Perfil perfil) {
        this(
                perfil.getNome()
        );
    }
}
