package com.dj;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "saved_job")
public class SavedJob 
{
	@EmbeddedId
	private SavedJobId id;

	public SavedJob() {
		super();
	}

	
	public SavedJob(SavedJobId id) {
		super();
		this.id = id;
	}

	public SavedJobId getId() {
		return id;
	}

	public void setId(SavedJobId id) {
		this.id = id;
	}

		
}

