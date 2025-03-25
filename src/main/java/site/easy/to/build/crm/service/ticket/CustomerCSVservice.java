package site.easy.to.build.crm.service.ticket;
    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.nio.charset.StandardCharsets;
    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;
    
    import org.apache.commons.csv.CSVFormat;
    import org.apache.commons.csv.CSVParser;
    import org.apache.commons.csv.CSVRecord;
    import org.springframework.web.multipart.MultipartFile;
    
    import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.CustomerLoginInfo;
import site.easy.to.build.crm.entity.Depense;
    import site.easy.to.build.crm.entity.Ticket;
    import site.easy.to.build.crm.entity.TicketDepense;
    import site.easy.to.build.crm.entity.User;
    import site.easy.to.build.crm.service.customer.CustomerService;
    import site.easy.to.build.crm.service.depense.DepenseService;
    import site.easy.to.build.crm.service.user.UserService;
    
    public class CustomerCSVservice {
    
    
        public List<Customer> importTicket(MultipartFile file,char separator,DepenseService depenseService,
        TicketService ticketService,UserService userService,CustomerService customerService) throws Exception {
    
            List<Customer> ticketDepenseList = new ArrayList<>();
            
    
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
                 @SuppressWarnings("deprecation")
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                         .withFirstRecordAsHeader()
                         .withDelimiter(separator))) {  // Spécifiez le séparateur ici
                    String customer_email="";
                    String customer_name="";
                    Customer customer =null;        
                    CustomerLoginInfo customerLoginInfo=new CustomerLoginInfo();
                    for (CSVRecord csvRecord : csvParser) {
                    // T instance = clazz.getDeclaredConstructor().newInstance();
                    customer_email = csvRecord.get("customer_email").trim();
                    customer_name=csvRecord.get("customer_name").trim();
                    customer = new Customer();
                    
                    customerLoginInfo=new CustomerLoginInfo(customer_name, customer_email, customer_name,true, null);
                    

                }
    
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Erreur lors de la lecture du fichier CSV : " + e.getMessage());
            }
            return ticketDepenseList;
        }
    
    
        public boolean insertCSV(List <TicketDepense> ticketDepenseList,DepenseService depenseService,TicketService ticketService){
            for (TicketDepense ticketDepense : ticketDepenseList) {
                Ticket ticket = ticketDepense.getTicket();
                Depense depense = ticketDepense.getDepense();
                Ticket saveTicket=ticketService.save(ticket);
                ticketDepense.getDepense().setTicket(saveTicket);
                ticketDepense.setTicket(saveTicket); 
                Depense depense2=depenseService.saveDepense(depense);
                ticketDepense.setDepense(depense2);
            }
            return true;
        }
    }
    
