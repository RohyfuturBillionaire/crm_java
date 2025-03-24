package site.easy.to.build.crm.service.depense;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.DialectOverride.OverridesAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.OverridesAttribute;
import site.easy.to.build.crm.entity.Depense;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Notification;

import site.easy.to.build.crm.repository.DepenseRepository;
import site.easy.to.build.crm.service.BudgetService;
import site.easy.to.build.crm.service.notification.NotificationService;
import site.easy.to.build.crm.service.notification.NotificationServiceImpl;

@Service
public class DepenseServiceImpl implements DepenseService {
    
    private final DepenseRepository depenseRepository;
   
    @Autowired
    public DepenseServiceImpl(DepenseRepository depenseRepository ){
        this.depenseRepository = depenseRepository;

    }

    @Override
    public double totalDepensesLead() {
        return depenseRepository.totalDepenseLead();
    }

    @Override   
    public double totalDepensesTicket() {
        return depenseRepository.totalDepenseTicket();
    }

    @Override
    public List<Depense> getAllDepenses() {
        return depenseRepository.findAll();
    }

    @Override
    public Optional<Depense> getDepenseById(Integer id) {
        return depenseRepository.findById(id);
    }

    @Override
    public Depense saveDepense(Depense depense) {
        return depenseRepository.save(depense);
    }

    @Override
    public void deleteDepense(Integer id) {
        depenseRepository.deleteById(id);
    }

    @Override
    public double getTotalDepenseByCustomerId(int customerId) {
        return depenseRepository.getTotalDepenseByCustomerId(customerId);
    }
    @Override
    public List<Depense> getDepensesWithTickets() {
        return depenseRepository.findAllWithTickets();
    }

    @Override
    public List<Object[]>  findTotalDepenseByCustomer(){
        return depenseRepository.findTotalDepenseByCustomer();
    }

    @Override
    public List<Object[]> findTotalDepenseLeadsByCustomer(){
        return depenseRepository.findTotalDepenseLeadsByCustomer();
    }

    @Override
        public List<Depense> getDepensesWithLeads(){
            return depenseRepository.findAllWithTickets();
        }

    @Override
    public void updateDepenseEtat(int depenseid, int newEtat) {
        Optional<Depense> depense = depenseRepository.findById(depenseid);
        Depense depense2=depense.get();
        if (depense2 != null) {
            depense2.setEtat(newEtat);;
            depenseRepository.save(depense2);
        }
    }
    // public void createDepense(Lead lead, double newDepense){
    //     Depense depense = new Depense();
    //     depense.setLead(lead);
    //     Notification notif = budgetService.checkBudget(lead.getCustomer().getCustomerId(), newDepense);
    //     if (notif.getMessage()!=null) {
    //         notificationService.save(notif);    
    //     }
    //     depenseRepository.save(depense);
        
    // }
}