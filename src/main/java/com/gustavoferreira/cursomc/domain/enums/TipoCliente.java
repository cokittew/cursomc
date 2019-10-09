package com.gustavoferreira.cursomc.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int code, String descricao) {
		this.cod = code;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return this.cod;
	}
	public String getDescricao() {
		return this.descricao;
	}
	
	public static TipoCliente toEnum (Integer code) {
		if(code == null) {
			return null;
		}
		
		for (TipoCliente x : TipoCliente.values() ) {
			if(code.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código inválido: "+code);
	}
}
