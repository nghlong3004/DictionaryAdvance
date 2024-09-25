package util.view;

import controller.UserController;
import repository.AccountRepository;
import service.UserService;

public class ObjectContainer {
	
	private final static AccountRepository ACCOUNT = AccountRepository.getInstance();
	private final static UserService USERSERVICE = UserService.getInstance(ACCOUNT);
	private final static UserController USERCONTROLLER = new UserController(USERSERVICE);
	
	public UserController getControllerInstance() {
		return USERCONTROLLER;
	}
	
}
