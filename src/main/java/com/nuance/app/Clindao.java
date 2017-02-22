
package com.nuance.app;
import java.util.List;

import org.springframework.dao.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import com.nuance.app.Clintaccess;

public class Clindao {

	public List<Clintaccess> list = new ArrayList<Clintaccess>();

	public List<Clintaccess> feedAllDataByClintegrity() throws SQLException, DataAccessException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clintegrity", "root", "system");
			Statement s = con.createStatement();

			ResultSet rs = s.executeQuery("select * from patient");

			while (rs.next()) {

				Clintaccess c = new Clintaccess();

				c.setSID(rs.getInt(1));
				c.setSSN(rs.getString(2));
				c.setENTERPRISE_NO(rs.getString(3));
				c.setBIRTHDATE(rs.getDate(4));
				c.setADDR_LINE_1(rs.getString(5));
				c.setADDR_LINE_2(rs.getString(6));
				c.setCITY(rs.getString(7));
				c.setPROVINCE_STATE_SID(rs.getInt(8));
				c.setZIP_CODE(rs.getString(9));
				c.setPHONE_NUMBER(rs.getString(10));
				c.setEMAIL(rs.getString(11));
				c.setNEXT_OF_KIN(rs.getString(12));
				c.setPERMANENT(rs.getString(13));
				c.setBATCH_SID(rs.getInt(14));
				c.setSEX(rs.getString(15));
				c.setORIG_SYSTEM(rs.getInt(16));
				c.setORIG_USER(rs.getString(17));
				c.setORIG_MODE(rs.getString(18));
				c.setCREATE_DATE(rs.getDate(19));
				c.setLAST_MODIFIED_DATE(rs.getDate(20));
				c.setMARITAL_STATUS(rs.getString(21));
				c.setCONFIDENTIALITY_LEVEL(rs.getInt(22));
				c.setLAST_NAME(rs.getString(23));
				c.setFIRST_NAME(rs.getString(24));
				c.setMIDDLE_INITIAL(rs.getString(25));
				c.setCOUNTRY(rs.getString(26));
				c.setPRE_MERGE_SSN(rs.getString(27));
				c.setPRE_MERGE_ENTERPRISE_NO(rs.getString(28));
				c.setPREVIOUS_SSN(rs.getString(29));

				list.add(c);

			}
			/* con.close(); */
		} catch (Exception e) {
			System.out.println(e);

		}
		return list;

	}
}