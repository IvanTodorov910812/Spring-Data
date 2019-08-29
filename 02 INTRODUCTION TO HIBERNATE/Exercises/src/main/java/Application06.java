import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Application06 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        String name=scanner.nextLine();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Address address= new Address();
        em.persist(address);
        address.setText("Vitoshka 15");
        List<Employee> employee=em.createQuery("FROM Employee WHERE concat(firstName,' ',lastName)=:name",Employee.class).
               setParameter("name",name).getResultList();
       if(employee.size()>0) {
           employee.get(0).setAddress(address);

       }
        em.getTransaction().commit();
            em.close();
            emf.close();
    }
}
