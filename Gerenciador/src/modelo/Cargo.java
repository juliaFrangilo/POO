package modelo;


public enum Cargo {
	COMUM("Comum"),
	COORDENADOR("Coordenador"),
	GERENTE("Gerente");

	private String descricao;

	Cargo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}


