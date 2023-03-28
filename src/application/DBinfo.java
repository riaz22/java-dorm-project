package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class DBinfo {
static Connection con =null ;
	
	public static  Connection Getconnection() {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java2","root","ghostrider");
		//	System.out.print("DataBase Connected");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return con ;
	}
	public static int save (Etudiant emp) {
		int st=0 ;
		int st1=0;
		try {
			String sql="INSERT INTO Etudiant (`Ncin`,`nom`,`prenom`,`num_tel`,`Chambre_num_chambre`)Values(?,?,?,?,?)";
			String sql1="INSERT INTO coord_compte_etud(`username`,`password`,`Etudiant_Ncin`)Values(?,?,?)";
			Connection con=DBinfo.Getconnection();
			PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
			ps.setInt(1, emp.getId());
			ps.setString(2,emp.getFname());
			ps.setString(3,emp.getLname());
			ps.setInt(4,emp.getNum_tel());
			ps.setString(5,emp.getNum_chambre());
			st=ps.executeUpdate();
			PreparedStatement ps1=(PreparedStatement)con.prepareStatement(sql1);
			ps1.setString(1, String.valueOf(emp.getId()));
			ps1.setString(2, String.valueOf(emp.getId()));
			ps1.setInt(3, emp.getId());
			st1=ps1.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return st+st1 ;
	}
	public static int update(Etudiant emp,int i) {
		int st=0 ;
		try {
			String sql="Update `etudiant`SET`nom`=?,`prenom`=?,`num_tel`=?,`Chambre_num_chambre`=? WHERE (Ncin=?) ";
			Connection con=DBinfo.Getconnection();
			PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
			
			ps.setString(1,emp.getFname());
			ps.setString(2, emp.getLname());
			ps.setInt(3, emp.getNum_tel());
			ps.setString(4, emp.getNum_chambre());
			ps.setInt(5, i);
			
			st=ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return st ;
	}
	public static int delete(int id) {
		int st=0 ;
		int st1=0;
		try {
			String sql="DELETE FROM Etudiant WHERE Ncin=? ";
			String sql1="DELETE FROM coord_compte_etud WHERE Etudiant_Ncin=? ";
			Connection con=DBinfo.Getconnection();
			PreparedStatement ps1=(PreparedStatement)con.prepareStatement(sql1);
			PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
			ps1.setInt(1, id);
			ps.setInt(1, id);	
			st1=ps1.executeUpdate();
			st=ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return st+st1 ;
	}
	public static Etudiant getEmployeeId(int id) {
		
	Etudiant emp=new Etudiant();
	try {
		String sql="SELECT * FROM Etudiant WHERE Ncin=?";
		Connection con =DBinfo.Getconnection();
		PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			emp.setId(rs.getInt(1));
			emp.setFname(rs.getString(2));
			emp.setLname(rs.getString(3));
			emp.setNum_tel(rs.getInt(4));
			emp.setNum_chambre(rs.getString(5));
		}
		con.close();
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return emp ;
	}
	public static List<Etudiant> getEmployee(){
		List<Etudiant> list=new ArrayList<Etudiant>();
		try {
			String sql="SELECT * FROM employee";
			Connection con=DBinfo.Getconnection();
			PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Etudiant emp =new Etudiant();
				emp.setId(rs.getInt(1));
				emp.setFname(rs.getString(2));
				emp.setLname(rs.getString(3));
				list.add(emp);
			}
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list ;
	}
	public static int chambreV() {
		int i =0 ;
		try {
			String sql="SELECT count(*) FROM Chambre ";
			Connection con=DBinfo.Getconnection();
			PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				i=Integer.parseInt(rs.getString(1));
			}
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return i;	
	}
}
