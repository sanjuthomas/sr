package com.scrumretro.enums;
/**
 * This enum shall list all possible type of emails.
 *  
 * @author Ragil
 * 
 */
public enum EmailType {

	USER_REGISTRATION (100) {
		@Override
		public String getSubject() {
			return "Successfully Registered in scrumretro";
		}

		@Override
		public String getTemplate() {
			return "UserRegistration";
		}

		@Override
		public String getFromAddress() {
			return "registration@scrumretro.com";
		}
	};
	
	int typeId ;
	
	
	private EmailType(int typeId){
		this.typeId = typeId;
	}
	
	
	public int getTypeId() {
		return typeId;
	}


	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}


	public abstract String getSubject();
	public abstract String getTemplate();
	public String getFromAddress() {
	  return "admin@scrumretro.com";	
	}
}
