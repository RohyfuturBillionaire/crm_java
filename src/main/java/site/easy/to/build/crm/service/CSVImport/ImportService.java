package site.easy.to.build.crm.service.CSVImport;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.CSVImporter;

import java.util.List;

@Service
public class ImportService {

    

    public <T> List<T> importCSV(MultipartFile file, Class<T> clazz) {
        CSVImporter<T> importer = new CSVImporter<>(clazz);
        List<T> data = importer.parseCSV(file);
        return data;
        // if (clazz == Product.class) {
        //     productRepository.saveAll((List<Product>) data);
        // } else if (clazz == Client.class) {
        //     clientRepository.saveAll((List<Client>) data);
        // } else {
        //     throw new IllegalArgumentException("Type non supporté : " + clazz.getSimpleName());
        // }
    }
}

