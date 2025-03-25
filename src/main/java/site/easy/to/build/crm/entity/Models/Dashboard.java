package site.easy.to.build.crm.entity.Models;

public class Dashboard {

    private double totalTicket;
    private double totalBudget;
    private double totalLead;

    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public void setTotalLead(double totalLead) {
        this.totalLead = totalLead;
    }

    public void setTotalTicket(double totalTicket) {
        this.totalTicket = totalTicket;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public double getTotalLead() {
        return totalLead;
    }

    public double getTotalTicket() {
        return totalTicket;
    }
    
}
