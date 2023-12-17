package ch.hearc.ig.guideresto.persistence.handlers.specializedHandlers;

import ch.hearc.ig.guideresto.business.EvaluationCriteria;
import ch.hearc.ig.guideresto.persistence.handlers.GenericHandler;

import javax.persistence.EntityManager;

public class EvaluationCriteriaHandler extends GenericHandler<EvaluationCriteria> {
    public EvaluationCriteriaHandler(EntityManager em) {
        super(em, EvaluationCriteria.class);
    }
}
