package cn.tf.wowossm.controller;

import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tf.wowossm.po.Userinfo;

@Controller
@RequestMapping("/mail")
public class MailController {
	
	@Autowired
	private JavaMailSender  mailSender;
	
	@RequestMapping("/active")
	public String active(HttpServletRequest request,Userinfo user,HttpSession session){
		String activeCode=UUID.randomUUID().toString();
		/*request.getSession().setAttribute("activeCode", activeCode);*/
		
		session.setAttribute("activeCode", activeCode);
		Object usId= session.getAttribute("userId");
		
		String activePath=request.getRequestURL().toString().replace("mail/active", "user/active/"+usId+"/"+activeCode);
		
		try {
			MimeMessage  message=mailSender.createMimeMessage();
			MimeMessageHelper  helper=new MimeMessageHelper(message,true);
			helper.setFrom("xingtian@tianfang1314.cn");
			helper.setTo(user.getEmail());
			helper.setSubject("会员激活");
			helper.setText("<a href='"+activePath+"'>会员激活</a><br>如果连接无效请把激活地址"+activePath+"复制到浏览器地址",true);
			mailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());

		}
		return "redirect:/page/regok.html?1";
	}
	

}
