package net.zepsuty;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.*;
import javafx.stage.Stage;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
    private void handleExit(ActionEvent actionEvent)
    {
        
        System.exit(0);
        
    }
    
    @FXML
    private void handleMnuAbout(ActionEvent actionEvent)
    {
        
        labStatus.setText("Created by Marek Witkowski.");
        
    }
    
    @FXML
    private void handleOpenSourceFile(ActionEvent actionEvent)
    {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Source File");
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("PDF Files", "*.pdf"),
            new ExtensionFilter("All Files", "*.*")
                                                );
        
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null)
        {
            txtSourceFile.setText(selectedFile.getPath());
        }
        
    }
    
    @FXML
    private void handleOpenDestinationFile(ActionEvent actionEvent)
    {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Destination File");
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("Text Files", "*.txt"),
            new ExtensionFilter("All Files", "*.*")
                                                );
        
        File selectedFile = fileChooser.showSaveDialog(null);
        if(selectedFile != null)
        {
            txtDestinationFile.setText(selectedFile.getPath());
        }
        
    }
    
    @FXML
    private void handleBtnConvertFile(ActionEvent actionEvent)
    {
        
        try
        {
            
            convertDoc(txtSourceFile.getText(), txtDestinationFile.getText());
            labStatus.setText("Conversion finished.");
        }
        catch(IOException e1)
        {
            labStatus.setText("Error:".concat(e1.getMessage()));
        }
    }
    
    private static void convertDoc(String sourceFile, String descFile) throws IOException
    {
        
        String extractedText;
        
        File file = new File(sourceFile);
        PDFParser parser = new PDFParser(new RandomAccessFile(file,"r"));
        
        parser.parse();
        
        COSDocument cosDoc = parser.getDocument();
        PDFTextStripper pdfStripper = new PDFTextStripper();
        PDDocument pdDoc = new PDDocument(cosDoc);
        extractedText = pdfStripper.getText(pdDoc);
        
        PrintWriter pw = new PrintWriter(new FileWriter(descFile));
        pw.write(extractedText);
        pw.close();
        
    }
    
}
