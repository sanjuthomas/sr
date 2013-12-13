package com.scrumretro.util;

import java.util.Random;

public class UIDGenerator {

	
	public String getValue(){
		
		return getValue(null);
	}
	
	public String getValue(String prefix){
		
		return prefix==null?new Random().nextLong()+"":prefix+new Random().nextLong();
    }
	
}
