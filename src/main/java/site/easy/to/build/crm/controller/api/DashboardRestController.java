package site.easy.to.build.crm.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Depense;
import site.easy.to.build.crm.entity.Models.Dashboard;
import site.easy.to.build.crm.service.BudgetService;
import site.easy.to.build.crm.service.depense.DepenseService;

import java.util.List;

@RestController
@RequestMapping("api/dashboard")
public class DashboardRestController {
    @Autowired
    private BudgetService budgetService;

    @Autowired
    private DepenseService depenseService;

    @GetMapping("/summary")
    public Dashboard getSummary() {
        Dashboard dash= new Dashboard();
        
        dash.setTotalBudget(budgetService.totalbudget());
        dash.setTotalLead(depenseService.totalDepensesLead());
        dash.setTotalTicket(depenseService.totalDepensesTicket());
        
        return dash;
    }
}
