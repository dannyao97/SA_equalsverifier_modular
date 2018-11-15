package equalsverifier.checkers.fieldchecks;

import nl.jqno.equalsverifier.internal.reflection.FieldAccessor;

@FunctionalInterface
public interface FieldCheck {
    void execute(FieldAccessor referenceAccessor, FieldAccessor changedAccessor);
}
