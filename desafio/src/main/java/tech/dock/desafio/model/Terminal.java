package tech.dock.desafio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_terminal")
@Entity
public class Terminal {
	
	@Id
	@NotNull
	@Column(name="id")
	private Integer id;

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

}