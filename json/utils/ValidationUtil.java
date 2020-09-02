package com.softuni.json.utils;

import javax.validation.*;
import java.util.*;


public interface ValidationUtil {

    <T>boolean isValid(T entity);

    <T> Set<ConstraintViolation<T>> violations(T entity);
}
