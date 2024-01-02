package com.knitkota.core.validation;

import java.util.function.Supplier;
import java.util.regex.Pattern;


public class Validation<T> {

	public static final String textOnly = "^[a-zA-ZÀ-ÿ-.' ]*$";

	private Pattern textOnlyPattern = Pattern.compile(textOnly);
	private Pattern fiveDigitOnly = Pattern.compile("^[0-9]{5}$");
	private Pattern eightDigitOnly = Pattern.compile("^[0-9]{8}$");
	private Pattern tenDigitOnly = Pattern.compile("^[0-9]{10}$");
	private Pattern nineDigitOnly = Pattern.compile("^[0-9]{9}$");
	private Pattern forteenDigitOnly = Pattern.compile("^[0-9]{14}$");
	private Pattern seventeenDigitOnly = Pattern.compile("^[0-9]{17}$");
	private Pattern tenOrElevenDigitOnly = Pattern.compile("^[0-9]{10}$||^[0-9]{11}$");

	private Pattern password = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,15}$");
	private Pattern username = Pattern.compile("^(?=[a-zA-Z0-9._]{5,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
	private Pattern email = Pattern.compile("^.+@.+\\..+$");

	private final T value;

	private Validation(T value) {

		this.value = value;

	}

	public static <T> Validation<T> getInstance(T value) {

		Validation<T> validation = new Validation<>(value);

		return validation;
	}

	public <X extends Throwable> T isNullOrEmpty(Supplier<? extends X> exceptionSupplier) throws X {
		if (value == null) {
			throw exceptionSupplier.get();
		}

		
		
		if (value instanceof String) {
			String s = (String) value;

			if (s.isEmpty()) {
				throw exceptionSupplier.get();
			}
			
			
		}

		return value;
	}

	

	public <X extends Throwable> String validateName(Supplier<? extends X> exceptionSupplier) throws X {

		if (!(value instanceof String)) {
			throw exceptionSupplier.get();
		}

		String name = (String) value;

		isNullOrEmpty(exceptionSupplier);

		name = name.trim();

		validateTextOnly(exceptionSupplier);

		return name;
	}

	public <X extends Throwable> boolean validateTextOnly(Supplier<? extends X> exceptionSupplier) throws X {

		if (!(value instanceof String)) {
			throw exceptionSupplier.get();
		}

		String v = (String) value;

		return textOnlyPattern.matcher(v).matches();
	}

	public <X extends Throwable> String validateEmail(Supplier<? extends X> exceptionSupplier) throws X {

		isNullOrEmpty(exceptionSupplier);

		if (!(value instanceof String)) {
			throw exceptionSupplier.get();
		}

		String v = (String) value;

		v = v.trim().toLowerCase();

		if (!email.matcher(v).matches()) {
			throw exceptionSupplier.get();
		}

		return v;

	}

	
	
	public <X extends Throwable> String validatePhoneNumber(Supplier<? extends X> exceptionSupplier) throws X {

		isNullOrEmpty(exceptionSupplier);

		if (!(value instanceof String)) {
			throw exceptionSupplier.get();
		}

		String v = (String) value;

		v = v.trim().toLowerCase();

		v = v.replace("-", "");

		if (!tenDigitOnly.matcher(v).matches()) {
			throw exceptionSupplier.get();
		}

		return v;

	}

	public <X extends Throwable> String validatePassword(Supplier<? extends X> exceptionSupplier) throws X {

		isNullOrEmpty(exceptionSupplier);

		if (!(value instanceof String)) {
			throw exceptionSupplier.get();
		}

		String v = (String) value;

		if (!password.matcher(v).matches()) {
			throw exceptionSupplier.get();
		}

		return v;

	}

	

}
