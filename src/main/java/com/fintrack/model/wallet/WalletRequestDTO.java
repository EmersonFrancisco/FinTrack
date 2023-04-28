package com.fintrack.model.wallet;

public class WalletRequestDTO {

	private Integer id;
	private String name;
	private String type;

	public WalletRequestDTO() {
	}

	public WalletRequestDTO(Integer id, String name, String type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "WalletRequestDTO [id=" + id + ", name=" + name + ", type=" + type + "]";
	}

}
