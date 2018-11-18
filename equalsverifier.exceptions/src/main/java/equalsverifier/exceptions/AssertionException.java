package equalsverifier.exceptions;

//import equalsverifier.exceptions.Formatter;

//import java.util.ServiceLoader;
//import equalsverifier.formatService.formatService;


import equalsverifier.service.MessagingException;

/**
 * Signals that an EqualsVerfier assertion has failed.
 */
@SuppressWarnings("serial")
public class AssertionException extends MessagingException {
    //ServiceLoader<formatService> loader = ServiceLoader.load(formatService.class);
    public AssertionException(Formatter message){
        super(message.format());
    }

    public AssertionException(Formatter message, Throwable cause) {
        super(message.format(), cause);
    }
}
