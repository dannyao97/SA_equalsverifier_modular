module equalsverifier.prefabvalues {
    //requires equalsverifier.reflection

    requires equalsverifier.service;
    requires equalsverifier.reflection;

    exports equalsverifier.prefabvalues;
    exports equalsverifier.prefabvalues.factories;
    exports equalsverifier.prefabvalues.factoryproviders;


    requires com.github.spotbugs.annotations;
    requires java.rmi;
    requires java.sql;
   // requires equalsverifier.exceptions;


    requires java.datatransfer;
    requires java.desktop;
    requires java.naming;

    requires transitive com.google.common;
    requires org.joda.time;
}
