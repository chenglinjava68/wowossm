package cn.tf.wowossm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import redis.clients.jedis.Jedis;
import cn.tf.wowossm.dao.JedisClient;
import cn.tf.wowossm.po.Userinfo;
import cn.tf.wowossm.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private JedisClient jedisClient;
	
	
	
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
	public String register(Userinfo user,HttpSession session){
		
		int count= userService.register(user);
		
		Long  userId= user.getUsid();
		if(count>0){
			session.setAttribute("userId",userId);
			return "forward:/mail/active?usId="+user.getUsid();
			/*return "redirect:/page/login.html";*/
		}else{
			return "redirect:/page/reg.html";
		}
		
	}
	
	
	@RequestMapping("/login")
	public String login(Userinfo user,HttpSession session){
	
		user=userService.login(user);
		if(user!=null){
			if(user.getStatus()==0){
				//未激活
				return "forward:/mail/active?email="+user.getEmail()+"&usId="+user.getUsid();
			}else{
				session.setAttribute("user", user);
				return "redirect:/index.html";
			}	
		}else{
			return "redirect:/page/login.html";
		}
	}
	
	@RequestMapping("/active/{usId}/{activeCode}")
	public String active(@PathVariable("usId") Integer usId,@PathVariable("activeCode") String activeCode,HttpSession session){
		
		//从redis中取出来
		String srcActiveCode=jedisClient.get("WOWO_ACTIVECODE_KEY:"+usId);
		System.out.println(srcActiveCode);
		//Object srcActiveCode=session.getAttribute("activeCode");
		if(((String) srcActiveCode).intern()==activeCode.intern()){
			if(userService.activeUser(usId)){
				return "redirect:/page/login.html";
			}
		}
		return "redirect:/page/regok.html";
	}
	

}
