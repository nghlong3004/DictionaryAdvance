package repository;

import static util.Utils.*;

import java.util.List;

import model.DataSource;
import model.account.User;

public class AccountRepository {
	
	private static AccountRepository instance;
	
	private DataSource dataSource;
	private Repository<User> repository;
	private User user;
	private List<User> accounts;
	
	public static synchronized AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
        }
        return instance;
    }
	
	private AccountRepository() {
		dataSource = new DataSource();
		RepositoryFactory<User> repositoryFactory = new RepositoryFactory<>(dataSource, User.class);
		repository = repositoryFactory.creatRepository();
		accounts = repository.loadUsers();
		for (User user : accounts) {
			if(user.isRemember()) {
				this.user = user;
				break;
			}
		}
		if(user == null) {
			user = new User();
		}
		
	}
	
	public boolean isUsername(String username) {
		for (User user : accounts) {
			if(user.getUsername().equalsIgnoreCase(username)) {
				return false;
			}
		}
		return true;
	}
	
	// login 
	public boolean isUser(User newUser) {
		for (User user : accounts) {
			if(user.getUsername().equalsIgnoreCase(newUser.getUsername())) {
				return user.getPassword().equals(newUser.getPassword());
			}
		}
		return false;
	}
	// update if login successfully
	public void updateUser(String username, boolean remember) {
		for(int i = 0; i < accounts.size(); ++i) {
			User curUser = accounts.get(i);
			if(username.equalsIgnoreCase(curUser.getUsername())) {
				curUser.setLastLogin(nowTimeinString());
				curUser.setRemember(remember);
				accounts.set(i, curUser);
			}
			else {
				if(curUser.isRemember()) {
					curUser.setRemember(false);
					accounts.set(i, curUser);
				}
			}
		}
		saveAccounts();
	}
	// register
	public void addUser(User user) {
		accounts.add(user);
		saveAccounts();
	}
	// save 
	public void saveAccounts() {
		repository.saveUsers(accounts);
	}
	
	
	public User getUser() {
		return this.user;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
