package tw.kgips.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "hello_world")
public class HelloWorldEntity {

	@Id
	@Column(name = "sn")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sn;

	@Column(name = "name")
	private String name;

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
