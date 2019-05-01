package br.datainfo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conecta {
	static final String URL  = "jdbc:postgresql://localhost:5432/bdDataInfo";
	static final String USER = "postgres";
	static final String PASS = "12345";
	
	public static Connection criarConexao() throws ClassNotFoundException, SQLException{
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection(URL,USER,PASS);
		
		if(con != null){
			System.out.print("Conexão Efetuada com sucesso...");
			return con;
		}
		return null;
	}
}
