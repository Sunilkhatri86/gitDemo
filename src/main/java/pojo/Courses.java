package pojo;

import java.util.List;

public class Courses {
	
	
	private List<WebAutomation> webAutomation;
	private List<API> api;
	private List<Mobile> mobile;
	
	
	

	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public List<API> getApi() {
		return api;
	}
	public List<Mobile> getMobile() {
		return mobile;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public void setApi(List<API> api) {
		this.api = api;
	}
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}

}
