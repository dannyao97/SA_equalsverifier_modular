package equalsverifier.checkers;

import equalsverifier.Warning;
import equalsverifier.checkers.fieldchecks.NullPointerExceptionFieldCheck;
import equalsverifier.reflection.ClassAccessor;
import equalsverifier.util.Configuration;

public class NullChecker<T> implements Checker {
    private final Configuration<T> config;

    public NullChecker(Configuration<T> config) {
        this.config = config;
    }

    @Override
    public void check() {
        if (config.getWarningsToSuppress().contains(Warning.NULL_FIELDS)) {
            return;
        }

        ClassAccessor<T> classAccessor = config.getClassAccessor();
        FieldInspector<T> inspector = new FieldInspector<>(classAccessor, config.getTypeTag());
        inspector.check(new NullPointerExceptionFieldCheck<>(config));
    }
}
