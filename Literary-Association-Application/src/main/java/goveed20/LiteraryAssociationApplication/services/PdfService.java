package goveed20.LiteraryAssociationApplication.services;

import goveed20.LiteraryAssociationApplication.exceptions.BusinessProcessException;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PdfService {
    private static final String writingsFolder = "Literary-Association-Application/src/main/resources/writings/";

    public List<String> pdfToBase64(List<String> paths) {
        return paths.stream().sequential().map(p -> {
            try {
                byte[] fileContent = FileUtils.readFileToByteArray(new File(p));
                return new String(Base64.getMimeEncoder().encode(fileContent), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new BusinessProcessException("Failed to parse pdf");
            }
        }).collect(Collectors.toList());
    }

    public List<String> saveBase64ToPdf(String[] base64Strings) {
        return Arrays.stream(base64Strings).sequential().map(s -> {
            if (!s.contains("data:application/pdf;base64,")) {
                throw new BusinessProcessException("Invalid file type. It should be a PDF file");
            }
            s = s.replace("data:application/pdf;base64,", "");
            byte[] decoded = Base64.getMimeDecoder().decode(s.getBytes(StandardCharsets.UTF_8));
            String title = String.format("pdf-%s", UUID.randomUUID().toString().replace("-", ""));
            String path = String.format("%s%s.pdf", writingsFolder, title);
            try {
                FileUtils.writeByteArrayToFile(new File(path), decoded);
            } catch (IOException e) {
                throw new BusinessProcessException("Failed to save pdf");
            }
            return path;
        }).collect(Collectors.toList());
    }

    @SneakyThrows
    public String getContentText(File file) {
        PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
        parser.parse();
        PDFTextStripper stripper = new PDFTextStripper();
        PDDocument document = parser.getPDDocument();
        String text = stripper.getText(document);
        document.close();
        return text;
    }
}
