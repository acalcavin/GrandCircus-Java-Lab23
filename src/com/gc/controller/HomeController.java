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
 * author: Andrew Calabro-Cavin
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

		return new ModelAndView("welcome", "pList", prodList);
	}

	public ArrayList<Product> listAllProducts() throws HibernateException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction(); 
		Criteria crit = session.createCriteria(Product.class);
		ArrayList<Product> prodList = (ArrayList<Product>) crit.list();
		System.out.println("current size prodList: " + prodList.size());

		tx.commit();
		session.close();
		return prodList;
	}
	
	@RequestMapping("additem")
	public String addItemPage(Model model) {

//		model.addAttribute("additemtest", "Pushing stuff to the additem");
		return "additem";
	}

//	@RequestMapping("searchbyproduct")
//	public ModelAndView searchProduct(@RequestParam("product") String prod) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction(); 
//
//		// Criteria is used to create the query
//		Criteria crit = session.createCriteria(Product.class);
//		crit.add(Restrictions.like("code", "%" + prod + "%"));
//		ArrayList<Product> prodList = (ArrayList<Product>) crit.list();
//		tx.commit();
//		session.close();
//
//		return new ModelAndView("welcome", "pList", prodList);
//	}

	@RequestMapping("addproduct")
	public ModelAndView addNewProduct(@RequestParam("name") String name, @RequestParam("description") String desc, @RequestParam("quantity") int quantity,
			@RequestParam("price") double price, Model model) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Session session = null;  
		Transaction tx = null;  
		Product newProduct = new Product();
		  
		try {  
		session = sessionFactory.openSession();
		tx = session.beginTransaction(); 
		newProduct.setName(name);
		newProduct.setDescription(desc);
		newProduct.setQuantity(quantity);
		newProduct.setPrice(price);
		
		session.save(newProduct);
		
		tx.commit();
		
		}catch (Exception ex) {  
			ex.printStackTrace();  
			tx.rollback();
			return new ModelAndView("addprodsuccess", "product", "Error. That item already exists.");
			} 
		
		finally {session.close();}  
		
		return new ModelAndView("addprodsuccess", "product", newProduct);
	}
	
	@RequestMapping("delete")
	public ModelAndView deleteProduct(@RequestParam("id") String name) {
		
		// temp object will store info for the object that we want to delete
		Product temp = new Product();
		temp.setName(name);
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction(); 
		
		session.delete(temp);
		tx.commit();
		session.close();
		
		ArrayList<Product> prodList = listAllProducts();
		return new ModelAndView("welcome", "pList", prodList);
		
	}
	
	@RequestMapping("update") 
	public ModelAndView update(@RequestParam("id") String name, Model model) {
		
//		ArrayList<Product> prodList = listAllProducts();
		
		model.addAttribute("description", "description");
		
		return new ModelAndView("updateproductform", "name", name);
	}
	
	// we are adding in the request param to send it to the form page and add it as a hidden field
	@RequestMapping("updateproduct") 
	public ModelAndView updateForm(@RequestParam("name") String name, @RequestParam("description") String desc, @RequestParam("quantity") int quantity,
			@RequestParam("price") double price) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction(); 

		Product updateProduct = new Product();
		updateProduct.setName(name);
		updateProduct.setDescription(desc);
		updateProduct.setQuantity(quantity);
		updateProduct.setPrice(price);
		
		session.update(updateProduct);
		tx.commit();
		session.close();
		
		// this is going to allow us to see the updates on the welcome page
		ArrayList<Product> prodList = listAllProducts();
		return new ModelAndView("welcome", "pList", prodList);
	}
	
	
}
