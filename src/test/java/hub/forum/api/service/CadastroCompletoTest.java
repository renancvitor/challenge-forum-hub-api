package hub.forum.api.service;

import hub.forum.api.domain.categoria.Categoria;
import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.topico.StatusTopico;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.curso.DadosCadastroCurso;
import hub.forum.api.dto.perfil.DadosCadastroPerfil;
import hub.forum.api.dto.resposta.DadosCadastroResposta;
import hub.forum.api.dto.topico.DadosCadastroTopico;
import hub.forum.api.dto.usuario.DadosCadastroUsuario;
import hub.forum.api.repository.PerfilRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class CadastroCompletoTest {

    @Autowired
    CursoService cursoService;

    @Autowired
    PerfilService perfilService;

    @Autowired
    RespostaService respostaService;

    @Autowired
    TopicoService topicoService;

    @MockBean
    UsuarioLogadoService usuarioLogadoService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PerfilRepository perfilRepository;

    @Test
    void deveCadastrarTodosOsDadosParaTeste() {
        var dadosPerfilFake = new DadosCadastroPerfil("TESTER-01");
        perfilService.cadastrar(dadosPerfilFake);
        var perfilFake = perfilRepository.findByNome("TESTER-01")
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        var dadosUsuarioFake = new DadosCadastroUsuario("Fake", "fake@example.com",
                "126456","TESTER-01");
        var usuarioFake = usuarioService.cadastrar(dadosUsuarioFake);
        Mockito.when(usuarioLogadoService.getUsuario())
                .thenReturn(usuarioFake);

        var dadosCurso = new DadosCadastroCurso("Java", Categoria.TECNOLOGIA);
        var curso = cursoService.cadastrar(dadosCurso);

        var dadosPerfil = new DadosCadastroPerfil("ADMIN-01");
        var perfil = perfilService.cadastrar(dadosPerfil);

        var dadosUsuario = new DadosCadastroUsuario("Renan", "renan@example.com", "123456",
                "ADMIN-01");
        var usuario = usuarioService.cadastrar(dadosUsuario);

        var dadosTopico = new DadosCadastroTopico("Teste JUnit", "Testes automatizados Junit",
                StatusTopico.NAO_RESPONDIDO, curso.nome());
        var topico = topicoService.criar(dadosTopico, usuario);

        var resposta = respostaService.cadastrar(topico.id(),
                "Mensagem sobre o tópico");
    }
}
