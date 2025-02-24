package com.example.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "resources3")
public class ChildEntity3 {

    public ChildEntity3(final String id) {
        this.id = id;
    }

    @Id
    private String id;

    @Setter
    private String parentId;
}
