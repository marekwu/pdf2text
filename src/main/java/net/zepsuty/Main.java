package net.zepsuty;

import javax.swing.*;
import java.io.File;

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
        sourceFileTextFild.setColumns(40);
        
        JTextField destinationFileTextFiled = new JTextField();
        destinationFileTextFiled.setColumns(40);
        
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
            int returnVal = fc.showDialog(null,"Open PDF file");
        
            if(returnVal == JFileChooser.APPROVE_OPTION){
                
                File file = fc.getSelectedFile();
                sourceFileTextFild.setText(file.getPath());
                
            }
        
        });
        
        btnSaveDetinationFile.addActionListener(e -> {
    
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showDialog(null,"Save file as TXT");
    
            if(returnVal == JFileChooser.APPROVE_OPTION){
        
                File file = fc.getSelectedFile();
                destinationFileTextFiled.setText(file.getPath());
        
            }
            
        });
        
        btnConvert.addActionListener(e ->
                                         JOptionPane.showMessageDialog(null, "Conversion finished.")
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
