package com.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.util.LocalDateAdapter;
import com.util.LocalDateDeserializer;
import com.util.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "anime")
public class Anime
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @NotBlank
    private String name;

    @NotNull
    private Integer critic_score;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "release_date", nullable = false)
    private LocalDate release_date;

    @XmlElement(name = "id")
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @XmlElement(name = "studio")
    public Studio getStudio()
    {
        return studio;
    }

    public void setStudio(Studio studio)
    {
        this.studio = studio;
    }

    @XmlElement(name = "name")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @XmlElement(name = "critic_score")
    public Integer getCritic_score()
    {
        return critic_score;
    }

    public void setCritic_score(Integer critic_score)
    {
        this.critic_score = critic_score;
    }

    @XmlElement(name = "release_date")
    public LocalDate getRelease_date()
    {
        return release_date;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public void setRelease_date(LocalDate release_date)
    {
        this.release_date = release_date;
    }
}
