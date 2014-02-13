package com.scrumretro.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/connecttoserver/{username}")
public class WebSocket {

    
    public static final Map<String,WebSocket> connections =
            new HashMap<String,WebSocket>();

    
    private Session session;

    public WebSocket() {
       
    }


    public Session getSession() {
		return session;
	}


	public void setSession(Session session) {
		this.session = session;
	}


	@OnOpen
    public void start(Session session,@PathParam("username") String userName) {
    	this.session = session;
    	connections.put(userName,this);
    }


    @OnClose
    public void end() {
        connections.remove(this);
       
    }


    @OnMessage
    public void incoming(String message) {
       
    }


    @OnError
    public void onError(Throwable t) throws Throwable {
    	System.out.println("Chat Error: " + t.toString());
    }

    public void broadcast(String message) throws IOException{
    	this.getSession().getBasicRemote().sendText(message);
    }
   

}
