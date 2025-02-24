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
public class ResourceEntity3 {

    public ResourceEntity3(final String id) {
        this.id = id;
    }

    @Id
    private String id;

    // the child side is responsible for the foreign key columns: `parent_id` and `parent_resourcedefinition_id`
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "parentId"
        )
    private Set<ChildEntity3> children = new HashSet<>();

}
