package com.bootcamp.exercicios.diploma.dto;

import com.bootcamp.exercicios.diploma.entity.Disciplina;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;

/*Obrigatorios

Nome de matéria
mínimo 8 caracteres, máximo 50 caracteres
Tipos de caracteres permitidos de a-z

nota da matéria
mínimo 1 caracter, máximo 2 caracteres
Tipos de caracteres permitidos de [0-9]
 */
@Data
@AllArgsConstructor
public class DisciplinaDTO {
    @NotNull
    @NotEmpty(message = "A disciplina é obrigatória")
    @Size(min = 8, max = 50, message = "tamanho minimo 8, máximo 50")
    @Pattern(regexp = "^[-'a-zA-ZÀ-ÖØ-öø-ÿ ]+$", message = "Apenas caracteres do alfabeto, incluindo acentos")
    private final String disciplina;

    @NotNull(message = "nota da disciplina é obrigatória")
    @Max(value = 10, message = "Nota não pode ser maior que 10")
    @Min(value = 0, message = "Nota não pode ser menor que 0")
    @Digits(integer = 2, fraction = 2, message = "Nota não válida. Aceito apenas de 0 a 10, com 2 dígitos decimais")
    private final Double nota;

    public static Disciplina converte(DisciplinaDTO disciplinaDTO) {
        return new Disciplina(disciplinaDTO.getDisciplina(),disciplinaDTO.getNota());
    }

    public static List<Disciplina> converteList(List<DisciplinaDTO> listDisciplinas){
        return listDisciplinas.stream().map(DisciplinaDTO::converte).collect(Collectors.toList());
    }

    /*
    public static DisciplinaDTO converte(Disciplina disciplina) {
        return new DisciplinaDTO(disciplina.getDisciplina(),disciplina.getNota());
    }

    public static List<DisciplinaDTO> converteListDTO(List<Disciplina> listDisciplinas){
        return listDisciplinas.stream().map(DisciplinaDTO::converte).collect(Collectors.toList());
    }
     */
}
