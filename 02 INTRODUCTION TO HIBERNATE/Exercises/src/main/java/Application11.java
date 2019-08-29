import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Application11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String townName = scanner.nextLine();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Town> town = em.createQuery("FROM Town WHERE name=:name", Town.class)
                .setParameter("name", townName).getResultList();
        if (town.size() != 0) {
            List<Address> addresses = em.createQuery("FROM Address WHERE town_id=:id", Address.class)
                    .setParameter("id", town.get(0).getId()).getResultList();


            for (Address address : addresses) {
                for (Employee employee : address.getEmployees()) {
                   employee.setAddress(null);
                }
                em.remove(address);
            }

            em.remove(town.get(0));
            System.out.printf("%d address in %s deleted\n", addresses.size(), townName);
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
