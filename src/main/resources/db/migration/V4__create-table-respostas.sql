CREATE TABLE respostas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    topico_id BIGINT NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_id BIGINT NOT NULL,
    solucao BOOLEAN NOT NULL,

    CONSTRAINT fk_resposta_topico FOREIGN KEY (topico_id) REFERENCES topicos(id),
    CONSTRAINT fk_resposta_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
