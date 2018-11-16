module equalsverifier {
	requires equalsverifier.checkers;
	requires equalsverifier.checkers.fieldchecks;
	requires equalsverifier.exceptions;
	requires equalsverifier.prefabvalues;
	requires equalsverifier.prefabvalues.factories;
	requires equalsverifier.prefabvalues.factoryproviders;
	requires equalsverifier.utils;
    requires org.objectweb.asm;

    requires equalsverifier.service;
}
