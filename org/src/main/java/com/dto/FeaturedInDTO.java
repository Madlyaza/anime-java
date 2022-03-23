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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "featuredIn")
public class FeaturedInDTO
{
    private int id;

    @NotNull
    private Anime anime;

    @NotNull
    private Actor actor;

    @XmlElement(name = "id")
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @XmlElement(name = "anime")
    public Anime getAnime()
    {
        return anime;
    }

    public void setAnime(Anime anime)
    {
        this.anime = anime;
    }

    @XmlElement(name = "actor")
    public Actor getActor()
    {
        return actor;
    }

    public void setActor(Actor actor)
    {
        this.actor = actor;
    }
}
