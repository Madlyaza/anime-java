package com.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.model.Studio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimeDTO
{
    private int id;

    @NotNull
    private Studio studio;

    @NotBlank
    private String name;

    @NotNull
    private Integer critic_score;

    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "CET")
    private Date release_date;
}