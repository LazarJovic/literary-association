package goveed20.LiteraryAssociationApplication.services;

import goveed20.LiteraryAssociationApplication.dtos.PlagiarismResultDTO;
import goveed20.LiteraryAssociationApplication.utils.PlagiatorClient;
import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class PlagiarismService {

    @Autowired
    private PlagiatorClient plagiatorClient;

    public PlagiarismResultDTO uploadPaper(String title, String path, Boolean isNew) throws IOException {
        MultipartFile file = new MockMultipartFile(title, title + ".pdf", "text/plain",
                IOUtils.toByteArray(new FileInputStream(path)));

        if (isNew) {
            return plagiatorClient.uploadNewPaper(file);
        }

        return plagiatorClient.uploadExistingPaper(file);
    }

    public void deletePaper(Long id) {
        try {
            plagiatorClient.deletePaper(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
