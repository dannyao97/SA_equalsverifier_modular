package equalsverifier.checkers.fieldchecks;

import nl.jqno.equalsverifier.internal.gentype.TypeTag;
import nl.jqno.equalsverifier.internal.prefabservice.PrefabAbstract;
import nl.jqno.equalsverifier.internal.reflection.FieldAccessor;
import nl.jqno.equalsverifier.internal.util.Formatter;

import java.util.function.Predicate;

import static nl.jqno.equalsverifier.internal.util.Assert.fail;

public class MutableStateFieldCheck implements FieldCheck {
    private final PrefabAbstract prefabAbstract;
    private final TypeTag typeTag;
    private final Predicate<FieldAccessor> isCachedHashCodeField;

    public MutableStateFieldCheck(PrefabAbstract prefabAbstract, TypeTag typeTag, Predicate<FieldAccessor> isCachedHashCodeField) {
        this.prefabAbstract = prefabAbstract;
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

        changedAccessor.changeField(prefabAbstract, typeTag);

        boolean equalsChanged = !reference.equals(changed);

        if (equalsChanged && !referenceAccessor.fieldIsFinal()) {
            fail(Formatter.of("Mutability: equals depends on mutable field %%.", referenceAccessor.getFieldName()));
        }

        referenceAccessor.changeField(prefabAbstract, typeTag);
    }
}
