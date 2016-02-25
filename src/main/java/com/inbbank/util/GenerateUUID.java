package com.inbbank.util;

import java.util.UUID;

public final class GenerateUUID {
	
	 private GenerateUUID() {
         
	    }

	public static final String getRendomString(){
	    //generate random UUIDs
	    return String.valueOf(UUID.randomUUID());
	    	  }
	  
	

}
