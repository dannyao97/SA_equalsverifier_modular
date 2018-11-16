package equalsverifier.prefabvalues.factories;

import equalsverifier.prefabvalues.PrefabValues;
import equalsverifier.prefabvalues.Tuple;
import equalsverifier.prefabvalues.TypeTag;

import java.util.LinkedHashSet;

/**
 * Creates instances of generic types for use as prefab value.
 *
 * @param <T> The type to instantiate.
 */
@FunctionalInterface
public interface PrefabValueFactory<T> {
    /**
     * Creates a tuple of two prefab values.
     *
     * @param tag The typetag of the type for which to create values.
     * @param prefabValues Repository for querying instances of generic types
     *          of the type tag.
     * @param typeStack A stack of {@link TypeTag}s that require tag in order
     *          to be created. Used for recursion detection.
     * @return A "red" instance of {@code T}.
     */
    public Tuple<T> createValues(TypeTag tag, PrefabValues prefabValues, LinkedHashSet<TypeTag> typeStack);
}
