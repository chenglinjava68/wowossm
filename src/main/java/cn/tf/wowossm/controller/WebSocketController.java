package cn.tf.wowossm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
public class WebSocketController extends TextWebSocketHandler{
	
	@Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
       System.out.println("message="+message);
       super.handleTextMessage(session, message);
    }



}
