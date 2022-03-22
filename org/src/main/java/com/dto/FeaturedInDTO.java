package com.dto;

import com.model.Actor;
import com.model.Anime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeaturedInDTO
{
    private int id;

    @NotNull
    private Anime anime;

    @NotNull
    private Actor actor;
}
