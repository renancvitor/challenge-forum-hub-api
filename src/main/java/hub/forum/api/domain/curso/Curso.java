package hub.forum.api.domain.curso;

import jakarta.persistence.*;

@Table(name = "cursos")
@Entity(name = "Curso")
public class Curso {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}
