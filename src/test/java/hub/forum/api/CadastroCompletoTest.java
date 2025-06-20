package hub.forum.api;

import hub.forum.api.domain.categoria.Categoria;
import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.topico.StatusTopico;
import hub.forum.api.dto.curso.DadosCadastroCurso;
import hub.forum.api.dto.topico.DadosCadastroTopico;
import hub.forum.api.dto.usuario.DadosCadastroUsuario;
import hub.forum.api.repository.PerfilRepository;
import hub.forum.api.repository.UsuarioRepository;
import hub.forum.api.service.CursoService;
import hub.forum.api.service.RespostaService;
import hub.forum.api.service.TopicoService;
import hub.forum.api.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class CadastroCompletoTest {

    @Autowired
    CursoService cursoService;

    @Autowired
    RespostaService respostaService;

    @Autowired
    TopicoService topicoService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PerfilRepository perfilRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    @Transactional
    void deveCadastrarTodosOsDadosParaTeste() {
        var usuarioAdmin = usuarioRepository.findByEmail("admin@example.com")
                .orElseThrow(() -> new RuntimeException("Usuário admin não encontrado"));

        var perfil = perfilRepository.findByNome("COMUM")
                .orElseGet(() -> perfilRepository.save(new Perfil("COMUM")));

        var dadosUsuario = new DadosCadastroUsuario("Usuario Tester", "tester@example.com",
                "senha", "COMUM");
        var usuario = usuarioService.cadastrar(dadosUsuario);

        var dadosCurso = new DadosCadastroCurso("Java", Categoria.TECNOLOGIA);
        var curso = cursoService.cadastrar(dadosCurso, usuarioAdmin);

        var dadosTopico = new DadosCadastroTopico("Teste", "Testando",
                StatusTopico.NAO_RESPONDIDO, curso.nome());
        var topico = topicoService.criar(dadosTopico, usuarioAdmin);

        var resposta = respostaService.cadastrar(topico.id(),
                "Minha Resposta", usuarioAdmin);
    }
}
