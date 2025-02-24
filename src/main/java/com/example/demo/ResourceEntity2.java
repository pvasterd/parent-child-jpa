package com.example.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "resources2")
public class ResourceEntity2 {

    public ResourceEntity2(final String id) {
        this.id = id;
    }

    @Id
    private String id;

    @Setter
    private String parentId;

    // the child side is responsible for the foreign key columns: `parent_id` and `parent_resourcedefinition_id`
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "parentId"
        )
    private Set<ResourceEntity2> children = new HashSet<>();

}
