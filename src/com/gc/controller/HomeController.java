package com.gc.controller;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gc.model.Product;
import com.gc.util.HibernateUtil;

/*
 * author: Antonella Solomon
 *
 */

@Controller
public class HomeController {

	@RequestMapping("/")
	public String homePage(Model model) {

		model.addAttribute("indexTest", "Pushing stuff to the index");
		return "index";
	}

	@RequestMapping("/welcome")
	// this is listing all the data from the product class
	public ModelAndView helloWorld(Model model) {

		ArrayList<Product> prodList = listAllProducts();
		model.addAttribute("specificItem", prodList.get(2).getDescription());

		return new ModelAndView("welcome", "pList", prodList);
	}

	public ArrayList<Product> listAllProducts() throws HibernateException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction(); // the transaction represents the unit of work or the actual
														// implemention of of our code
		Criteria crit = session.createCriteria(Product.class);
		ArrayList<Product> prodList = (ArrayList<Product>) crit.list();
		System.out.println(prodList.size());

		
		tx.commit();
		session.close();
		return prodList;
	}

	@RequestMapping("searchbyproduct")
	public ModelAndView searchProduct(@RequestParam("product") String prod) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction(); // the transaction represents the unit of work or the actual
														// implemention of of our code

		// Criteria is used to create the query
		Criteria crit = session.createCriteria(Product.class);
		crit.add(Restrictions.like("code", "%" + prod + "%"));
		ArrayList<Product> prodList = (ArrayList<Product>) crit.list();
		tx.commit();
		session.close();

		return new ModelAndView("welcome", "pList", prodList);
	}

	@RequestMapping("addproduct")
	public ModelAndView addNewProduct(@RequestParam("code") String code, @RequestParam("description") String desc,
			@RequestParam("listprice") double price) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction(); // the transaction represents the unit of work or the actual
														// implemention of of our code

		Product newProduct = new Product();
		newProduct.setCode(code);
		newProduct.setDescription(desc);
		newProduct.setListPrice(price);
		
		session.save(newProduct);
		tx.commit();
		session.close();
		
		return new ModelAndView("addprodsuccess", "product", newProduct);
	}
	
	@RequestMapping("delete")
	public ModelAndView deleteProduct(@RequestParam("id") int id) {
		
		// temp object will store info for the object that we want to delete
		Product temp = new Product();
		temp.setProductID(id);
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction(); // the transaction represents the unit of work or the actual
														// implemention of of our code
		
		session.delete(temp);
		tx.commit();
		session.close();
		
		ArrayList<Product> prodList = listAllProducts();
		return new ModelAndView("welcome", "pList", prodList);
		
	}
	
	@RequestMapping("update") 
	public ModelAndView update(@RequestParam("id") int id) {
		return new ModelAndView("updateproductform", "productID", id);
	}
	
	// we are adding in the request param to send it to the form page and add it as a hidden field
	@RequestMapping("updateproduct") 
	public ModelAndView updateForm(@RequestParam("id") int id,@RequestParam("code") String code, @RequestParam("description") String desc,
			@RequestParam("listprice") double price) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction(); // the transaction represents the unit of work or the actual
														// implemention of of our code

		Product updateProduct = new Product();
		updateProduct.setProductID(id);
		updateProduct.setCode(code);
		updateProduct.setDescription(desc);
		updateProduct.setListPrice(price);
		
		session.update(updateProduct);
		tx.commit();
		session.close();
		// this is going to allow us to see the updates on the welcome page
		ArrayList<Product> prodList = listAllProducts();
		return new ModelAndView("welcome", "pList", prodList);
	}
	
	
}
