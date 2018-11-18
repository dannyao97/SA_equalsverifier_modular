module equalsverifier.checkers.fieldchecks {
	//requires equalsverifier.checkers;
	requires equalsverifier.reflection;

    requires com.github.spotbugs.annotations;
	//requires equalsverifier.service_exception;
    requires equalsverifier.exceptions;
    requires equalsverifier.utils;
    requires equalsverifier.service;
    exports equalsverifier.checkers.fieldchecks;
}
