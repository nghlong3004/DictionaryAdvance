package model;

import java.util.TreeMap;

public class Account {
	private TreeMap<String, String> id;
	private DataSource dataSource;
	private Repository<String> repository;
	private User user;
	public Account() {
		user = new User();
		dataSource = new DataSource();
		RepositoryFactory repositoryFactory = new RepositoryFactory(dataSource);
		repository = repositoryFactory.creatRepository();
		id = repository.load();
		createrUser();
	}
	private void createrUser() {
		user.name(repository.loadUser());
		if(user.getName() != null) {
			user.password(getPassword(user.getName()));
		}
	}
	public TreeMap<String, String> getId(){
		return this.id;
	}
	public boolean isId(String username) {
		if(id == null) {
			return false;
		}
		if(id.containsKey(username)) {
			return true;
		}
		return false;
	}
	public String getPassword(String username) {
		if(this.id.isEmpty()) {
			return null;
		}
		return new String(this.id.get(username));
	}
	public void addUser(String username, String password) {
		this.id.put(username, password);
		repository.save(username, this);
	}
	public void saveDataUserCur(User user) {
		String idName = "";
		if(user != null) {
			idName = user.getName();
		}
		repository.save("user.txt", idName);
	}
	public User getUser() {
		return this.user;
	}
	public DataSource getDataSource() {
		return dataSource;
	}
}