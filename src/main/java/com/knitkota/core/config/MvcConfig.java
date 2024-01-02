package com.knitkota.core.config;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
// public class MvcConfig implements WebMvcConfigurer {

public class MvcConfig extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {

	List<Locale> LOCALES = Arrays.asList(new Locale("en"), new Locale("fr"), new Locale("fren"));

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String headerLang = request.getHeader("Accept-Language");
		return headerLang == null || headerLang.isEmpty() ? Locale.getDefault()
				: Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
		rs.setBasename("messages");
		rs.setDefaultEncoding("UTF-8");
		rs.setUseCodeAsDefaultMessage(true);
		return rs;
	}

	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/error").setViewName("ErrorPage");
		
	}

	
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("fr"));
		//localeResolver.setDefaultLocale(Locale.getDefault());
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang");
		return localeInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(localeChangeInterceptor());
	}

}
