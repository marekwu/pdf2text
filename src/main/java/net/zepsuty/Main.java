package net.zepsuty;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static java.awt.FlowLayout.LEFT;

public class Main
{
    
    public static void main(String[] args)
    {
        
        try
        {
    
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
    
            {
                if("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                }
            }
            
        } catch(Exception e)
        {
        
            JOptionPane.showMessageDialog(null,"Error in LookAndFeel module.");
            
        
        }
        
        
        javax.swing.SwingUtilities.invokeLater(Main::createAndShowGUI);
        
    }
    
    private static void createAndShowGUI()
    {
        
        JFrame frame = new JFrame("PDF to TXT Converter");
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.PAGE_AXIS));
        
        JPanel panelSourceFile = new JPanel();
        panelSourceFile.setLayout(new FlowLayout(LEFT));
        
        JPanel panelDestinationFile = new JPanel();
        panelDestinationFile.setLayout(new FlowLayout(LEFT));
        
        JPanel panelNagitation = new JPanel();
        panelNagitation.setLayout(new FlowLayout(LEFT));
        
        JButton btnConvert = new JButton("Convert");
        btnConvert.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JButton btnExit = new JButton("Exit");
        btnExit.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        JButton btnOpenSourceFile = new JButton("Open PDF from file...");
        btnOpenSourceFile.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        JButton btnSaveDestinationFile = new JButton("Save TXT file as...");
        btnSaveDestinationFile.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        JLabel sourceFileLabel = new JLabel("PDF to convert:");
        JLabel destinationFileLabel = new JLabel("File to save:");
        
        JTextField sourceFileTextFild = new JTextField();
        sourceFileTextFild.setColumns(50);
        
        JTextField destinationFileTextFiled = new JTextField();
        destinationFileTextFiled.setColumns(50);
        
        panelSourceFile.add(sourceFileLabel);
        panelSourceFile.add(sourceFileTextFild);
        panelSourceFile.add(btnOpenSourceFile);
        
        panelDestinationFile.add(destinationFileLabel);
        panelDestinationFile.add(destinationFileTextFiled);
        panelDestinationFile.add(btnSaveDestinationFile);
        
        panelNagitation.add(Box.createHorizontalGlue());
        panelNagitation.add(btnConvert);
        panelNagitation.add(btnExit);
        
        panelMain.add(panelSourceFile);
        panelMain.add(panelDestinationFile);
        panelMain.add(panelNagitation);
        panelMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        btnOpenSourceFile.addActionListener(e -> {
            
            final JFileChooser fc = new JFileChooser();
            fc.addChoosableFileFilter(new FileNameExtensionFilter("PDF files", "pdf"));
            int returnVal = fc.showDialog(null, "Open PDF file");
            
            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
                
                File file = fc.getSelectedFile();
                sourceFileTextFild.setText(file.getPath());
                
            }
            
        });
        
        btnSaveDestinationFile.addActionListener(e -> {
            
            final JFileChooser fc = new JFileChooser();
            fc.addChoosableFileFilter(new FileNameExtensionFilter("TXT files", "txt"));
            int returnVal = fc.showDialog(null, "Save file as TXT");
            
            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
                
                File file = fc.getSelectedFile();
                destinationFileTextFiled.setText(file.getPath());
                
            }
            
        });
        
        btnConvert.addActionListener(e -> convertDoc(sourceFileTextFild.getText(), destinationFileTextFiled.getText()));
        
        btnExit.addActionListener(e -> System.exit(0));
        
        frame.add(panelMain);
        frame.setLocation(500, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        
        frame.setVisible(true);
        
    }
    
    private static void convertDoc(String sourceFile, String descFile)
    {
        
        try
        {
            File file = new File(sourceFile);
            String extractedText;
            PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
            parser.parse();
            
            COSDocument cosDoc = parser.getDocument();
            PDFTextStripper pdfStripper = new PDFTextStripper();
            PDDocument pdDoc = new PDDocument(cosDoc);
            extractedText = pdfStripper.getText(pdDoc);
            
            PrintWriter pw = new PrintWriter(new FileWriter(descFile));
            pw.write(extractedText);
            pw.close();
            
            JOptionPane.showMessageDialog(null, "Conversion finished.");
        }
        catch(IOException e1)
        {
            JOptionPane.showMessageDialog(null, e1);
        }
        
    }
    
}
