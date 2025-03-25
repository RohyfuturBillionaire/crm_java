package site.easy.to.build.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


import site.easy.to.build.crm.entity.TicketDepense;
import site.easy.to.build.crm.service.ticket.ImportTicket;

import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.depense.DepenseService;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.service.ticket.TicketService;
import site.easy.to.build.crm.service.user.UserService;


@Controller
@RequestMapping("/import")
public class ImportCsvController {

    private DepenseService depenseService;
    public LeadService leadService;
    private TicketService ticketService;
    private UserService userService;
    private CustomerService customerService;
    
    @Autowired
    public ImportCsvController(DepenseService depenseService, TicketService ticketService, UserService userService,
            CustomerService customerService,LeadService leadService) {
        this.depenseService = depenseService;
        this.ticketService = ticketService;
        this.userService = userService;
        this.customerService = customerService;
        this.leadService=leadService;
    }

    @GetMapping("/pageTicketDepense")
    public String importCsv() {
        return "import/import";
    }

    @PostMapping("/importTicketDepense")
    public String importCsv(MultipartFile file,Model model) {
        ImportTicket importTicket = new ImportTicket();
        try {
            List<TicketDepense> ticketDepense=importTicket.importTicket(file, ',', depenseService, ticketService, userService, customerService);
            model.addAttribute("ticketDepense", ticketDepense);
            importTicket.insertCSV(ticketDepense, depenseService, ticketService);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "import/import";
    }


    // @GetMapping("/pageLeadDepense")
    // public String importCsvLead() {
    //     return "eval/importLead";
    // }

    // @PostMapping("/importLeadDepense")
    // public String importCsvLead(MultipartFile file,Model model) {
    //     ImportLead importLead = new ImportLead();
    //     try {
    //         List<LeadDepense> leadDepense=importLead.importLead(file, ',', depenseService, leadService, userService, customerService);
    //         model.addAttribute("leadDepense", leadDepense);
    //         importLead.insertCSV(leadDepense, depenseService, leadService);
    //     } catch (Exception e) {
    //         model.addAttribute("errorMessage", e.getMessage());
    //     }
    //     return "eval/importLead";
    // }
    
    

}
