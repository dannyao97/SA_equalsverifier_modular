package equalsverifier.prefabvalues.factories;

import equalsverifier.prefabvalues.FactoryCache;
import equalsverifier.prefabvalues.PrefabValues;
import equalsverifier.prefabvalues.Tuple;
import equalsverifier.prefabvalues.TypeTag;
import equalsverifier.prefabvalues.factoryproviders.FactoryProvider;
import equalsverifier.reflection.ConditionalInstantiator;

import java.util.LinkedHashSet;

import static equalsverifier.reflection.Util.classes;
import static equalsverifier.reflection.Util.objects;

public class ExternalFactory<T> implements PrefabValueFactory<T> {
    private static final String EXTERNAL_FACTORIES_PACKAGE = "nl.jqno.equalsverifier.internal.prefabvalues.factoryproviders.";

    private final String factoryName;
    private FactoryCache factoryCache;

    public ExternalFactory(String factoryName) {
        this.factoryName = EXTERNAL_FACTORIES_PACKAGE + factoryName;
    }

    @Override
    public Tuple<T> createValues(TypeTag tag, PrefabValues prefabValues, LinkedHashSet<TypeTag> typeStack) {
        if (factoryCache == null) {
            ConditionalInstantiator ci = new ConditionalInstantiator(factoryName);
            FactoryProvider provider = ci.instantiate(classes(), objects());
            factoryCache = provider.getFactoryCache();
            //**'getFactoryCache' return 'equalsverifier.prefabvalues.FactoryCache'
        }

        PrefabValueFactory<T> factory = factoryCache.get(tag.getType());
        return factory.createValues(tag, prefabValues, typeStack);
    }
}
