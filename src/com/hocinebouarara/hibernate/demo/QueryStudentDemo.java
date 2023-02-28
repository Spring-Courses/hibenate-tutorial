package com.hocinebouarara.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hocinebouarara.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory 
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		// create session
		Session session =factory.getCurrentSession();
		
		try {
						
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			displayStudents(theStudents);
			
			// query students: lastName: 'bouarara';
			theStudents = session.createQuery("from Student s where s.lastName='bouarara'").getResultList();
			
			// display students
			System.out.println("Students who have lastName of bouarara");
			
			displayStudents(theStudents);
		
			
			// commit transaction
			session.getTransaction().commit();

		
			System.out.println("Done!");
			
		} finally {
			
			factory.close();
			
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

}
