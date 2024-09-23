package repository;

import java.util.List;

public interface Repository <T>{
	public List<T> loadUsers();
	public void saveUsers(List<T> users);
	public void delete();
}
