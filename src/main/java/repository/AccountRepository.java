package repository;

import static util.repository.Utils.*;

import java.util.ArrayList;
import java.util.List;

import model.DataSource;
import model.account.User;
import model.account.UserInterface;

public class AccountRepository implements UserInterface{
	
	private static AccountRepository instance;

	private DataRepository<User> repository;
	private User user;
	private List<User> accounts;
	
	public static synchronized AccountRepository getInstance(DataSource dataSource) {
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
		System.out.println(accounts.get(0));
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
	public boolean isUser(User newUser) {
		for (User user : accounts) {
			if(user.getUsername().equalsIgnoreCase(newUser.getUsername())) {
				return user.getPassword().equals(newUser.getPassword());
			}
		}
		return false;
	}
	// update if login successfully
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
	public void addUser(User user) {
		accounts.add(user);
		saveAccounts();
	}
	// save 
	public void saveAccounts() {
		repository.save(accounts);
	}
	
	
	public User getUser() {
		return this.user;
	}

	@Override
	public boolean login(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		
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
