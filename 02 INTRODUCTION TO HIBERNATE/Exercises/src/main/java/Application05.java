import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Application05 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
//        List<Object[]> employee= em.createQuery("SELECT e.firstName, e.lastName,d.name,e.salary FROM Employee As e JOIN e.department AS d WHERE d.name='Research and Development' ORDER BY e.salary,e.id ",Object[].class)
////                .getResultList();
////        for (Object[] employee1 : employee) {
////            System.out.printf("%s %s from %s - $%.2f\n",employee1[0],
////                    employee1[1],employee1[2],employee1[3]);
////        }
                List<Employee> employee= em.createQuery("SELECT e FROM Employee As e JOIN e.department WHERE e.department.name='Research and Development' ORDER BY e.salary,e.id ",Employee.class)
               .getResultList();
       for (Employee employee1 : employee) {
           System.out.printf("%s %s from %s - $%.2f\n",employee1.getFirstName(),
                    employee1.getLastName(),employee1.getDepartment().getName(),employee1.getSalary());
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
