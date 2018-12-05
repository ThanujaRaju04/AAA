package com.capgemini.takehome.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.takehome.bean.ProductBean;
import com.capgemini.takehome.exception.BSAException;
import com.capgemini.takehome.ui.Client;
import com.capgemini.takehome.utility.JdbcUtility;

public class ProductDAO implements IProductDAO {
	static Logger logger = Logger.getLogger(Client.class);
	
	
	Connection connection = null;
	PreparedStatement statement = null;


	/*
	 * method name - getProductDetails 
	 * -List Author -Capgemini  purpose -  
	 * it creates connection and get product details from database for given product Code.
	 */
	
	@Override
	public List<ProductBean> getDetailsProduct(int productCode)
			throws BSAException {
		PropertyConfigurator.configure("resources/log4j.properties");

		logger.info("log4j configured");
		List<ProductBean> list = new ArrayList<>();

		connection = JdbcUtility.getconnection();
		logger.info("Connection Established Succesfully");
	
		try {
			statement = connection.prepareStatement(QueryConstants.selectQuery);
			statement.setInt(1, productCode);
			ResultSet resultset = statement.executeQuery();
			
			while (resultset.next()) {
				logger.info("In iteration mode");
				int productCode1 = resultset.getInt(1);
				String productName = resultset.getString(2);
				String productCategory = resultset.getString(3);
				String productDescription = resultset.getString(4);
				long productPrice = resultset.getLong(5);
				
				ProductBean product = new ProductBean(productCode1,
						productName, productCategory, productDescription,
						productPrice);
				
				list.add(product);
			
			}logger.info("list stores required data and returns");
		
		} catch (SQLException e) {
			logger.debug("incorrect query");
		}finally {
			try {
				
				statement.close();
			} catch (SQLException e) {
				throw new BSAException("not able to close resultSet");
			}

			JdbcUtility.closeConnection();

		}
		return list;
	}

}
