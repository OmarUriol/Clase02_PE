package pe.edu.cibertec.proyemp.jpa.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import pe.edu.cibertec.proyemp.jpa.domain.Departamento;
import pe.edu.cibertec.proyemp.jpa.domain.Empleado;

public class JpaTest {
	
	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	
	public static void main(String[] args) {
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("persistenceUnit");
		
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		//test.crearEmpleados();
		test.crearEmpleados2();
		tx.commit();
		test.listarEmpleados();
		
		
	}
	
	private void crearEmpleados() {

			Departamento departamento = new Departamento("Java");
			Departamento departamentoNet = new Departamento("NET");
			manager.persist(departamento);
			manager.persist(departamentoNet);

			manager.persist(new Empleado("Bob",departamento));
			manager.persist(new Empleado("Mike",departamento));
			manager.persist(new Empleado("Americo",departamentoNet));
			manager.persist(new Empleado("Wawqicha",departamentoNet));

		
	}
	
	private void crearEmpleados2(){
		Departamento java = new Departamento("Java");
		Empleado emp1 = new Empleado("Bob");
		Empleado emp2 = new Empleado("Mike");
		
		
		java.setEmpleados(Arrays.asList(emp1,emp2));
		manager.persist(java);
	}
	
	private void listarEmpleados() {
		List<Empleado> resultList = manager.
				createQuery("Select a From Empleado a", Empleado.class).getResultList();
		
		System.out.println("nro de empleados:" + resultList.size());
		for (Empleado next : resultList) {
			System.out.println("siguiente empleado: " + next);
		}
	}
}
