package site.easy.to.build.crm.service.profil;

import java.util.List;

import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.ProfilTest;

public interface ProfilTestService {
    public ProfilTest findByProfilId(int id);

    public ProfilTest save(ProfilTest profilTest);

    public void delete(ProfilTest profilTest);

    public List<ProfilTest> findAll();

    public List<ProfilTest> getRecentProfils(int userId, int limit);

    public long countByUserId(int userId);

    public void deleteAllByCustomer(Customer customer);
}
