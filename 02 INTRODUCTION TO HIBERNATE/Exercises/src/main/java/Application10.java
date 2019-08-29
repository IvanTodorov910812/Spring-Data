import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Application10 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Employee> employees=em.createQuery("FROM Employee WHERE department.name IN('Engineering','Tool Design','Marketing','Information Services') ORDER BY id",Employee.class).getResultList();
        for (Employee employee : employees) {
            em.detach(employee);
            employee.setSalary(new BigDecimal(1.12).multiply(employee.getSalary()));
            em.merge(employee);
            System.out.printf("%s %s ($%.2f)\n",employee.getFirstName(),employee.getLastName(),
                    employee.getSalary());
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
