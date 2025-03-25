package site.easy.to.build.crm.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Depense;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.service.BudgetService;
import site.easy.to.build.crm.service.depense.DepenseService;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.service.ticket.TicketService;

import java.util.List;

@RestController
@RequestMapping("api/tickets")
public class TicketRestController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private DepenseService depenseService;
   
    @GetMapping
    public List<Ticket> findAll(){
        return ticketService.findAll();
    }

    @DeleteMapping("/delete/{id}/{idDepense}")
    public void deleteLead(@PathVariable("id") int id, @PathVariable("idDepense") int idDepense) {
        Ticket ticket = ticketService.findByTicketId(id);
        depenseService.deleteDepense(idDepense);
        ticketService.delete(ticket);
        
    }
   

    

}

