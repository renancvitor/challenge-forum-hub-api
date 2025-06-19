package hub.forum.api.domain.curso;

import hub.forum.api.domain.categoria.Categoria;
import hub.forum.api.dto.curso.DadosCadastroCurso;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Curso(DadosCadastroCurso dados) {
        this.nome = dados.nome();
        this.categoria = dados.categoria();
    }
}
