package com.neha.empmgmt.validator;

import java.util.List;

public interface Validator<T> {
	List<String> validate(T objectToValidate);
}
