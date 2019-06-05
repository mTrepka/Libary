package mTrepka.libary.controller;

import lombok.RequiredArgsConstructor;
import mTrepka.libary.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequiredArgsConstructor
public class LoginController {
	private final UserService userService;

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	

}
