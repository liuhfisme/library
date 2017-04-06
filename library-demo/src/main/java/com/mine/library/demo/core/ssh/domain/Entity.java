package com.mine.library.demo.core.ssh.domain;

import java.io.Serializable;

public abstract class Entity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8685110128927327204L;
	private Integer id;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof Entity)) {
			return false;
		}
		if ((this.id == null) || (((Entity) o).getId() == null)) {
			return false;
		}
		return this.id.equals(((Entity) o).getId());
	}

	public int hashCode() {
		return this.id == null ? System.identityHashCode(this) : this.id
				.hashCode();
	}
}