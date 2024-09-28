package repository;

import java.util.List;

public interface DataRepository <T>{
	@SuppressWarnings("hiding")
	public <T, I> I load(Class<I> returnType);
	public void save(List<T> datas);
	public void delete();
}
