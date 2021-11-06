package dev.fringe.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
class MainModel {
	@Id
	private String model
	private String type

	public String toString() {
		return "Car [model=" + model + ", type=" + type + "]";
	}
}
