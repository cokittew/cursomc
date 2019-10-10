package com.gustavoferreira.cursomc.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento(int code, String descricao) {
		this.cod = code;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return this.cod;
	}
	public String getDescricao() {
		return this.descricao;
	}
	
	public static EstadoPagamento toEnum (Integer code) {
		if(code == null) {
			return null;
		}
		
		for (EstadoPagamento x : EstadoPagamento.values() ) {
			if(code.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código inválido: "+code);
	}
}
