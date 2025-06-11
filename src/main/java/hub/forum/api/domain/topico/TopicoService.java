package hub.forum.api.domain.topico;

import hub.forum.api.domain.curso.Curso;
import hub.forum.api.repository.CursoRepository;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.repository.UsuarioRepository;
import hub.forum.api.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public DadosDetalhamentoTopico criar(DadosCriarTopico dados) {
        Usuario autor = usuarioRepository.findById(dados.autorId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        Curso curso = cursoRepository.findById(dados.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Topico topico = new Topico(dados, autor, curso);
        topico = topicoRepository.save(topico);

        return new DadosDetalhamentoTopico(topico);
    }

    @Transactional
    public void receberResposta(Long topicoId) {
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new RuntimeException("Tõpico não encontrado"));

        topico.setStatus(StatusTopico.NAO_SOLUCIONADO);
    }

    @Transactional
    public void validarResposta(Long topicoId, Long autorId) {
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

        if (!topico.getAutor().getId().equals(autorId)) {
            throw new RuntimeException("Apenas o autor pode validar a resposta");
        }

        topico.setStatus(StatusTopico.SOLUCIONADO);
        topicoRepository.save(topico);
    }

    @Transactional
    public void deletarTopico(Long topicoId, Usuario usuario) {
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

        String nomePerfil = usuario.getPerfil().getNome();

        if (!nomePerfil.equals("ADMIN") && !nomePerfil.equals("MODERADOR")) {
            throw new RuntimeException("Apenas ADMIN ou MODERADOR podem deletar um tópico");
        }

        topico.setAtivo(false);
    }
}
