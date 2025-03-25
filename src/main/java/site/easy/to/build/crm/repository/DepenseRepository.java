package site.easy.to.build.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Depense;


@Repository
public interface DepenseRepository extends JpaRepository<Depense, Integer> {
  
    @Query("SELECT COALESCE(SUM(d.valeurDepense), 0) " +
       "FROM Depense d " +
       "LEFT JOIN d.lead l " +
       "LEFT JOIN d.ticket t " +
       "LEFT JOIN l.customer c " +
       "LEFT JOIN t.customer c2 " +
       "WHERE c.customerId = :customerId OR c2.customerId = :customerId And d.etat = 1")    
    public double getTotalDepenseByCustomerId(@Param("customerId") int customerId);

    @Query("SELECT d FROM Depense d JOIN FETCH d.ticket WHERE d.etat = 1")
    List<Depense> findAllWithTickets();

    
    @Query("SELECT d FROM Depense d JOIN FETCH d.lead WHERE d.etat = 1")
    List<Depense> findAllWithLeads();

    @Query("SELECT d.ticket.customer.customerId,d.ticket.customer.name,SUM(d.valeurDepense) " +
    "FROM Depense d " +
    "WHERE d.ticket IS NOT NULL " +
    "AND d.etat= 1"+
    "GROUP BY d.ticket.customer.customerId")
    List<Object[]> findTotalDepenseByCustomer();

    
    @Query("SELECT d.lead.customer.customerId,d.lead.customer.name,SUM(d.valeurDepense) " +
    "FROM Depense d " +
    "WHERE d.lead IS NOT NULL " +
    "AND d.etat= 1"+
    "GROUP BY d.lead.customer.customerId")
    List<Object[]> findTotalDepenseLeadsByCustomer();

    @Query("SELECT COALESCE(SUM(d.valeurDepense), 0) FROM Depense d WHERE d.etat = 1 and d.lead IS NULL")
    double totalDepenseTicket();

    @Query("SELECT COALESCE(SUM(d.valeurDepense), 0) FROM Depense d WHERE d.etat = 1 and d.ticket IS NULL")
    double totalDepenseLead();
}