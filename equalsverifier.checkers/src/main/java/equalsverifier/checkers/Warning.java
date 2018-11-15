package equalsverifier.checkers;


public enum Warning {
    /**
     * Signals that not all fields are relevant in the {@code equals} contract.
     * {@code EqualsVerifier} will not fail if one or more fields do not affect
     * the outcome of {@code equals}.
     * <p>
     * Only applies to non-transient fields.
     */
    ALL_FIELDS_SHOULD_BE_USED,

    /**
     * Signals that non-final fields are not relevant in the {@code equals}
     * contract. {@code EqualsVerifier} will not fail if one or more non-final
     * fields do not affect the outcome of {@code equals}.
     * <p>
     * Only applies to non-transient fields.
     */
    ALL_NONFINAL_FIELDS_SHOULD_BE_USED,

    /**
     * Disables the check for reference equality on fields.
     * <p>
     * EqualsVerifier will check if the {@code equals} method calls equals on
     * the object fields of the class under test, instead of the {@code ==}
     * operator, since normally this signifies a mistake (e.g. comparing string
     * fields with {@code ==}).
     * <p>
     * However, sometimes {@code ==} is used intentionally, or the field in
     * question doesn't implement {@code equals} itself, so a call to the
     * {@code equals} method of that field is essentially a reference equality
     * check instead of a value equality check. In these cases, this warning can
     * be suppressed.
     */
    REFERENCE_EQUALITY,


    IDENTICAL_COPY,


    IDENTICAL_COPY_FOR_VERSIONED_ENTITY,

    /**
     * Disables the check that verifies {@code equals} is actually overridden.
     * <p>
     * Can be used when a whole package of classes is automatically scanned and
     * presented to EqualsVerifier, and one or more of them don't need to
     * override {@code equals}.
     */
    INHERITED_DIRECTLY_FROM_OBJECT,

    /**
     * Disables the example check for cached {@code hashCode}.
     * <p>
     * The example check verifies that the cached {@code hashCode} is properly
     * initialized. You can use this, if creating an example object is too
     * cumbersome. In this case, null can be passed as an example.
     * <p>
     * Note that suppressing this warning can be dangerous and should only be
     * done in unusual circumstances.
     */
    NO_EXAMPLE_FOR_CACHED_HASHCODE,


    NONFINAL_FIELDS,

    /**
     * Disables checks for {@link NullPointerException} within {@code equals},
     * {@code hashCode} and {@code toString} methods.
     * <p>
     * Sometimes the constructor of a class makes sure no field can be null. If
     * this is the case, and if the fields cannot be made null later in the
     * lifecycle of the class by setters or other methods, suppress this warning
     * to disable the check for {@link NullPointerException}.
     */
    NULL_FIELDS,

    /**
     * Disables the check that all fields used in {@code equals} must also be
     * used in {@code hashCode}.
     * <p>
     * This is useful when bringing legacy systems under test, where you don't
     * want to change the existing {@code hashCode} behaviour but you do want
     * to use EqualsVerifier.
     * <p>
     * Note that {@code hashCode}s with higher distributions give better
     * performance when used in collections such as {@link java.util.HashMap}.
     * Therefore, if possible, you should use all fields that are used in
     * {@code equals}, in {@code hashCode} as well.
     */
    STRICT_HASHCODE,


    STRICT_INHERITANCE,


    TRANSIENT_FIELDS
}
