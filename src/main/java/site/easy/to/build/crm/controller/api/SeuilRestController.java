package site.easy.to.build.crm.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Depense;
import site.easy.to.build.crm.entity.Seuil;
import site.easy.to.build.crm.service.BudgetService;
import site.easy.to.build.crm.service.depense.DepenseService;
import site.easy.to.build.crm.service.seuil.SeuilService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/seuils")
public class SeuilRestController {

    @Autowired
    SeuilService seuilService;

    @GetMapping("/actual")
    public Seuil getSeuilActuel() {
        return seuilService.getSeuilActuel();
    }

    @PostMapping("/update")
    public void update(double seuil)  
    {   
        Seuil seuil2= new Seuil();
        seuil2.setTaux(new BigDecimal(seuil));
        seuil2.setDateSeuil(LocalDateTime.now());
        System.out.println("new seuil ="+seuil);
        seuilService.addSeuil(seuil2);
    }
}
