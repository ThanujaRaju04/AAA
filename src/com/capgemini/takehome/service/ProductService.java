package com.capgemini.takehome.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.takehome.bean.ProductBean;
import com.capgemini.takehome.dao.IProductDAO;
import com.capgemini.takehome.dao.ProductDAO;
import com.capgemini.takehome.exception.BSAException;

public class ProductService implements IProductService {
	IProductDAO dao = new ProductDAO();

	/*
	 * method name - getProductDetails arguments -productCode object return type
	 * -List Author -Capgemini- details from database for given product Code.
	 */
	@Override
	public List<ProductBean> getProductDetails(int productCode)
			throws BSAException {
		return dao.getDetailsProduct(productCode);
	}

	/*
	 * method name - isValidproductCode arguments -productCode object return
	 * type -boolean Author -Capgemini 
	 * method  validates productCode.
	 */
	@Override
	public boolean isValidproductCode(int productCode) {

		String pCode = Integer.toString(productCode);
		Pattern pattern = Pattern.compile("[0-9]{4}");
		Matcher matcher = pattern.matcher(pCode);
		return matcher.matches();

	}

}
