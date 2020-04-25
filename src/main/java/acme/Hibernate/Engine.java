package acme.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Engine {

	public static void main(String[] args) {

		Engine e = new Engine();
		Foo foo = new Foo();
		e.create(foo);

	}

	SessionFactory getConnection() {
		Configuration cfg = new Configuration();
		cfg.configure();
		cfg.addAnnotatedClass(Foo.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties())
				.build();

		SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

	void create(Foo foo) {
		Session session = getConnection().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(foo);
		transaction.commit();
		session.close();
	}
	
	void create(String fn, String ln, String c) {
		Foo foo = new Foo();
		foo.setFirstName(fn);
		foo.setLastName(ln);
		foo.setCountry(c);
		Session session = getConnection().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(foo);
		transaction.commit();
		session.close();
	}
	
	void create(int id, String fn, String ln, String c) {
		Foo foo = new Foo();
		foo.setId(id);
		foo.setFirstName(fn);
		foo.setLastName(ln);
		foo.setCountry(c);
		Session session = getConnection().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(foo);
		transaction.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	void read() {
		Session session = getConnection().openSession();
		List<Foo> fooList = session.createQuery("from Foo").list();
		for (Foo foo : fooList) {
			System.out.printf
			("%d %s %s %s\n", foo.getId(), foo.getFirstName(), foo.getLastName(), foo.getCountry());
		}
	}
	
	void read(int id) {
		Session session = getConnection().openSession();
		Foo foo = session.get(Foo.class, id);
		System.out.printf
			("%d %s %s %s\n", foo.getId(), foo.getFirstName(), foo.getLastName(), foo.getCountry());
		session.close();
	}

	void update(Foo foo) {
		Session session = getConnection().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(foo);
		transaction.commit();
		session.close();
	}
	
	void delete(int id) {
		Session session = getConnection().openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(id);
		transaction.commit();
		session.close();
	}

}
