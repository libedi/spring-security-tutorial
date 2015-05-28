package com.libedi.test;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/**
	 * login form test
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login")
	public String loginForm(Model model){
		return "login";
	}
	
	/**
	 * authentication test
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/loginSuccess")
	public String loginSuccess(Model model, Authentication authentication){
		model.addAttribute("id", authentication.getPrincipal().toString());
		// spring-security 설정파일에서 authentication-manager 에 erase-credentials="false" 를 하지 않으면
		// 인증 후에는 NULL처리가 된다.
//		model.addAttribute("pw", authentication.getCredentials().toString());
		return "loginSuccess";
	}
	
	@RequestMapping(value="/loginDuplicate")
	public String loginDuplicate(Model model){
		return "loginDuplicate";
	}
	
}
