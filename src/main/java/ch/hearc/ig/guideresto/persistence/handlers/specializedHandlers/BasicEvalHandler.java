package ch.hearc.ig.guideresto.persistence.handlers.specializedHandlers;

import ch.hearc.ig.guideresto.business.BasicEvaluation;
import ch.hearc.ig.guideresto.persistence.handlers.GenericHandler;

import javax.persistence.EntityManager;

public class BasicEvalHandler extends GenericHandler<BasicEvaluation> {
    public BasicEvalHandler(EntityManager em) {
        super(em, BasicEvaluation.class);
    }
}
