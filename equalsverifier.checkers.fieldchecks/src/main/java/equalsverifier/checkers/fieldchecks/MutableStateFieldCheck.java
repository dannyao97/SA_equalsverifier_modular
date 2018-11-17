package equalsverifier.checkers.fieldchecks;

import equalsverifier.reflection.PrefabValues;
import equalsverifier.reflection.TypeTag;
import equalsverifier.reflection.FieldAccessor;

import java.util.function.Predicate;

import static equalsverifier.exceptions.Assert.fail;
import equalsverifier.exceptions.Formatter;

public class MutableStateFieldCheck implements FieldCheck {
    private final PrefabValues prefabValues;
    private final TypeTag typeTag;
    private final Predicate<FieldAccessor> isCachedHashCodeField;

    public MutableStateFieldCheck(PrefabValues prefabValues, TypeTag typeTag, Predicate<FieldAccessor> isCachedHashCodeField) {
        this.prefabValues = prefabValues;
        this.typeTag = typeTag;
        this.isCachedHashCodeField = isCachedHashCodeField;
    }

    @Override
    public void execute(FieldAccessor referenceAccessor, FieldAccessor changedAccessor) {
        if (isCachedHashCodeField.test(referenceAccessor)) {
            return;
        }

        Object reference = referenceAccessor.getObject();
        Object changed = changedAccessor.getObject();

        changedAccessor.changeField(prefabValues, typeTag);

        boolean equalsChanged = !reference.equals(changed);

        if (equalsChanged && !referenceAccessor.fieldIsFinal()) {
            fail(Formatter.of("Mutability: equals depends on mutable field %%.", referenceAccessor.getFieldName()));
        }

        referenceAccessor.changeField(prefabValues, typeTag);
    }
}
