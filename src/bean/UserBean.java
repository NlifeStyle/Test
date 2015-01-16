package bean;

public class UserBean implements Ibean {
	private String name;
	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void addServPath(String path) {
		// TODO Auto-generated method stub

	}
}
