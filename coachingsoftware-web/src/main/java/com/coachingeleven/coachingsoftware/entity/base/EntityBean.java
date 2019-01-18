package com.coachingeleven.coachingsoftware.entity.base;

import java.util.List;

public interface EntityBean<T> {
	
	public T getEntity();
	public void setEntity(T entity);
	public List<T> getEntities();
	public void setEntities(List<T> entities);

}
