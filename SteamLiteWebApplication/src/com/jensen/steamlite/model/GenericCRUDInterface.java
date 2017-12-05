package com.jensen.steamlite.model;

import java.util.List;

public interface GenericCRUDInterface<E> {
	
	public List<E> readList(E x);
	
	public E read(E x);
	
	public E create(E x);
	
	public E update(E x);
	
	public E delete(E x);
}
