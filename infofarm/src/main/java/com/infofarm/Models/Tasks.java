package com.infofarm.Models;

import com.infofarm.Models.Users.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private String status;
    private String priority;
    private Date limit_date;

    @ManyToOne
    @JoinColumn(name = "id_bussines", nullable = false)
    private Bussines bussines;

    //TODO: Implementar los roles que pueden ver la tarea
    //private Set<Roles> canWatch;
}
