package com.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@XmlRootElement(name = "studio")
public class StudioDTO
{
    private int id;

    @NotBlank
    private String name;

    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "CET")
    private Date founded;

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
    public Date getFounded()
    {
        return founded;
    }

    public void setFounded(Date founded)
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
