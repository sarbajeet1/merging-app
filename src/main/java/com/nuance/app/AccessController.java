package com.nuance.app;

import java.sql.SQLException;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.nuance.app.Clintaccess;
import com.nuance.app.Enscripaccess;


public class AccessController {

	public static void main(String args[]) throws DataAccessException, SQLException, ClassNotFoundException {

	
		Clindao clindao = new Clindao();
		Onedao onedao = new Onedao();
		Enscripdao enscription = new Enscripdao();
	
		List<Clintaccess> clinList = clindao.feedAllDataByClintegrity();
		List<Enscripaccess> enlist = enscription.feedAllDataByEnscription();

		onedao.feedalldatabyClintegrityinOnehc(clinList);
		onedao.feedalldatabyEnscriptioninOnehc(enlist);
		

	}

}