package cardealer.util;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidationUtil {

    //Generic cause we don't know, what kind of data format we should become
    <O> boolean isValid(O object);

    <O> Set<ConstraintViolation<O>> violation(O object);
}
