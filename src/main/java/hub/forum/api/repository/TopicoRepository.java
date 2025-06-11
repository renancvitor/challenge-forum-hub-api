package hub.forum.api.repository;

import hub.forum.api.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("SELECT t FROM Topico t WHERE t.ativo = true")
    List<Topico> findAllAtivos();
}
