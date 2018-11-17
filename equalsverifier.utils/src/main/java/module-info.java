module equalsverifier.utils {
    exports equalsverifier.utils;
    //exports equalsverifier.utils;
	//opens equalsverifier.service_exception.Formatter to equalsverifier.exceptions;
    requires equalsverifier.service;
    //requires equalsverifier.exceptions;
   // requires equalsverifier.service.exception;
   // uses equalsverifier.service_exception.serviceInterface;

    requires equalsverifier.reflection;
    requires equalsverifier.exceptions;
    requires equalsverifier.prefabvalues;
   // requires equalsverifier.reflection.annotations;
   // requires equalsverifier.prefabvalues.factories;

  //  requires equalsverifier.formatService;
  //  provides equalsverifier.formatService.formatService with equalsverifier.service_exception.Formatter;
}
