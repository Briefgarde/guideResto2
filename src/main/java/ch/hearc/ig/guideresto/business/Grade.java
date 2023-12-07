package ch.hearc.ig.guideresto.business;


import javax.persistence.*;

@Entity
@Table(name = "NOTES")
public class Grade {

    @Id
    @Column(name = "NUMERO", nullable = false)
    @GeneratedValue(  strategy= GenerationType.SEQUENCE,  generator="hibernate_seq_notes")
    @SequenceGenerator(name = "hibernate_seq_notes", sequenceName = "SEQ_NOTES", allocationSize = 1)
    private Integer id;

    @Column(name = "note", nullable = false)
    private Integer grade;

    @ManyToOne
    @JoinColumn(name = "FK_EVAL", nullable = false)
    private CompleteEvaluation evaluation;

    @ManyToOne
    @JoinColumn(name = "FK_CRIT", nullable = false)
    private EvaluationCriteria criteria;

    public Grade(Integer id, Integer grade, CompleteEvaluation evaluation, EvaluationCriteria criteria) {
        this.id = id;
        this.grade = grade;
        this.evaluation = evaluation;
        this.criteria = criteria;
    }

    public Grade(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public CompleteEvaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(CompleteEvaluation evaluation) {
        this.evaluation = evaluation;
    }

    public void setCriteria(EvaluationCriteria criteria) {
        this.criteria = criteria;
    }

    public Integer getGrade() {
        return grade;
    }

    public EvaluationCriteria getCriteria() {
        return criteria;
    }
}