package Datos;

import Objetos.Actor;
import Objetos.Director;
import Objetos.Pelicula;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session;

    /**
     * Crea la factoria de sesiones
     */
    public static void buildSessionFactory() {

        Configuration configuration = new Configuration();
        configuration.configure();

        // Se registran las clases que hay que mapear con cada tabla de la base de datos
        configuration.addAnnotatedClass(Actor.class);
        configuration.addAnnotatedClass(Director.class);
        configuration.addAnnotatedClass(Pelicula.class);


        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    /**
     * Abre una nueva sesión
     */
    public static void openSession() {
        session = sessionFactory.openSession();
    }

    /**
     * Devuelve la sesión actual
     *
     * @return
     */
    public static Session getCurrentSession() {

        if ((session == null) || (!session.isOpen()))
            openSession();

        return session;
    }

    /**
     * Cierra Hibernate
     */
    public static void closeSessionFactory() {

        if (session != null)
            session.close();

        if (sessionFactory != null)
            sessionFactory.close();
    }

//        Acceso a bbdd

    public boolean crear_mod(Object objeto) {
        boolean procesoOk;
        if (objeto instanceof Actor || objeto instanceof Director || objeto instanceof Pelicula) {
            Session sesion = HibernateUtil.getCurrentSession();
            sesion.beginTransaction();
            sesion.save(objeto);
            sesion.getTransaction().commit();
            sesion.close();
            procesoOk = true;
        } else {
            procesoOk = false;
        }
        return procesoOk;
    }

    public boolean borrar(int id, int tipo) {
        boolean procesoOk;
        Object objeto = null;
        switch (tipo) {
            case 0:
                System.out.println(Pelicula.class);
                objeto = HibernateUtil.getCurrentSession().get(Pelicula.class, id);
                break;
            case 1:
                System.out.println(Actor.class);
                objeto = HibernateUtil.getCurrentSession().get(Actor.class, id);
                break;
            case 2:
                System.out.println(Director.class);
                objeto = HibernateUtil.getCurrentSession().get(Director.class, id);
                break;
        }
        try (Session sesion = HibernateUtil.getCurrentSession()) {

            sesion.beginTransaction();
            sesion.delete(objeto);
            sesion.getTransaction().commit();
            sesion.close();
            procesoOk = true;

        } finally {
            session.close();
        }
        return procesoOk;
    }
    public Object obtener(int id, int tipo) {

        Object objeto = null;

        switch (tipo) {
            case 0:
                System.out.println(Pelicula.class);
                objeto = HibernateUtil.getCurrentSession().get(Pelicula.class, id);
                break;
            case 1:
                System.out.println(Actor.class);
                objeto = HibernateUtil.getCurrentSession().get(Actor.class, id);
                break;
            case 2:
                System.out.println(Director.class);
                objeto = HibernateUtil.getCurrentSession().get(Director.class, id);
                break;
        }
        return objeto;
    }

    public ArrayList<Object> obtenerTodos(int tipo) {
        Query query = null;
        String stmt = "";
        switch (tipo) {
            case 0:
                stmt = "FROM Pelicula";
                break;
            case 1:
                stmt = "FROM Actor";
                break;
            case 2:
                stmt = "FROM Director";
                break;
        }
        query = HibernateUtil.getCurrentSession().createQuery(stmt);

        return (ArrayList<Object>) query.getResultList();
    }
}
