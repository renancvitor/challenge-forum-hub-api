package hub.forum.api.domain.perfil;

public record DadosDetalhamentoPerfil(Long id,
                                      String nome) {

    public DadosDetalhamentoPerfil(Perfil perfil) {
        this(
                perfil.getId(),
                perfil.getNome()
        );
    }
}
