package com.testclient.app;

import java.util.Random;
import java.util.UUID;

public class MessagesUtil {

	public static String getServerHelloMsg(UUID uuid) {
		return "HI, I’M session :" + uuid + "\n";
	}
	
	public static String getServerResponceMsg(String value) {
		return "HI " + value + "\n";
	}
	
	public static String getServerEndMsg(String name, int m) {
		return "BYE " +name +", WE SPOKE FOR " + m +" MS! \n";
	}
	
	public static String getNotSupportedMsg() {
		return "SORRY, I DIDN’T UNDERSTAND THAT \n";
	}
	
	public static String getClientHiMsg(String value) {
		return "HI, I’M " + value + "\n";
	}
	
	public static String getClientEndMsg() {
		return "BYE MATE! \n";
	}
	
	public static String generateName() {
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ-".toCharArray();
		StringBuilder sb = new StringBuilder(5);
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	public static UUID getUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid;
	}
}
