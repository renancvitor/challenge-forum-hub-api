package hub.forum.api.repository;

import hub.forum.api.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u JOIN FETCH u.perfil WHERE u.email = :email")
    Optional<Usuario> findByEmail(@Param("email") String email);
}
