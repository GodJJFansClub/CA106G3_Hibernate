package com.genterator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CookGodGenerator implements IdentifierGenerator{

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		String food_ID = null;
		Connection con =  session.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT FOOD_SEQ.NEXTVAL as nextval FROM DUAL");
			rs.next();
			int nextval = rs.getInt("nextval");
			food_ID = getLetterNumberID(nextval);
		}catch(SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		
		return food_ID;
	}
	
	public String getLetterNumberID(int nextval) {
		return String.format("F%05d", nextval);
	}
	
	
}
