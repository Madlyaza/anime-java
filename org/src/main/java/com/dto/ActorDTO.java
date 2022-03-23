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
@XmlRootElement(name = "actor")
public class ActorDTO
{
    private int id;

    @NotBlank
    private String name;

    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "CET")
    private Date birth_date;

    @NotNull
    private String birth_place;

    @NotNull
    private Integer age;

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

    @XmlElement(name = "birth_date")
    public Date getBirth_date()
    {
        return birth_date;
    }

    public void setBirth_date(Date birth_date)
    {
        this.birth_date = birth_date;
    }

    @XmlElement(name = "birth_place")
    public String getBirth_place()
    {
        return birth_place;
    }

    public void setBirth_place(String birth_place)
    {
        this.birth_place = birth_place;
    }

    @XmlElement(name = "age")
    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }
}
