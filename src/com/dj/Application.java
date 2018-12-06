package com.dj;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "application")
public class Application 
{
	@EmbeddedId
	private ApplicationId id;

	public Application() 
	{}

	
	public Application(ApplicationId id) {
		super();
		this.id = id;
	}

	public ApplicationId getId() {
		return id;
	}

	public void setId(ApplicationId id) {
		this.id = id;
	}
	
	
	
	
}
