package com.app.API.category;

import com.app.API.requirementType.RequirementType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 128, nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<RequirementType> requirements = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RequirementType> getRequirements() {
        return requirements;
    }

    public void setRequirements(Set<RequirementType> requirements) {
        this.requirements = requirements;
    }
}
