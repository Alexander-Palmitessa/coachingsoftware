package com.coachingeleven.coachingsoftware.entity.base;

public interface CreateBean<T> {
	
	public void create(T entity);
	public String getSuccessClass();
	public boolean getCreateSuccess();

}
