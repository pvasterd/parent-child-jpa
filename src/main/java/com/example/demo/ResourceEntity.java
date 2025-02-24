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
@Table(name = "resources")
public class ResourceEntity {

    public ResourceEntity(final String id) {
        this.id = id;
    }

    @Id
    private String id;

    // See https://stackoverflow.com/questions/53647672/how-to-save-parent-and-child-in-one-shot-jpa-hibernate
    // See https://medium.com/javarevisited/managing-parent-child-relationships-with-spring-data-jpa-a-comprehensive-guide-327a9c3e3c6f
    // a many(children)-to-one(parent) entity association ("parent") maps to a foreign key: columns `parent_id` and `parent_resource_definition_id` columns in the child, the _join columns_,
    // referencing `id` and `resourcedefinition_id` columns in the parent respectively.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "parent_id", referencedColumnName = "id"),
    })
    @Setter
    private ResourceEntity parent;


    // the child side is responsible for the foreign key columns: `parent_id` and `parent_resourcedefinition_id`
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "parent")
    private Set<ResourceEntity> children = new HashSet<>();

}
