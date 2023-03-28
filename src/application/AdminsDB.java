package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminsDB {
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

public static List<Admins> getAdmins(String s){
	List<Admins> list= new ArrayList<Admins>();
	try {
		String sql="SELECT * FROM "+s;
		Connection con =AdminsDB.Getconnection();
		PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Admins adm= new Admins();
			adm.setId(rs.getInt(1));
			adm.setUsername(rs.getString(2));
			adm.setPassword(rs.getString(3));
			list.add(adm);
		}
		con.close();
	} catch (Exception e) {
		// TODO: handle exception
	}
	return list ;

}	
}