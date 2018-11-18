package equalsverifier.internal.checkers;

import equalsverifier.checkers.FieldInspector;
import equalsverifier.checkers.fieldchecks.FieldCheck;
import equalsverifier.reflection.PrefabValues;
import equalsverifier.reflection.TypeTag;
import equalsverifier.reflection.ClassAccessor;
import equalsverifier.reflection.FieldAccessor;
import equalsverifier.reflection.ObjectAccessor;
import equalsverifier.reflection.annotations.AnnotationCache;
import equalsverifier.testhelpers.FactoryCacheFactory;
import equalsverifier.testhelpers.types.Point;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class FieldInspectorTest {
    private final PrefabValues prefabValues = new PrefabValues(FactoryCacheFactory.withPrimitiveFactories());
    private final ClassAccessor<Point> accessor = ClassAccessor.of(Point.class, prefabValues);

    @Test
    public void objectsAreReset_whenEachIterationBegins() {
        FieldInspector<Point> inspector = new FieldInspector<>(accessor, TypeTag.NULL);

        inspector.check(new ResetObjectForEachIterationCheck());
    }

    @Test
    public void objectsAreReset_whenEachIterationBegins_givenNullObjects() {
        FieldInspector<Point> inspector = new FieldInspector<>(accessor, TypeTag.NULL);
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
