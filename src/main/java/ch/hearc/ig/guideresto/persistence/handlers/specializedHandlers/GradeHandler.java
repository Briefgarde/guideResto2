package ch.hearc.ig.guideresto.persistence.handlers.specializedHandlers;

import ch.hearc.ig.guideresto.business.Grade;
import ch.hearc.ig.guideresto.persistence.handlers.GenericHandler;

import javax.persistence.EntityManager;

public class GradeHandler extends GenericHandler<Grade> {
    public GradeHandler (EntityManager em){
        super(em, Grade.class);
    }
}
