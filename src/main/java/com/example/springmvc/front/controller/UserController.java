package com.example.springmvc.front.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springmvc.core.util.CipherUtil;
import com.example.springmvc.front.model.User;
import com.example.springmvc.front.services.IUserService;

/**
 * User Controller
 * 
 * @author henryyue
 * 
 */
@Controller
public class UserController {

	private static Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	/**
	 * index page
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/", "/index" })
	public String tologin(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		logger.info("from IP[" + request.getRemoteHost() + "]");
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			return "success";
		}
		return "index";
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password) {

		Subject currentUser = SecurityUtils.getSubject();
		String result = "login";
		if (!currentUser.isAuthenticated()) {
			result = login(currentUser, username, password);
		} else {
			User user = (User) currentUser.getPrincipal();
			if (!user.getUsername().equalsIgnoreCase(username)) {
				currentUser.logout();
				result = login(currentUser, username, password);
			}
		}
		return result;
	}

	/**
	 * 
	 * @param currentUser
	 * @param username
	 * @param password
	 * @return
	 */
	private String login(Subject currentUser, String username, String password) {
		String result = "login";
		password = CipherUtil.generatePassword(password);
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		try {
			currentUser.login(token);
			token.setRememberMe(true);
			result = "success";
		} catch (UnknownAccountException uae) {
			result = "index";
		} catch (IncorrectCredentialsException ice) {
			result = "index";
		} catch (LockedAccountException lae) {
			result = "index";
		} catch (AuthenticationException ae) {
			result = "index";
		}
		return result;
	}

	/**
	 * logout
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "redirect:index";
	}

	/**
	 * check
	 * 
	 * @return
	 */
	@RequestMapping(value = "/chklogin", method = RequestMethod.POST)
	@ResponseBody
	public String chkLogin() {
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			return "false";
		}
		return "true";
	}

}
