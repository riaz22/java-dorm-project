package application;

public class Etudiant {
	private int id ;
	private String fname ;
	private String lname ;
	private int num_tel;
	private String num_chambre ;
	public String getNum_chambre() {
		return num_chambre;
	}
	public void setNum_chambre(String num_chambre) {
		this.num_chambre = num_chambre;
	}
	public Etudiant(int id, String fname, String lname, int num_tel, String num_chambre) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.num_tel = num_tel;
		this.num_chambre = num_chambre;
	}
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getNum_tel() {
		return num_tel;
	}
	public void setNum_tel(int num_tel) {
		this.num_tel = num_tel;
	}
	public Etudiant(int id, String fname, String lname, int num_tel) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.num_tel = num_tel;
	}
	public Etudiant(int num_tel , String fname, String lname) {
		super();
		this.num_tel = num_tel;
		this.fname = fname;
		this.lname = lname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	

}
