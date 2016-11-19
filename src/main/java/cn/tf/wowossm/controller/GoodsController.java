package cn.tf.wowossm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Controller("goodsHandler")
public class GoodsController extends TextWebSocketHandler{
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		/*String msgStr="肯德基一元抢";*/
		String msgStr="{\"content\":\"肯德基亿元优惠\"}";
		session.sendMessage(new TextMessage(msgStr));  //给每一个进入页面的
	}
	

}
