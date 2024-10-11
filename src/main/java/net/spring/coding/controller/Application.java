package net.spring.coding.controller;


import net.spring.coding.model.Employee;
import net.spring.coding.repository.ContactRepository;
import net.spring.coding.service.ContactService;

import java.util.List;

public class Application {

    private ContactRepository employeeRepository;

    public Application () {
        this.employeeRepository = new ContactService();
    }

    public void test() {
        ///// READS
        /*List<Employee> rows = employeeRepository.reads();
        for (Employee employee: rows) {
            System.out.println(employee);
        }*/

        ///// CREATE
        /*Employee employee = new Employee();
        employee.setName("Phy Sical");
        employee.setEmail("phy.sical@hotmail.com");
        employee.setPhone("0741277799");
        employee.setCity("Bangkok");
        employee.setPosition("Software Engineer");
        System.out.println(employeeRepository.create(employee));*/

        ///// READ
        /*Employee employee = (Employee) employeeRepository.readById(1L);
        System.out.println(employee);*/


        ///// UPDATE
        /*Employee employee = new Employee();
        employee.setName("Go lang");
        employee.setEmail("go.lang@hotmail.com");
        employee.setPhone("0888777998");
        employee.setCity("Los Angeles");
        employee.setPosition("Data Analyst");
        employeeRepository.update(employee,5l);
        System.out.println(employeeRepository.readById(5l));*/

        ///// DELETE
        /*employeeRepository.delete(5l);*/

    }
    public static void main(String[] args) {
        new Application().test();
    }
}
