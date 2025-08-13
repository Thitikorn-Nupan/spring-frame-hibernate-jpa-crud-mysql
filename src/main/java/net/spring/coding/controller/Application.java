package net.spring.coding.controller;


import net.spring.coding.model.Employee;
import net.spring.coding.repository.ModelRepository;
import net.spring.coding.service.EmployeeService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.util.List;

public class Application {

    private static final Logger log = Logger.getLogger(Application.class);
    private final ModelRepository<Employee> employeeRepository;

    public Application () {
        this.employeeRepository = new EmployeeService();
        log.log(Level.DEBUG, "Application created");
        display();
    }

    public void display() {
        // By default don't do these the same time
        ///// CREATE
        // create();
        ///// CREATES
        // creates();
        ///// READS
        // reads();
        ///// READ
        // read();
        ///// UPDATE
        // update();
        ///// DELETE
        // delete();
    }

    private void delete() {
        employeeRepository.delete(13L);
    }

    private void update() {
        Employee employee2Edit = new Employee();
        employee2Edit.setName("Go lang");
        employee2Edit.setEmail("go.lang@hotmail.com");
        employee2Edit.setPhone("0888777998");
        employee2Edit.setCity("Los Angeles");
        employee2Edit.setPosition("Data Analyst");
        employeeRepository.update(employee2Edit,13L);
    }

    private void read() {
        Employee row = employeeRepository.readById(14L);
        log.log(Level.DEBUG, "id 14 ? "+row);
    }

    private void reads() {
        List<Employee> rows = employeeRepository.reads();
        for (Employee row: rows) {
            log.log(Level.DEBUG, "row ? "+row);
        }
    }

    private void create() {
        Employee employee = new Employee();
        employee.setName("Phy Sical");
        employee.setEmail("phy3.sical@hotmail.com");
        employee.setPhone("0941277799");
        employee.setCity("Bangkok");
        employee.setPosition("Software Engineer");
        employeeRepository.create(employee);
    }

    private void creates() {
        Employee employee = new Employee();
        employee.setName("Phy Sical");
        employee.setEmail("phy3.sical@hotmail.com");
        employee.setPhone("0941277799");
        employee.setCity("Bangkok");
        employee.setPosition("Software Engineer");
        Employee employee2 = new Employee();
        employee2.setName("Json Sical");
        employee2.setEmail("json.sical@hotmail.com");
        employee2.setPhone("0941277799");
        employee2.setCity("Bangkok");
        employee2.setPosition("Software Engineer");
        List<Employee> employees = List.of(employee, employee2);
        employeeRepository.creates(employees);
    }

    public static void main(String[] args) {
        new Application().display();
    }
}
