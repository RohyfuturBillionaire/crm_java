package site.easy.to.build.crm.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import site.easy.to.build.crm.entity.Depense;

import site.easy.to.build.crm.service.depense.DepenseService;

import java.util.List;

@RestController
@RequestMapping("api/depenses")
public class DepenseRestController {

    @Autowired
    private DepenseService depenseService;

    @GetMapping("/ticket")
    public List<Depense> getDepensesWithTickets() {
        return depenseService.getDepensesWithTickets();
    }

    @GetMapping("/Lead")
    public List<Depense> getDepensesWiiLeads()
        {
            return depenseService.getDepensesWithLeads();
        }

    @GetMapping("/leads/total")
    public double getTotalDepenseLead() {
        return depenseService.totalDepensesLead();
    }

    @GetMapping("/tickets/total")
    public double getTotalDepenseTicket() {
        return depenseService.totalDepensesTicket();
    }

    @GetMapping("/totalTicket")
    public List<Object[]> findTotalDepenseByCustomer() {
        return depenseService.findTotalDepenseByCustomer();
    }

    @GetMapping("/totalLead")
    public List<Object[]> findTotalDepenseLeadsByCustomer() {
        return depenseService.findTotalDepenseLeadsByCustomer();
    }

}
