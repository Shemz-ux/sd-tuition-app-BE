package SDstudios.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "child")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int childId;

    @NotBlank
    private String name;

    @NotBlank
    private String school;

    @NotNull
    private Integer yearGroup;

    @NotBlank
    private String currentGrade;

    private String additionalInfo;

    @ManyToOne
    @JoinColumn(
            name = "parent_id"
    )
    @JsonBackReference(value="parent-child")
    private Parent parent;

    public Child(){}

    public Child(String name, String school, Integer yearGroup, String currentGrade, String additionalInfo) {
        this.name = name;
        this.school = school;
        this.yearGroup = yearGroup;
        this.currentGrade = currentGrade;
        this.additionalInfo = additionalInfo;
    }

    public int getChildId() {
        return childId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getYearGroup() {
        return yearGroup;
    }

    public void setYearGroup(Integer yearGroup) {
        this.yearGroup = yearGroup;
    }

    public String getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(String currentGrade) {
        this.currentGrade = currentGrade;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Integer getParentId() {
        return parent.getParentId();
    }
}
