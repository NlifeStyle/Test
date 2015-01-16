package bean;

import java.util.ArrayList;
import java.util.List;

public class JspInfoBean implements Ibean {
	private String sessionId;
	private UserBean user;
	private List<String> servPath = new ArrayList<String>();

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public List<String> getServPath() {
		return servPath;
	}

	public void addServPath(String path) {
		servPath.add(path);
	}

	public void setServPath(List<String> servPath) {
		this.servPath = servPath;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
