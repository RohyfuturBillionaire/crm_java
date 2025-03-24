package site.easy.to.build.crm.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Depense;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.service.BudgetService;
import site.easy.to.build.crm.service.depense.DepenseService;
import site.easy.to.build.crm.service.lead.LeadService;

import java.util.List;

@RestController
@RequestMapping("api/leads")
public class LeadRestController {

    @Autowired
    private LeadService leadService;

   
    @GetMapping
    public List<Lead> findAll(){
        return leadService.findAll();
    }
   

    

}
