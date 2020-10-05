package dev.domain;

import javax.persistence.*;

@Entity
public class RoleEmploye {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "collegue_id")
    private Employe collegue;

    @Enumerated(EnumType.STRING)
    private Role role;

    public RoleEmploye() {
    }

    public RoleEmploye(Employe collegue, Role role) {
        this.collegue = collegue;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Employe getCollegue() {
        return collegue;
    }

    public void setCollegue(Employe collegue) {
        this.collegue = collegue;
    }
}