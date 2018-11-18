package equalsverifier.checkers.fieldchecks;

import equalsverifier.reflection.PrefabValues;
import equalsverifier.reflection.TypeTag;
import equalsverifier.reflection.FieldAccessor;
import equalsverifier.reflection.annotations.AnnotationCache;
import equalsverifier.reflection.annotations.SupportedAnnotations;
import equalsverifier.utils.Configuration;

import static equalsverifier.exceptions.Assert.fail;
import equalsverifier.exceptions.Formatter;

public class TransientFieldsCheck<T> implements FieldCheck {
    private final PrefabValues prefabValues;
    private final TypeTag typeTag;
    private final AnnotationCache annotationCache;

    public TransientFieldsCheck(Configuration<T> config) {
        this.prefabValues = config.getPrefabValues();
        this.typeTag = config.getTypeTag();
        this.annotationCache = config.getAnnotationCache();
    }

    @Override
    public void execute(FieldAccessor referenceAccessor, FieldAccessor changedAccessor) {
        Object reference = referenceAccessor.getObject();
        Object changed = changedAccessor.getObject();

        changedAccessor.changeField(prefabValues, typeTag);

        boolean equalsChanged = !reference.equals(changed);
        boolean fieldIsTransient = referenceAccessor.fieldIsTransient() ||
                annotationCache.hasFieldAnnotation(typeTag.getType(), referenceAccessor.getFieldName(), SupportedAnnotations.TRANSIENT);

        if (equalsChanged && fieldIsTransient) {
            fail(Formatter.of("Transient field %% should not be included in equals/hashCode contract.", referenceAccessor.getFieldName()));
        }

        referenceAccessor.changeField(prefabValues, typeTag);
    }
}
