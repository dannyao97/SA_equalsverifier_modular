package nl.jqno.equalsverifier.internal.checkers;

import nl.jqno.equalsverifier.internal.checkers.mod_checkers.fieldchecks.FieldCheck;
import nl.jqno.equalsverifier.internal.checkers.mod_checkers.FieldInspector;
import nl.jqno.equalsverifier.internal.prefabvalues.PrefabValues;
import nl.jqno.equalsverifier.internal.prefabvalues.TypeTag;
import nl.jqno.equalsverifier.internal.reflection.ClassAccessor;
import nl.jqno.equalsverifier.internal.reflection.FieldAccessor;
import nl.jqno.equalsverifier.internal.reflection.ObjectAccessor;
import nl.jqno.equalsverifier.internal.reflection.annotations.AnnotationCache;
import nl.jqno.equalsverifier.testhelpers.FactoryCacheFactory;
import nl.jqno.equalsverifier.testhelpers.types.Point;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class FieldInspectorTest {
    private final PrefabValues prefabValues = new PrefabValues(FactoryCacheFactory.withPrimitiveFactories());
    private final ClassAccessor<Point> accessor = ClassAccessor.of(Point.class, prefabValues);

    @Test
    public void objectsAreReset_whenEachIterationBegins() {
        nl.jqno.equalsverifier.internal.checkers.mod_checkers.FieldInspector<Point> inspector = new nl.jqno.equalsverifier.internal.checkers.mod_checkers.FieldInspector<>(accessor, TypeTag.NULL);

        inspector.check(new ResetObjectForEachIterationCheck());
    }

    @Test
    public void objectsAreReset_whenEachIterationBegins_givenNullObjects() {
        nl.jqno.equalsverifier.internal.checkers.mod_checkers.FieldInspector<Point> inspector = new FieldInspector<>(accessor, TypeTag.NULL);
        Set<String> nullFields = new HashSet<>();
        AnnotationCache annotationCache = new AnnotationCache();

        inspector.checkWithNull(nullFields, annotationCache, new ResetObjectForEachIterationCheck());
    }

    private final class ResetObjectForEachIterationCheck implements FieldCheck {
        private Object originalReference;
        private Object originalChanged;

        @Override
        public void execute(FieldAccessor referenceAccessor, FieldAccessor changedAccessor) {
            if (originalReference == null) {
                originalReference = ObjectAccessor.of(referenceAccessor.getObject()).copy();
                originalChanged = ObjectAccessor.of(changedAccessor.getObject()).copy();
            }
            else {
                assertEquals(originalReference, referenceAccessor.getObject());
                assertEquals(originalChanged, changedAccessor.getObject());
            }

            referenceAccessor.changeField(prefabValues, TypeTag.NULL);
            changedAccessor.changeField(prefabValues, TypeTag.NULL);
        }
    }
}
