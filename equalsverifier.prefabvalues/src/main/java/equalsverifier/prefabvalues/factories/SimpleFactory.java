package equalsverifier.prefabvalues.factories;

import equalsverifier.prefabvalues.PrefabValues;
import equalsverifier.prefabvalues.Tuple;
import equalsverifier.prefabvalues.TypeTag;

import java.util.LinkedHashSet;

/**
 * Implementation of {@link PrefabValueFactory} that holds on to two instances
 * that have already been created.
 */
public class SimpleFactory<T> implements PrefabValueFactory<T> {
    private Tuple<T> tuple;

    public SimpleFactory(T red, T black, T redCopy) {
        this.tuple = new Tuple<>(red, black, redCopy);
    }

    @Override
    public Tuple<T> createValues(TypeTag tag, PrefabValues prefabValues, LinkedHashSet<TypeTag> typeStack) {
        return tuple;
    }
}
