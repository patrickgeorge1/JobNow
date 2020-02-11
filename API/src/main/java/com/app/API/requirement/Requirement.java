package com.app.API.requirement;

import com.app.API.job.Job;
import com.app.API.requirementType.RequirementType;

import javax.persistence.*;

@Entity
@Table(name = "requirements")
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", length = 128)
    private String value;

    @OneToOne
    @JoinColumn(name = "requirementType", nullable = false)
    private RequirementType requirementType;

    @OneToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RequirementType getRequirementType() {
        return requirementType;
    }

    public void setRequirementType(RequirementType requirementType) {
        this.requirementType = requirementType;
    }
}
