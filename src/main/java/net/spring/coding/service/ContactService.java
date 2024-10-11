package net.spring.coding.service;

import net.spring.coding.model.Employee;
import net.spring.coding.repository.ContactRepository;
import net.spring.coding.sesstion.HibernateSesstion;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ContactService implements ContactRepository<Employee> {

    private static Session session;
    private static Transaction transaction;
    private static HibernateSesstion hibernateSesstion;

    /* private final String READ = "select * from contact ;"; */

    public ContactService() {

        hibernateSesstion = new HibernateSesstion();

    }

    /*      @Override
            public List<Object[]> read() {

                List<Object[]> rows = null;

                try {

                    session = hibernateSesstion.getSesstion();
                    SQLQuery query = session.createSQLQuery(this.READ);

                    rows = query.list(); //store all info database


            } catch (Exception mess) {

                System.out.println("somethings is wrong..."+mess);

            } finally { // last working

                try {

                    if(session != null) {
                        session.close();
                    }

                } catch(Exception mess) {

                    System.out.println("somethings is wrong..."+mess);

                }

                return rows;
            }
        }
    */
    @Override
    public Employee create(Employee object) {

    try {
        session = hibernateSesstion.getSesstion(); /** Create session factory object */
        transaction = session.beginTransaction(); /** Getting transaction object from session object */

        session.save(object); /* this save() method for insert */

        transaction.commit();
    } catch (Exception mess) {

        System.out.println("somethings is wrong..."+mess);

    } finally {

        try {

            if(session != null) {
                session.close();
            }

        } catch(Exception mess) {

            System.out.println("somethings is wrong..."+mess);

        }

    } /* finally */
    return object;
} /* end create */
    @Override
    public List<Employee> reads() {

        List<Employee> employeesList = null;

        try {
            session = hibernateSesstion.getSesstion(); /** Create session factory object */
            transaction = session.beginTransaction(); /** Getting transaction object from session object */
            Query query = session.createQuery("from Employee"); /** อ้างอิงค์จาก Model Class query all table */
            employeesList = query.list(); /* then store to array list */

            } catch (Exception mess) {

            System.out.println("somethings is wrong..."+mess);

        } finally {
            try {
                if(session != null) {

                    session.close();

                }
            } catch(Exception mess) {

                System.out.println("somethings is wrong..."+mess);

            }
        }
        return employeesList;
    }
    @Override
    public Employee readById(Long id) {
        Employee contact = null;
        try {

            session = hibernateSesstion.getSesstion(); /** Create session factory object */
            transaction = session.beginTransaction(); /** Getting transaction object from session object */
            contact = session.get(Employee.class , id); /* method get() for retrieve info */

        } catch (Exception mess) {

            System.out.println("somethings is wrong..."+mess);

        } finally {

            try {
                if(session != null) {

                    session.close();

                }
            } catch(Exception mess) {

                System.out.println("somethings is wrong..."+mess);

            }
        } /* finally */
        return contact;
    } /* end read by id */
    @Override
    public void update(Employee object , Long id) {

        Employee contact;

        try {

            session = hibernateSesstion.getSesstion(); /** Create session factory object */
            transaction = session.beginTransaction(); /** Getting transaction object from session object */

            contact = session.get(Employee.class , id);
            /* add value to Model */
            contact.setName(object.getName());
            contact.setEmail(object.getEmail());
            contact.setPhone(object.getPhone());
            contact.setCity(object.getCity());
            contact.setPosition(object.getPosition());
            /* then get Model into method save() for update */
            session.save(contact); /* method for update */
            System.out.println("update successfully");
            transaction.commit();

        } catch (NullPointerException mess) {

            System.out.println("The value if you need is null "+mess);

        } finally {

            try {

                if(session != null) {
                    session.close();
                }

            } catch(Exception mess) {

                System.out.println("somethings is wrong..."+mess);

            }

        } /* finally */
    } /* end update */
    @Override
    public void delete(Long id) {

        Employee contact;
        try {
            session = hibernateSesstion.getSesstion(); /** Create session factory object */
            transaction = session.beginTransaction(); /** Getting transaction object from session object */

            contact = session.get(Employee.class , id);

            session.delete(contact); /* method for delete */
            System.out.println("delete successfully");
            transaction.commit();

        } catch (NullPointerException mess) {

            System.out.println("The value if you need is null "+mess);

        } finally {

            try {

                if(session != null) {
                    session.close();
                }

            } catch(Exception mess) {

                System.out.println("somethings is wrong..."+mess);

            }

        } /* finally */
    } /* end delete */

}
