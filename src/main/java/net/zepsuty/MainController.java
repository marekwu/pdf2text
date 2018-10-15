package net.zepsuty;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class MainController
{
    
    @FXML
    MenuItem mnuOpenSourceFile;
    
    @FXML
    MenuItem mnuSaveDestinationFile;
    
    @FXML
    MenuItem mnuExit;
    
    @FXML
    MenuItem mnuAbout;
    
    @FXML
    TextField txtSourceFile;
    
    @FXML
    TextField txtDestinationFile;
    
    @FXML
    Button btnOpenSourceFile;
    
    @FXML
    Button btnOpenDestinationFile;
    
    @FXML
    Button btnConvertFile;
    
    @FXML
    Button btnExit;
    
    @FXML
    Label labStatus;
    
    @FXML
    private void handleMnuOpenSourceFile(ActionEvent actionEvent)
    {
    }
    
    @FXML
    private void handleMnuSaveDestinationFile(ActionEvent actionEvent)
    {
    }
    
    @FXML
    private void handleExit(ActionEvent actionEvent)
    {
        
        System.exit(0);
        
    }
    
    @FXML
    private void handleMnuAbout(ActionEvent actionEvent)
    {
    }
    
    @FXML
    private void handleOpenSourceFile(ActionEvent actionEvent)
    {
        
        final JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new FileNameExtensionFilter("PDF files", "pdf"));
        int returnVal = fc.showDialog(null, "Open PDF file");
        
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            
            File file = fc.getSelectedFile();
            txtSourceFile.setText(file.getPath());
            
        }
        
    }
    
    @FXML
    private void handleOpenDestinationFile(ActionEvent actionEvent)
    {
        
        final JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new FileNameExtensionFilter("TXT files", "txt"));
        int returnVal = fc.showDialog(null, "Save file as TXT");
        
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            
            File file = fc.getSelectedFile();
            txtDestinationFile.setText(file.getPath());
            
        }
    }
    
    @FXML
    private void handleBtnConvertFile(ActionEvent actionEvent)
    {
        
        labStatus.setText(convertDoc(txtSourceFile.getText(), txtDestinationFile.getText()));
        
    }
    
    private static String convertDoc(String sourceFile, String descFile)
    {
        
        try
        {
            File file = new File(sourceFile);
            String extractedText;
            PDFParser parser = new PDFParser((RandomAccessRead) new RandomAccessFile(file, "r"));
            parser.parse();
            
            COSDocument cosDoc = parser.getDocument();
            PDFTextStripper pdfStripper = new PDFTextStripper();
            PDDocument pdDoc = new PDDocument(cosDoc);
            extractedText = pdfStripper.getText(pdDoc);
            
            PrintWriter pw = new PrintWriter(new FileWriter(descFile));
            pw.write(extractedText);
            pw.close();
            
            return "Conversion finished.";
        }
        catch(IOException e1)
        {
            return "error: " + e1.getMessage();
        }
        
    }
    
}
