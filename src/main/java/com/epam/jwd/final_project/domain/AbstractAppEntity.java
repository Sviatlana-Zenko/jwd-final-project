package com.epam.jwd.final_project.domain;

// implements Serializable, Cloneable?
public abstract class AbstractAppEntity implements AppEntity {

    private Long id;

    public AbstractAppEntity() {
    }

    public AbstractAppEntity(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Нормально ли такое переопределение?
    @Override
    public String toString() {
        return "id=" + id;
    }
}
