package com.nuance.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.nuance.app.Enscripaccess;

public class Enscripdao {

	public List<Enscripaccess> list = new ArrayList<Enscripaccess>();

	public List<Enscripaccess> feedAllDataByEnscription() throws SQLException, DataAccessException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/enscription", "root", "system");
			Statement s = con.createStatement();

			ResultSet rs = s.executeQuery("select * from patient");

			while (rs.next()) {
				Enscripaccess e = new Enscripaccess();

				e.setId(rs.getInt(1));
				e.setPATIENT_RSN(rs.getInt(2));
				e.setFACILITY_CODE(rs.getString(3));
				e.setENTERPRISE_CODE(rs.getString(4));
				e.setMEDICALRECORDNUMBER(rs.getString(5));
				e.setBIRTHDAte(rs.getDate(6));
				e.setSSN(rs.getString(7));
				e.setLAST_FOLDER_ID(rs.getString(8));
				e.setLASTIMAGE_ID(rs.getString(9));
				e.setLAST_ORDER_ID(rs.getInt(10));
				e.setPATIENT_MFPI_RSN(rs.getInt(11));
				e.setPATIENT_TYPE(rs.getInt(12));

				list.add(e);

			}
			con.close();
		} catch (Exception exc) {
			System.out.println(exc);

		}
		return list;

	}
}