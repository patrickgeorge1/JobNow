package com.app.API.requirementType;


import com.app.API.category.Category;

import javax.persistence.*;

@Entity
@Table(name = "requirementTypes")
public class RequirementType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 128, nullable = false, unique = true)
    private String name;

    @Column(name = "type", nullable = false)
    private Integer type = 0;

    @Column(name = "mandatory", nullable = false)
    private Integer mandatory = 0;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMandatory() {
        return mandatory;
    }

    public void setMandatory(Integer mandatory) {
        this.mandatory = mandatory;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public RequirementType(Long id, String name, Integer type, Integer mandatory, Category category) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.mandatory = mandatory;
        this.category = category;
    }
}
