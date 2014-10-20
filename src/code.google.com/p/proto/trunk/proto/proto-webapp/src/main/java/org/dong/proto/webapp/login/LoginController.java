package org.dong.proto.webapp.login;

import javax.annotation.Resource;

import org.dong.proto.core.constants.OSConstants;
import org.dong.proto.modules.permission.user.User;
import org.dong.proto.modules.permission.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/login")
@SessionAttributes({OSConstants.SESSION_KEY_USER,"randCode"})
public class LoginController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/back")
	public String login(ModelMap model,SessionStatus sessionStatus,
			@ModelAttribute("randCode") String randCode,
			String username,String password,String valdateCode) throws Exception{
		
		User user = userService.backLogin(randCode,username,password,valdateCode);
		model.addAttribute(OSConstants.SESSION_KEY_USER, user);
		
//		sessionStatus.setComplete();
		return "back/index";
	}
	
}
