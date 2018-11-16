module equalsverifier.prefabvalues {
    requires equalsverifier.reflection;
    requires equalsverifier.service;
    exports equalsverifier.prefabvalues;
    exports equalsverifier.prefabvalues.factories;
    requires com.github.spotbugs.annotations;
    requires java.rmi;
    requires java.sql;
    requires equalsverifier.exceptions;


    requires java.datatransfer;
    requires java.desktop;
    requires java.naming;
}
