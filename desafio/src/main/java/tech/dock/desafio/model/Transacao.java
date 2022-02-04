package tech.dock.desafio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_transacao")
@Entity
public class Transacao {
	
	@Id
	@Column(name="logic")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer logic;
	
	@Column(name="serial", length = 20)
	private String serial;
	
	@Column(name="sam")
	private Integer sam;
	
	@Column(name="pt_id")
	private Integer ptId;
	
	@Column(name="plat")
	private Integer plat;
	
	@Column(name="pverfm", length = 20)
	private String pverfm;

}