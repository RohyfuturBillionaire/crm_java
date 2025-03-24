package site.easy.to.build.crm.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Depense;
import site.easy.to.build.crm.service.BudgetService;
import site.easy.to.build.crm.service.depense.DepenseService;

import java.util.List;

@RestController
@RequestMapping("api/budgets")
public class BudgetRestController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping
    public List<Budget> findAll(){
        return budgetService.findAll();
    }

    @GetMapping("/total")
    public double getTotalBudget() {
        return budgetService.totalbudget();
    }

    @GetMapping("/customers")
    public List<Object[]> findTotalBudgetByCustomer() {
        return budgetService.findTotalBudgetByCustomer();
    }
   

    

}
