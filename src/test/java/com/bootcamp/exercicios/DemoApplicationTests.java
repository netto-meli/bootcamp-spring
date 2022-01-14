package com.bootcamp.exercicios;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
==User Story
Teste de usuário
Digite um aluno com nome e 3 disciplinas carregadas corretamente (passar)
Insira um aluno sem nome e 3 disciplinas carregadas corretamente (falha na validação)
Insira um aluno com nome e sem assuntos carregados (reprovação)


*/


@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

/*
POST - /analyzeNotes
Se criado com sucesso, retorna "201 Created".
Caso o aluno admitido não cumpra a validação, não prossiga com a geração do diploma,
informando a exceção de validação dos dados correspondentes.
 */
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
