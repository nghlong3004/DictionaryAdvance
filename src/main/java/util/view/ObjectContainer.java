package util.view;

import controller.UserController;
import repository.ObjectRepository;
import service.UserService;

public class ObjectContainer {
	
	private final static ObjectRepository REPOSITORY = new ObjectRepository();
	
	private final static UserService USERSERVICE = UserService.getInstance(REPOSITORY.getAccount());
	private final static UserController USERCONTROLLER = new UserController(USERSERVICE);
	
	public UserController getControllerInstance() {
		return USERCONTROLLER;
	}
	
}
