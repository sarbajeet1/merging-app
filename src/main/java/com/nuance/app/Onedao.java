package com.nuance.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import java.sql.*;
import java.time.LocalDateTime;

import com.nuance.app.Oneaccess;

public class Onedao {


	
	



	
	
	//CLINTEGRITY
	
	public String feedalldatabyClintegrityinOnehc(List<Clintaccess> clinList)
			throws SQLException, DataAccessException, ClassNotFoundException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onehc", "root", "system");
		Class.forName("com.mysql.jdbc.Driver");
		Statement s = con.createStatement();
		String SSN;
		String countryname;
		boolean isPatientInOnehc = false;
		LocalDateTime defaultdate;
		boolean isValueInOnehcNull = true;
		int state;

		for (Clintaccess clrecord : clinList) {
			try {

				String bdstring = "";
				if (clrecord.getBIRTHDATE() != null) {
					bdstring = "'" + clrecord.getBIRTHDATE() + "'";
				} else {
					bdstring = "NULL";
				}

				SSN = clrecord.getSSN();

				isPatientInOnehc = ifPatientinOnehc(SSN);

				countryname = clrecord.getCOUNTRY();
				int id =clrecord.getPROVINCE_STATE_SID();
				
				state= clrecord.getPROVINCE_STATE_SID();
				
				int newidforcountry = fetchCountryNamefromClintegrityToOneHC(countryname);//changing name to id in country(onehc)
				String statename = changeStateIdtoNameInClintegrity(state);//changing id to name in province state(clintegrity)
				int newidforstate= fetchprovincestateNamefromClintegrityToOneHC(statename);//changing name to id in province state(onehc) 
				
				defaultdate = changeNullDate();

				if (!isPatientInOnehc) {
					String secondname= clrecord.getMIDDLE_INITIAL() != null ? clrecord.getMIDDLE_INITIAL() : "";
					String lastname = clrecord.getLAST_NAME() != null ? clrecord.getLAST_NAME() : "";
					String address1= clrecord.getADDR_LINE_1() != null ? clrecord.getADDR_LINE_1() : "";
					String address2= clrecord.getADDR_LINE_2() != null ? clrecord.getADDR_LINE_2() : "";
					String city= clrecord.getCITY() != null ? clrecord.getCITY() : "";
					String zip= clrecord.getZIP_CODE() != null ? clrecord.getZIP_CODE() : "";
					String gender= clrecord.getSEX() != null ? clrecord.getSEX() : "";
					String maritialstatus= clrecord.getMARITAL_STATUS() != null ? clrecord.getMARITAL_STATUS() : "";
					String email = clrecord.getEMAIL() != null ? clrecord.getEMAIL() : "";
					
					String phone =  clrecord.getPHONE_NUMBER() != null ?  clrecord.getPHONE_NUMBER() : ""; 
					String ssn = clrecord.getSSN() != null ? clrecord.getSSN() : "";
					
				/*	//for change in zero to null in int confidential >>>
					Integer confidentialityisnull = null;
					int change = confidentialityisnull.intValue();
					int confidential = clrecord.getCONFIDENTIALITY_LEVEL() ;
					int confidentiality = clrecord.getCONFIDENTIALITY_LEVEL() != 0 ? confidential : change;*/
					
					String sql = "insert into patient (patient_first_name,patient_middle_name,patient_last_name,birth_date,ssn,email,patient_address_1,patient_address_2,patient_city,patient_zip,province_state_id,country_id,phone_number,gender,marital_status,confidential_level,patient_type_id,created_by,created_date,updated_by,updated_date) values ('"
							+ clrecord.getFIRST_NAME() + "','" + secondname+ "','"
							+lastname + "'," + bdstring + ",'" + ssn + "','"
							+ email  + "','" + address1 + "','"
							+ address2+ "','" + city + "','" + zip
							+ "'," + newidforstate + "," + newidforcountry + ",'" + phone
							+ "','" + gender+ "','" + maritialstatus+ "',"
							+ clrecord.getCONFIDENTIALITY_LEVEL()  + "," + null + ",'admin','" + defaultdate + "', 'admin','" + defaultdate + "')";
					

					int i = s.executeUpdate(sql);

					System.out.println("sql:clintaccess..." + sql);

				}
				
				
				
				
				
			

			} catch (Exception e) {

				System.out.println(e);

			} finally {

			}

		}
		con.close();
		String i = "records inserted";

		return i;
	}// end of method
	// ****************************************************************************************************************************
	// ****************************************************************************************************************************
	
	
	
	
	
	public boolean ifPatientinOnehc(String SSN) throws SQLException {

		boolean patientpresent = false;
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onehc", "root", "system");
			ResultSet resultSet = null;
			Statement s = con.createStatement();
			String sql = "Select count(*) from patient where ssn ='" + SSN + "'";
			resultSet = s.executeQuery(sql);

			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			if (count > 0) {
				patientpresent = true;
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return patientpresent;

	}// End of method
	
	// ****************************************************************************************************************************
	// ****************************************************************************************************************************
	
	
	
	
	
	
	public LocalDateTime changeNullDate() {
		LocalDateTime realdate = LocalDateTime.now();
		return realdate;

	}//End of method
	
	// ****************************************************************************************************************************
	// ****************************************************************************************************************************
	
	
	
	
	
	public int fetchCountryNamefromClintegrityToOneHC(String countryvariable) throws SQLException {

		int idforcountry = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onehc", "root", "system");
			ResultSet resultSet = null;
			Statement s = con.createStatement();
			String sql = "Select ID from country where Name='United States of America'";
			resultSet = s.executeQuery(sql);

			while (resultSet.next()) {
				idforcountry = resultSet.getInt(1);

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return idforcountry;

	}// end of method

	// ****************************************************************************************************************************
	// ****************************************************************************************************************************

	
	//ENSCRIPTION
	
	
	public String feedalldatabyEnscriptioninOnehc(List<Enscripaccess> enlist)
			throws SQLException, DataAccessException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onehc", "root", "system");

		Statement s = con.createStatement();
		String SSN;
		boolean isPatientInOnehc = false;
		LocalDateTime newdate;
		for (Enscripaccess enrecord : enlist) {
			try {
				SSN = enrecord.getSSN();

				isPatientInOnehc = ifPatientinOnehc(SSN);

				String bdStr = "";

				if (enrecord.getBIRTHDAte() != null) {
					bdStr = "'" + enrecord.getBIRTHDAte() + "'";
				} else {
					bdStr = "NULL";
				}
				
				newdate = changeNullDate();

				if (isPatientInOnehc == false) {
					
					String firstname= "";
					String secondname=  "";
					String lastname = "";
					String address1=  "";
					String address2=  "";
					String city=  "";
					String zip=  "";
					String gender=  "";
					String maritialstatus=  "";
					String email =  "";
					String phone = "";
					
				
					
					String ssn = enrecord.getSSN() != null ? enrecord.getSSN() : "";
					Integer confidential = 0;
					
					
					

					String sql = "insert into patient (patient_first_name,patient_middle_name,patient_last_name,birth_date,ssn,email,patient_address_1,patient_address_2,patient_city,patient_zip,province_state_id,country_id,phone_number,gender,marital_status,confidential_level,patient_type_id,created_by,created_date,updated_by,updated_date) values ('"
							+ firstname + "','" + secondname + "','" + lastname + "'," + bdStr + ",'" + ssn+ "','"
							+ email + "','" + address1 + "','" + address2 + "','" + city + "','" + zip + "'," + null + ","
							+ null + ",'" + phone + "','" + gender + "','" + maritialstatus + "'," + confidential + ","
							+ enrecord.getPATIENT_TYPE() +  ",'admin','"  + newdate +  "', 'admin','" + newdate + "')";

					System.out.println("sql:enscripaccess..." + sql);

					s.executeUpdate(sql);

				}
				
				

			} catch (Exception e) {

				System.out.println(e);

			} finally {

			}
		}
		String i = "records inserted";

		return i;

	}// End of method
	
	// ****************************************************************************************************************************
	// ****************************************************************************************************************************
	
	
	
	
	
	
public String changeStateIdtoNameInClintegrity(int id) throws SQLException{//changing province state id to province state name in clintegrity
	
	
	String nameforstate = null ;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clintegrity", "root", "system");
		ResultSet resultSet = null;
		Statement s = con.createStatement();
		String sql = "Select province_state_name from province_state where id="+id+"";
		resultSet = s.executeQuery(sql);

		while (resultSet.next()) {
			nameforstate = resultSet.getString(1);

		}
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}

	return nameforstate;
	
}//End of method


// ****************************************************************************************************************************
// ****************************************************************************************************************************





	
public int fetchprovincestateNamefromClintegrityToOneHC(String statevariable) throws SQLException {//changing province state name to province state id in clintegrity.

	int idforstate = 0;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onehc", "root", "system");
		ResultSet resultSet = null;
		Statement s = con.createStatement();
		String sql = "Select ID from province_state where Name='"+statevariable+"'";
		resultSet = s.executeQuery(sql);

		while (resultSet.next()) {
			idforstate = resultSet.getInt(1);

		}
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}

	return idforstate;

}//End of method

	
	
	
}



//End of program

// ****************************************************************************************************************************
// ****************************************************************************************************************************