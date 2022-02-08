package tech.dock.desafio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_terminal")
@Entity
public class Terminal {
	
	@Id
	@NotNull
	@Column(name="id")
	private Integer logic;

	@NotNull
	@Length(max = 20)
	@Column(name="serial")
	private String serial;
	
	@NotNull
	@Length(max = 20)
	@Column(name="model")
	private String model;

	@Min(value = 0)
	@Column(name="sam")
	private Integer sam;
	
	@Column(name="ptid")
	private Integer ptId;
	
	@Column(name="plat")
	private Integer plat;
	
	@NotNull
	@Length(max = 20)
	@Column(name="version")
	private String version;
	
	@Column(name="mxr")
	private Integer mxr;
	
	@Column(name="mxf")
	private Integer mxf;
	
	@Length(max = 20)
	@Column(name="pverfm")
	private String pverfm;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((logic == null) ? 0 : logic.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Terminal other = (Terminal) obj;
		if (this.getLogic() == null) {
			if (other.getLogic() != null)
				return false;
		} else if (!getLogic().equals(getLogic()))
			return false;
		return true;
	}

	

}