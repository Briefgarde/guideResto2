package ch.hearc.ig.guideresto.business;

import javax.persistence.*;

@Entity
@Table(name = "CRITERES_EVALUATION")
public class EvaluationCriteria {

    @Id
    @Column(name = "NUMERO", nullable = false)
    @GeneratedValue(  strategy= GenerationType.SEQUENCE,  generator="hibernate_seq_criteria")
    @SequenceGenerator(name = "hibernate_seq_criteria", sequenceName = "SEQ_CRITERES_EVALUATION", allocationSize = 1)
    private Integer id;
    @Column(name = "NOM", nullable = false)
    private String name;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    public EvaluationCriteria(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public EvaluationCriteria(){}

    public static EvaluationCriteria createCriteria(String name, String description){
        EvaluationCriteria evaluationCriteria = new EvaluationCriteria();
        evaluationCriteria.setDescription(description);
        evaluationCriteria.setName(name);
        return evaluationCriteria;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}