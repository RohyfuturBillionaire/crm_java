package site.easy.to.build.crm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "trigger_lead")
public class ProfilTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profil_id")
    private int leadId;

    @Column(name = "name")
    @NotBlank(message = "Name is required")
    private String name;

    public void setLeadId(int leadId) {
        this.leadId = leadId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeadId() {
        return leadId;
    }

    public String getName() {
        return name;
    }
    
}
