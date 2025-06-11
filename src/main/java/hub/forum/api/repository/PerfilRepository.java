package hub.forum.api.repository;

import hub.forum.api.domain.perfil.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
