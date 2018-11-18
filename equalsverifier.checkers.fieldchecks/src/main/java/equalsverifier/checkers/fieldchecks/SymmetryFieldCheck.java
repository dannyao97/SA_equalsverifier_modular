package equalsverifier.checkers.fieldchecks;

import equalsverifier.reflection.PrefabValues;
import equalsverifier.reflection.TypeTag;
import equalsverifier.reflection.FieldAccessor;

import static equalsverifier.exceptions.Assert.assertTrue;
import equalsverifier.exceptions.Formatter;

public class SymmetryFieldCheck implements FieldCheck {
    private final PrefabValues prefabValues;
    private final TypeTag typeTag;

    public SymmetryFieldCheck(PrefabValues prefabValues, TypeTag typeTag) {
        this.prefabValues = prefabValues;
        this.typeTag = typeTag;
    }

    @Override
    public void execute(FieldAccessor referenceAccessor, FieldAccessor changedAccessor) {
        checkSymmetry(referenceAccessor, changedAccessor);

        changedAccessor.changeField(prefabValues, typeTag);
        checkSymmetry(referenceAccessor, changedAccessor);

        referenceAccessor.changeField(prefabValues, typeTag);
        checkSymmetry(referenceAccessor, changedAccessor);
    }

    private void checkSymmetry(FieldAccessor referenceAccessor, FieldAccessor changedAccessor) {
        Object left = referenceAccessor.getObject();
        Object right = changedAccessor.getObject();
        assertTrue(Formatter.of("Symmetry: objects are not symmetric:\n  %%\nand\n  %%", left, right),
                left.equals(right) == right.equals(left));
    }
}
