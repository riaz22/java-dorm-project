package application;

public class Chambre {
	private String num_chambre ;
	private String bloc ;
	public Chambre(String num_chambre, String bloc) {
		super();
		this.num_chambre = num_chambre;
		this.bloc = bloc;
	}
	public String getNum_chambre() {
		return num_chambre;
	}
	public void setNum_chambre(String num_chambre) {
		this.num_chambre = num_chambre;
	}
	public String getBloc() {
		return bloc;
	}
	public void setBloc(String bloc) {
		this.bloc = bloc;
	}
	

}
