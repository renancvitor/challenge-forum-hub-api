package hub.forum.api.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("ForumHub API")
                        .version("1.0.0")
                        .description("API REST da aplicação ForumHub. Esta API fornece operações de criação, leitura, " +
                                "atualização e remoção (CRUD) para os recursos de perfis, usuários, cursos, tópicos e " +
                                "respostas, que compõem o funcionamento de um fórum.")
                        .contact(new Contact()
                                .name("Renan C. Vitor")
                                .email("renan.vitor.cm@gmail.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://github.com/renancvitor/challenge-forum-hub-api/blob/main/LICENSE")));
    }
}
