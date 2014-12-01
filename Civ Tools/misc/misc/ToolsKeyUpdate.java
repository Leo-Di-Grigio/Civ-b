package misc;

import java.awt.event.KeyEvent;

public class ToolsKeyUpdate {
	
	public static String update(String str, String key, KeyEvent e, int maxLength){
		if(key.compareTo("Space") == 0 && str.length() < 128){
			return (str += " ");
		}
		
		if(key.compareTo("Backspace") == 0 && str.length() > 0){
			return str.substring(0, str.length()-1);
		}
		
		if(str.length() < maxLength){
			int code = e.getKeyChar();
			
			if((code >= 33 && code <= 126) || (code >= 1040 && code <= 1105)){
				str += (char)code;
			}
		}
		
		return str;
	}
}
