package net.spring.coding.service;

import net.spring.coding.model.Employee;
import net.spring.coding.repository.ModelRepository;
import net.spring.coding.sesstion.HibernateSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;


public class EmployeeService implements ModelRepository<Employee> {

    private static final Logger log = Logger.getLogger(EmployeeService.class);
    private static Session session;
    private static Transaction transaction;
    private static HibernateSession hibernateSession;

    public EmployeeService() {
        hibernateSession = new HibernateSession();
    }

    @Override
    public Employee create(Employee object) {
        // have to set session,transaction for holding process query () // fix twice query
        Session session = null;
        Transaction transaction = null;
        try {
            session = hibernateSession.getSession(); // Create session factory object
            transaction = session.beginTransaction(); // Getting transaction object from session object */
            session.save(object); // this save() method for insert
            transaction.commit();
        } catch (Exception mess) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.log(Level.DEBUG, "Failed on save section : " + mess);
        } finally {
            try {
                if (session != null) {
                    session.close();
                    hibernateSession.sessionFactory.close(); // fix twice query
                }
            } catch (Exception mess) {
                log.log(Level.DEBUG, "Failed on close section : " + mess);
            }

        } // finally
        return object;
    } // end create

    @Override
    public List<Employee> creates(List<Employee> object) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = hibernateSession.getSession(); // Create session factory object
            transaction = session.beginTransaction(); // Getting transaction object from session object */
            for (Employee employee : object) {
                session.save(employee);
            }
            transaction.commit();
        } catch (Exception mess) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.log(Level.DEBUG, "Failed on save section : " + mess);
        } finally {
            try {
                if (session != null) {
                    session.close();
                    hibernateSession.sessionFactory.close(); // fix twice query
                }
            } catch (Exception mess) {
                log.log(Level.DEBUG, "Failed on close section : " + mess);
            }

        } // finally
        return object;
    }

    @Override
    public List<Employee> reads() { // bug call twice !!!
        List<Employee> employeesList = null;
        try {
            session = hibernateSession.getSession(); // Create session factory object */
            // transaction = session.beginTransaction(); // Getting transaction object from session object */
            Query query = session.createQuery("from Employee", Employee.class); // อ้างอิงค์จาก Model Class query all table */
            employeesList = query.list(); // then store to array list */
        } catch (Exception mess) {
            log.log(Level.DEBUG, "Failed on select section : " + mess);
        } finally {
            try {
                if (session != null) {
                    session.close();
                    // hibernateSession.sessionFactory.close(); // fix twice query
                }
            } catch (Exception mess) {
                log.log(Level.DEBUG, "Failed on close section : " + mess);
            }
        }
        return employeesList;
    }

    @Override
    public Employee readById(Long id) {
        Employee employee = null;
        try {
            session = hibernateSession.getSession();
            employee = session.get(Employee.class, id); // method get() for retrieve as select where pk
        } catch (Exception mess) {
            log.log(Level.DEBUG, "Failed on select section : " + mess);
        }  finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (Exception mess) {
                log.log(Level.DEBUG, "Failed on close section : " + mess);
            }
        }
        return employee;
    }

    @Override
    public void update(Employee object, Long id) {
        // have to set session,transaction for holding process query () // fix twice query
        Session session = null;
        Transaction transaction = null;
        Employee employee;
        try {
            session = hibernateSession.getSession();
            transaction = session.beginTransaction();
            employee = session.get(Employee.class, id); // method get() for retrieve as select where pk
            // then change values of model
            employee.setName(object.getName());
            employee.setEmail(object.getEmail());
            employee.setPhone(object.getPhone());
            employee.setCity(object.getCity());
            employee.setPosition(object.getPosition());
            // then get Model into method save() for update if pk exists
            session.saveOrUpdate(employee);
            transaction.commit();
        } catch (NullPointerException mess) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.log(Level.DEBUG, "Failed on update section : " + mess);
        } finally {
            try {
                if (session != null) {
                    session.close();
                    hibernateSession.sessionFactory.close(); // fix twice query
                }
            } catch (Exception mess) {
                log.log(Level.DEBUG, "Failed on close section : " + mess);
            }
        }
    }

    @Override
    public void delete(Long id) {
        // have to set session,transaction for holding process query () // fix twice query
        Session session = null;
        Transaction transaction = null;
        Employee employee;
        try {
            session = hibernateSession.getSession();
            transaction = session.beginTransaction();
            employee = session.get(Employee.class, id);
            session.delete(employee); // method for delete
            transaction.commit();
        } catch (NullPointerException mess) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.log(Level.DEBUG, "Failed on delete section : " + mess);
        } finally {
            try {
                if (session != null) {
                    session.close();
                    hibernateSession.sessionFactory.close(); // fix twice query
                }
            } catch (Exception mess) {
                log.log(Level.DEBUG, "Failed on close section : " + mess);
            }
        }
    }

}
