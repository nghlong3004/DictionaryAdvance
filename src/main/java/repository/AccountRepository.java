package repository;

import static util.repository.Utils.*;

import java.util.ArrayList;
import java.util.List;

import model.DataSource;
import model.account.User;

public class AccountRepository implements AccountInterface{
	
	private static AccountInterface instance;

	private DataRepository<User> repository;
	private User user;
	private List<User> accounts;
	
	public static synchronized AccountInterface getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new AccountRepository(dataSource);
        }
        return instance;
    }
	
	@SuppressWarnings("unchecked")
	private AccountRepository(DataSource dataSource) {
		DataRepositoryFactory<User> repositoryFactory = new DataRepositoryFactory<>(dataSource, User.class);
		repository = repositoryFactory.creatRepository();
		accounts = new ArrayList<User>();
		accounts = repository.load(accounts.getClass());
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
	// login 
	@Override
	public boolean isUser(User newUser) {
		for (User user : accounts) {
			if(user.getUsername().equalsIgnoreCase(newUser.getUsername())) {
				return user.getPassword().equals(newUser.getPassword());
			}
		}
		return false;
	}
	// update if login successfully
	@Override
	public void handleLoginSuccess(String username, boolean remember) {
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
	@Override
	public void addUser(User user) {
		accounts.add(user);
		saveAccounts();
	}
	// save 
	@Override
	public void saveAccounts() {
		repository.save(accounts);
	}
	
	
	@Override
	public User getUser() {
		return this.user;
	}

	@Override
	public boolean isUsernameAvailable(String username) {
		for (User user : accounts) {
			if(user.getUsername().equalsIgnoreCase(username)) {
				return false;
			}
		}
		return true;
	}
	
	
}
