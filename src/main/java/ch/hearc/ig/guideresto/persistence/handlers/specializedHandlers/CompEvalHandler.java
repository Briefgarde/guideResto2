package ch.hearc.ig.guideresto.persistence.handlers.specializedHandlers;

import ch.hearc.ig.guideresto.business.CompleteEvaluation;
import ch.hearc.ig.guideresto.persistence.handlers.GenericHandler;

import javax.persistence.EntityManager;

public class CompEvalHandler extends GenericHandler<CompleteEvaluation> {
    public CompEvalHandler(EntityManager em) {
        super(em, CompleteEvaluation.class);
    }
}
