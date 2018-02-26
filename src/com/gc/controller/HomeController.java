package com.gc.controller;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gc.model.Product;

/*
 * author: Antonella Solomon
 *
 */

@Controller
public class HomeController {

	@RequestMapping("/welcome")
	// this is listing all the data from the product class
	public ModelAndView helloWorld() {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		
		SessionFactory sessionFactory = config.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria  crit = session.createCriteria(Product.class);
		ArrayList<Product> prodList = (ArrayList<Product>)crit.list();
		tx.commit();
		session.close();
		
		

		return new ModelAndView("welcome", "pList", prodList);
}
}
