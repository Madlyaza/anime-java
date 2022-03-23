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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "anime")
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
    public Date getRelease_date()
    {
        return release_date;
    }

    public void setRelease_date(Date release_date)
    {
        this.release_date = release_date;
    }
}
