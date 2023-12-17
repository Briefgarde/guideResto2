package ch.hearc.ig.guideresto.persistence.handlers.specializedHandlers;

import ch.hearc.ig.guideresto.business.Evaluation;
import ch.hearc.ig.guideresto.persistence.handlers.GenericHandler;

import javax.persistence.EntityManager;

public class EvaluationHandler extends GenericHandler<Evaluation> {
    public EvaluationHandler(EntityManager em) {
        super(em, Evaluation.class);
    }
}
