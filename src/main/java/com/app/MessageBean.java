package com.app;

import java.io.Serializable;
import java.util.UUID;

public class MessageBean implements Serializable {

	private static final long serialVersionUID = -5653209468651989384L;
	
	private String name;
	private UUID uuid;
	private String clientMessage;
	private String serverMessage;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public String getClientMessage() {
		return clientMessage;
	}
	public void setClientMessage(String clientMessage) {
		this.clientMessage = clientMessage;
	}
	public String getServerMessage() {
		return serverMessage;
	}
	public void setServerMessage(String serverMessage) {
		this.serverMessage = serverMessage;
	}
	
	
}
