package equalsverifier.checkers.fieldchecks;

import nl.jqno.equalsverifier.internal.gentype.TypeTag;
import nl.jqno.equalsverifier.internal.prefabservice.PrefabAbstract;
import nl.jqno.equalsverifier.internal.reflection.FieldAccessor;
import nl.jqno.equalsverifier.internal.util.Formatter;

import static nl.jqno.equalsverifier.internal.util.Assert.assertTrue;

public class SymmetryFieldCheck implements FieldCheck {
    private final PrefabAbstract prefabAbstract;
    private final TypeTag typeTag;

    public SymmetryFieldCheck(PrefabAbstract prefabAbstract, TypeTag typeTag) {
        this.prefabAbstract = prefabAbstract;
        this.typeTag = typeTag;
    }

    @Override
    public void execute(FieldAccessor referenceAccessor, FieldAccessor changedAccessor) {
        checkSymmetry(referenceAccessor, changedAccessor);

        changedAccessor.changeField(prefabAbstract, typeTag);
        checkSymmetry(referenceAccessor, changedAccessor);

        referenceAccessor.changeField(prefabAbstract, typeTag);
        checkSymmetry(referenceAccessor, changedAccessor);
    }

    private void checkSymmetry(FieldAccessor referenceAccessor, FieldAccessor changedAccessor) {
        Object left = referenceAccessor.getObject();
        Object right = changedAccessor.getObject();
        assertTrue(Formatter.of("Symmetry: objects are not symmetric:\n  %%\nand\n  %%", left, right),
                left.equals(right) == right.equals(left));
    }
}
