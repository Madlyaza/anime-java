package com.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.util.LocalDateAdapter;
import com.util.LocalDateDeserializer;
import com.util.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "studio")
public class StudioDTO
{
    private int id;

    @NotBlank
    private String name;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate founded;

    @NotNull
    private String headquarters;

    @NotNull
    private String type;

    @XmlElement(name = "id")
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    @XmlElement(name = "founded")
    public LocalDate getFounded()
    {
        return founded;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public void setFounded(LocalDate founded)
    {
        this.founded = founded;
    }

    @XmlElement(name = "headquarters")
    public String getHeadquarters()
    {
        return headquarters;
    }

    public void setHeadquarters(String headquarters)
    {
        this.headquarters = headquarters;
    }

    @XmlElement(name = "type")
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
