module equalsverifier.reflection {
	exports equalsverifier.reflection;
	exports equalsverifier.reflection.annotations;
    //requires com.github.spotbugs.annotations
    requires com.github.spotbugs.annotations;
    requires equalsverifier.service;
   // requires equalsverifier.prefabvalues;
    requires net.bytebuddy;
    requires org.objectweb.asm;
    requires org.objenesis;

    //requires equalsverifier.exceptions;


}
