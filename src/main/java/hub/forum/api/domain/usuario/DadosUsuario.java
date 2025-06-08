package hub.forum.api.domain.usuario;

public record DadosUsuario(String nome,
                           String email,
                           String senha,
                           DadosPerfil perfil) {
}
