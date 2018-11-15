package nl.jqno.equalsverifier.internal.checkers.mod_checkers.fieldchecks;

import nl.jqno.equalsverifier.internal.reflection.FieldAccessor;

@FunctionalInterface
public interface FieldCheck {
    void execute(FieldAccessor referenceAccessor, FieldAccessor changedAccessor);
}
