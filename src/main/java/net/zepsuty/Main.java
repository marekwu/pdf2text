package net.zepsuty;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main
{
    
    public static void main(String[] args)
    {
        
        javax.swing.SwingUtilities.invokeLater(Main::createAndShowGUI);
        
    }
    
    private static void createAndShowGUI()
    {
        
        JFrame frame = new JFrame("PDF to TXT Converter");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        JLabel titleLab = new JLabel("PDF to TXT Converter");
        
        JButton btnExit = new JButton("Exit");
        JButton btnConvert = new JButton("Convert");
        JButton btnOpenSourceFile = new JButton("Open PDF from file...");
        JButton btnSaveDetinationFile = new JButton("Save TXT file as...");
        
        JLabel sourceFileLabel = new JLabel("PDF to convert:");
        JLabel destinationFileLabel = new JLabel("File to save:");
        
        JTextField sourceFileTextFild = new JTextField();
        sourceFileTextFild.setColumns(50);
        
        JTextField destinationFileTextFiled = new JTextField();
        destinationFileTextFiled.setColumns(50);
        
        panel.add(titleLab);
        panel.add(sourceFileLabel);
        panel.add(sourceFileTextFild);
        panel.add(btnOpenSourceFile);
        panel.add(destinationFileLabel);
        panel.add(destinationFileTextFiled);
        panel.add(btnSaveDetinationFile);
        panel.add(btnConvert);
        panel.add(btnExit);
        
        btnOpenSourceFile.addActionListener(e -> {
            
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showDialog(null, "Open PDF file");
            
            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
                
                File file = fc.getSelectedFile();
                sourceFileTextFild.setText(file.getPath());
                
            }
            
        });
        
        btnSaveDetinationFile.addActionListener(e -> {
            
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showDialog(null, "Save file as TXT");
            
            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
                
                File file = fc.getSelectedFile();
                destinationFileTextFiled.setText(file.getPath());
                
            }
            
        });
        
        btnConvert.addActionListener(e -> {
            
                                         try
                                         {
                                             PDDocument pdDocument = PDDocument.load(new File(sourceFileTextFild.getText()));
                                             String extractedText = new PDFTextStripper().getText(pdDocument);
                
                                             PrintWriter pw = new PrintWriter(new FileWriter(destinationFileTextFiled.getText()));
                                             pw.write(extractedText);
                                             pw.close();
                                         }
                                         catch(IOException e1)
                                         {
                                             JOptionPane.showMessageDialog(null, e1);
                                         }
            
                                         JOptionPane.showMessageDialog(null, "Conversion finished.");
                                     }
                                    );
        
        btnExit.addActionListener(e ->
                                      System.exit(0)
        
                                 );
        
        frame.add(panel);
        frame.setLocation(500, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        
        frame.setVisible(true);
        
    }
    
}
