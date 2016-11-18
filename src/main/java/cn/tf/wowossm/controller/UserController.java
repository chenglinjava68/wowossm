package cn.tf.wowossm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tf.wowossm.po.Userinfo;
import cn.tf.wowossm.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String showIndex() {
		return "redirect:/index.html";
	}  
	
	@RequestMapping("/reg")
	public String showReg() {
		return "redirect:/page/reg.html";
	}
	
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "redirect:/page/login.html";
	}
	
	
	
	@RequestMapping("/register")
	public String register(Userinfo user){
		System.out.println(user.getUname()+" "+user.getCity());
		int count = userService.register(user);
		if(count>0){
			return "redirect:/page/login.html";
		}else{
			return "redirect:/page/reg.html";
		}
	}
	
	
	@RequestMapping("/login")
	public String login(Userinfo user){
		user=userService.login(user);
		if(user!=null){
			return "redirect:/page/index.html";
		}else{
			return "redirect:/page/login.html";
		}
			
			
		
	}
	
	

}
