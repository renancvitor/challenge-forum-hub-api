package hub.forum.api.repository;

import hub.forum.api.domain.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    @Query("SELECT t FROM Topico t WHERE t.ativo = true")
    Page<Topico> findAllAtivos(Pageable pageable);
}
