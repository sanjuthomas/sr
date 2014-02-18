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

import com.scrumretro.exception.ScrumRetroRuntimeException;

/**
 * 
 * @author Ragil
 *
 */
@ServerEndpoint(value = "/connecttoserver/{username}")
public class WebSocket {

	private static final Map<String, WebSocket> connections = new HashMap<String, WebSocket>();

	private Session session;

	public WebSocket() {

	}

	public Session getSession() {
		return session;
	}

	public void setSession(final Session session) {
		this.session = session;
	}

	@OnOpen
	public void start(final Session session,
			@PathParam("username") final String userName) {
		this.session = session;
		connections.put(userName, this);
	}

	@OnClose
	public void end() {
		connections.remove(this);

	}

	@OnMessage
	public void incoming(final String message) {

	}

	@OnError
	public void onError(final Throwable t) throws Throwable {
		throw new ScrumRetroRuntimeException("Error occurred in WebSocket", t);
	}

	public void broadcast(final String message) throws IOException {
		this.getSession().getBasicRemote().sendText(message);
	}

}
