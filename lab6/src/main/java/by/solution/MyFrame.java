package by.solution;



//import javax.swing.*;
//import java.awt.*;
//import java.io.*;
//import java.util.ArrayList;
//
//public class MyFrame extends JFrame{
//    private File file = new File("");
//    private File file1 = new File("");
//    private ArrayList<LightBulb> lightBulbs = new ArrayList<>();
//    private JPanel inputPanel = new JPanel();
//    private JPanel outputPanel = new JPanel();
//    private JPanel buttonsPanel = new JPanel();
//
//    private JTextArea fileTextArea = new JTextArea(10, 4);
//    private JTextArea containerTextArea = new JTextArea(10, 4);
//    private JButton sortUp = new JButton("Sort by price");
//    private JButton findC = new JButton("Find C-manufacturers");
//
//    private JButton sortDown = new JButton("Sort by price/power");
//    private JButton avg = new JButton("Find average");
//
//
//
//    public MyFrame() {
//        super("New Year Gift");
//
//        fileTextArea.setEditable(false);
//        containerTextArea.setEditable(false);
//        inputPanel.setLayout(new GridLayout(6, 2));
//        this.add(inputPanel, BorderLayout.NORTH);
//        outputPanel.setLayout(new GridLayout(1, 2));
//        fileTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        outputPanel.add(fileTextArea);
//        containerTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        outputPanel.add(containerTextArea);
//        this.add(outputPanel, BorderLayout.CENTER);
//
//        buttonsPanel.setLayout(new GridLayout(3, 3));
//        buttonsPanel.add(sortUp);
//        buttonsPanel.add(findC);
//
//        buttonsPanel.add(sortDown);
//        buttonsPanel.add(avg);
//
//        this.add(buttonsPanel, BorderLayout.SOUTH);
//        JMenu fileMenu = new JMenu("File");
//        JMenuItem openItem = new JMenuItem("Open");
//        JMenuItem saveItem = new JMenuItem("Save");
//        fileMenu.add(openItem);
//        fileMenu.add(saveItem);
//        openItem.addActionListener(
//                e -> {
//                    JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
//                    int i = fileChooser.showOpenDialog(this);
//                    if (i == JFileChooser.APPROVE_OPTION) {
//                        file = fileChooser.getSelectedFile();
//                    }
//
//        try(BufferedReader reader = new LineNumberReader(new FileReader(file.getName()))) {
//            String line;
//            while ((line = reader.readLine()) != null){
//                String[] informationParts = line.split(",");
//                if(informationParts.length == 3){
//                    String name = informationParts[0].trim();
//                    double power = Double.parseDouble(informationParts[1]);
//                    int diod = Integer.parseInt(informationParts[2]);
//                    LightBulb b=new LED(name,power,diod);
//                    lightBulbs.add(b);
//                    StringBuilder stringBuilder = new StringBuilder();
//                    for(LightBulb bulb:lightBulbs){
//                        stringBuilder.append(bulb).append("\n");
//                    }
//                    fileTextArea.setText(stringBuilder.toString());
//                }
//                else{
//                    JOptionPane.showMessageDialog(null, "Invalid data in file");
//
//                }
//            }
//        } catch (IOException ex) {
//            JOptionPane.showMessageDialog(null, "Problem with fail(");
//        }
//    }
//        );
//
//        saveItem.addActionListener(
//                e -> {
//                    JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
//                    int i = fileChooser.showOpenDialog(this);
//                    if (i == JFileChooser.APPROVE_OPTION) {
//                        file1 = fileChooser.getSelectedFile();
//                    }
//                    try (FileWriter fileWriter = new FileWriter(file1.getName())) {
//                        fileWriter.write(lightBulbs.toString());
//                    } catch (IOException ex) {
//                        JOptionPane.showMessageDialog(null, "Problem with fail(");
//                        throw new RuntimeException(ex);
//                    }
//                }
//        );
//
//        JMenuBar menuBar = new JMenuBar();
//        setJMenuBar(menuBar);
//        menuBar.add(fileMenu);
//
//
//    }
//}
