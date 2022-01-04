package com.bootcamp.exercicios.diploma.dto;

import com.bootcamp.exercicios.diploma.entity.Aluno;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/* obrigatorio

NOME
mínimo 8 caracteres, máximo 50 caracteres
Tipos de caracteres permitidos de a-z
 */
@Data
@AllArgsConstructor
public class AlunoDTO {
    @NotNull(message = "nome é obrigatório")
    @NotEmpty(message = "nome é obrigatório")
    @Size(min = 8, max = 50, message = "tamanho minimo 8, máximo 50")
    @Pattern(regexp = "^[-'a-zA-ZÀ-ÖØ-öø-ÿ ]+$", message = "Apenas caracteres do alfabeto, incluindo acentos")
    private final String nome;
    @Valid
    @NotNull (message = "aluno deve ter disciplinas")
    private final List<DisciplinaDTO> listaDisciplinas;

    public static Aluno converte(AlunoDTO alunoDTO) {
        return new Aluno(alunoDTO.getNome(),DisciplinaDTO.converteList(alunoDTO.getListaDisciplinas()));
    }

    /*
    public static AlunoDTO converte(Aluno aluno) {
        return new AlunoDTO(aluno.getNome(),DisciplinaDTO.converteListDTO(aluno.getListaDisciplinas()));
    }
    */
}