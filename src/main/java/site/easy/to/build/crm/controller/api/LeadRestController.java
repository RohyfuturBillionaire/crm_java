package site.easy.to.build.crm.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Depense;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.service.BudgetService;
import site.easy.to.build.crm.service.depense.DepenseService;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.util.AuthorizationUtil;

import java.util.List;

@RestController
@RequestMapping("api/leads")
public class LeadRestController {

    @Autowired
    private LeadService leadService;

    @Autowired
    private DepenseService depenseService;
   
    @GetMapping
    public List<Lead> findAll(){
        return leadService.findAll();
    }
    
    
     @DeleteMapping("/delete/{id}/{idDepense}")
    public void deleteLead(@PathVariable("id") int id, @PathVariable("idDepense") int idDepense) {
        Lead lead = leadService.findByLeadId(id);
        depenseService.deleteDepense(idDepense);
        leadService.delete(lead);
        
    }

    
        
        
    


    

}
