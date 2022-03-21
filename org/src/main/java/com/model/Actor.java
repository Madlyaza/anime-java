package com.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "actor")
public class Actor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @Column(name = "birth_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "CET")
    private Date birth_date;

    @NotNull
    private String birth_place;

    @NotNull
    private Integer age;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "anime-actor",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "anime_id"))
    private Set<Anime> animes = new HashSet<Anime>();
}
