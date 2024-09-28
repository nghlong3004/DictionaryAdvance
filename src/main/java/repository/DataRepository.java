package repository;

import java.util.List;
import java.util.Map;

public interface DataRepository <T>{
	// dictionary
	public Map<?, T> loadWords();
	public void saveWords();
	public void deleteWords();
	// user
	@SuppressWarnings("hiding")
	public <T, I> I load(Class<I> returnType);
	public void save(List<T> datas);
	public void deleteUser();
}
