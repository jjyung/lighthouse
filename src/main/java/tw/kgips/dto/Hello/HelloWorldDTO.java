package tw.kgips.dto.Hello;

import tw.kgips.persistence.entity.HelloWorldEntity;

public class HelloWorldDTO {

	private Long sn;
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

	public static HelloWorldDTO fromEntity(HelloWorldEntity entity) {

		if (entity == null) {
			return null;
		}

		HelloWorldDTO dto = new HelloWorldDTO();

		dto.setSn(entity.getSn());
		dto.setName(entity.getName());

		return dto;
	}
}
