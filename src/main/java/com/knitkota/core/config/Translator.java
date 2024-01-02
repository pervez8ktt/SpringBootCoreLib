package com.knitkota.core.config;

import java.text.MessageFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.knitkota.core.model.MessageModel;
import com.knitkota.core.utils.Utils;

@Component
public class Translator {

	private static ResourceBundleMessageSource messageSource;

	@Autowired
	Translator(ResourceBundleMessageSource messageSource) {
		Translator.messageSource = messageSource;
	}

	public static String toLocale(String msgCode) {
		String message = "";
		try {
			Locale locale = getApiLocale();

			message = messageSource.getMessage(msgCode, null, locale);

		} catch (Exception e) {
			message = msgCode;
		}
		return message;
	}

	public static String toLocale(String msgCode, String lang) {
		Locale locale = Locale.forLanguageTag(lang);

		String message = messageSource.getMessage(msgCode, null, locale);
		return message;
	}

	public static String messageTranslator(String messageModel, Object... param) {

		String message;
		if (messageModel != null && !messageModel.isEmpty()) {
			message = Translator.toLocale(messageModel);
		} else {
			message = "";
		}

		if (param != null) {
			message = MessageFormat.format(message, param);
		}

		return message;
	}

	public static Locale getApiLocale() {

		Locale locale = null;

		try {

			HttpServletRequest httpServletRequest = Utils.getHttpServletRequest();

			String headerLang = httpServletRequest.getHeader("Accept-Language");

			if (headerLang != null) {
				locale = Locale.forLanguageTag(headerLang);
			} else {
				String requestLang = httpServletRequest.getParameter("lang");

				if (requestLang != null) {
					locale = Locale.forLanguageTag(requestLang);
				}

			}

		} catch (Exception e) {

		}

		if (locale == null) {
			locale = Locale.forLanguageTag("fr");
		}

		return locale;
	}

	public static String messageTranslator(MessageModel messageModel) {

		return messageTranslator(messageModel.getMessage(), messageModel.getParams());
	}

}