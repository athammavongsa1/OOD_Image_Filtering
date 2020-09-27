import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;


import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

/**
 * This class represents the implementation of the GUI view for this project. It's purpose is to
 * initialize and functionalize all components of the GUI and call back to the controller when
 * user input is acquired.
 */
public class ViewImpl extends JFrame implements ViewInterface {
  //Fields for the JFrame are a label, a textfield box, and a button.

  private JLabel imageLabel;
  private JTextField filePath;
  private JTextField dimensionOne;
  private JTextField dimensionTwo;
  private JTextField filePathSave;
  private JTextField fileNameSaveAs;
  private JTextArea batchText;
  private JButton loadButton;
  private JButton applyFeature;
  private JButton saveButton;
  private JButton applyBatch;
  private JButton undoButton;
  //private JButton redoButton;

  private ImageIcon image;
  private JComboBox featureList;
  private JFileChooser fileChooseText;
  private JFileChooser fileChooseSave;

  private String fileName;
  private int batchDimensionOne;
  private int batchDimensionTwo;
  private int count;

  /**
   * This class represents the implementation of the GUI view for this project. It creates the
   * visual interface that the user interacts with.
   * @param titleOfFrame the title of the GUI.
   * @throws FileNotFoundException if file to load and save are not found.
   */
  public ViewImpl(String titleOfFrame) throws FileNotFoundException {
    //Title.
    super(titleOfFrame);

    count = 0;
    setSize(800, 600);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());


    //Combo box initialization.
    String[] features = {"Blur", "Sharpen", "Sepia", "Grayscale", "Dither", "Mosaic",
                         "Checkerboard", "Horizontal Rainbow", "Vertical Rainbow", "Flag France",
                         "Flag Switzerland", "Flag Greece"};

    featureList = new JComboBox(features);

    //Label initialization.
    JLabel chooseFileLabel = new JLabel("Choose file:");
    JLabel fileNameSaveAsLabel = new JLabel("Save As (filename)");
    JLabel saveToLabel = new JLabel("Pick save location:");

    //Textfield initialization.
    filePath = new JTextField(30);
    filePathSave = new JTextField(10);
    fileNameSaveAs = new JTextField(10);
    dimensionOne = new JTextField(5);
    dimensionOne.setEditable(false);
    dimensionTwo = new JTextField(5);
    dimensionTwo.setEditable(false);
    batchText = new JTextArea(5, 10);
    batchText.setLineWrap(true);
    JScrollPane scrollBatch = new JScrollPane(batchText);
    //scrollBatch.setPreferredSize(new Dimension(5,10));
    scrollBatch.createVerticalScrollBar();
    scrollBatch.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    //Panel initialization
    JPanel panelOne = new JPanel();
    JPanel panelTwo = new JPanel();
    JPanel panelThree = new JPanel();
    //panelThree.setLayout(new BoxLayout(panelThree, BoxLayout.Y_AXIS));
    panelThree.setLayout(new FlowLayout());
    panelThree.setMaximumSize(new Dimension(200, 200));
    //panelThree.setLayout(new GridLayout(0,1));
    JPanel panelFour = new JPanel();
    JPanel panelFive = new JPanel();

    this.add(panelOne, BorderLayout.PAGE_START);
    this.add(panelTwo, BorderLayout.LINE_START);
    this.add(panelFive, BorderLayout.CENTER);
    this.add(panelThree, BorderLayout.LINE_END);
    this.add(panelFour, BorderLayout.PAGE_END);
    panelFour.setLayout(new BorderLayout());
    panelFour.setLayout(new BorderLayout(15, 15));

    //Image initialization.
    image = new ImageIcon();
    imageLabel = new JLabel(image);
    JScrollPane scroll = new JScrollPane(imageLabel);
    scroll.createHorizontalScrollBar();
    scroll.createVerticalScrollBar();
    scroll.setPreferredSize(new Dimension(400, 400));
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    //Button initialization.
    applyFeature = new JButton("Apply Feature");
    loadButton = new JButton("Load");
    applyBatch = new JButton("Apply Batch Script");
    JButton clearBatch = new JButton("Clear Script");
    saveButton = new JButton("Save Image");
    undoButton = new JButton("       Undo       ");
    //redoButton = new JButton("       Redo       ");
    saveButton.setActionCommand("Save");
    JButton browseButton = new JButton("Browse");

    browseButton.addActionListener(e -> {
      //Initialize file chooser.
      fileChooseText = new JFileChooser();

      //Set directory
      fileChooseText.setFileSelectionMode(JFileChooser.FILES_ONLY);

      int returnVal = fileChooseText.showOpenDialog(null);

      if (returnVal == JFileChooser.APPROVE_OPTION) {
        filePath.setText(fileChooseText.getSelectedFile().toString());
      }
      System.out.println(JFileChooser.APPROVE_OPTION);
    }
    );

    JButton saveToButton = new JButton("Browse Save Location");

    saveToButton.addActionListener(e -> {
      fileChooseSave = new JFileChooser();
      fileChooseSave.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      int returnValSave = fileChooseSave.showOpenDialog(null);
      if (returnValSave == JFileChooser.APPROVE_OPTION) {
        filePathSave.setText(fileChooseSave.getSelectedFile().toString());
      }
      System.out.println(JFileChooser.APPROVE_OPTION);
    });

    clearBatch.addActionListener(e -> batchText.setText(""));

    panelOne.add(chooseFileLabel);
    panelOne.add(filePath);
    panelOne.add(browseButton);
    panelOne.add(loadButton);

    panelTwo.add(scroll);
    panelFive.add(featureList);
    panelFive.add(applyFeature);
    panelFive.add(undoButton);

    //panelFive.add(redoButton);
    panelFive.add(fileNameSaveAsLabel);
    panelFive.add(fileNameSaveAs);
    panelFive.add(saveToLabel);
    panelFive.add(filePathSave);
    panelFive.add(saveToButton);


    panelFour.add(scrollBatch, BorderLayout.CENTER);
    panelFour.add(applyBatch, BorderLayout.AFTER_LINE_ENDS);
    panelFour.add(clearBatch, BorderLayout.BEFORE_LINE_BEGINS);
    panelFour.add(saveButton, BorderLayout.PAGE_END);

    panelThree.add(dimensionOne);
    panelThree.add(dimensionTwo);

    panelOne.setBackground(Color.CYAN.darker());
    panelTwo.setBackground(Color.CYAN.darker());
    panelFive.setBackground(Color.CYAN.darker());
    panelThree.setBackground(Color.CYAN.darker());
    panelFour.setBackground(Color.CYAN.darker());
    panelFour.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker(), 8));
  }

  @Override
  public void setOutput(BufferedImage s) {
    try {
      image = new ImageIcon(s);
      imageLabel.setIcon(image);

      revalidate();
    } catch (NullPointerException e) {
      incorrectDimensions();
    }
    //pack();
  }

  @Override
  public void addFeatures(Features features) {
    loadButton.addActionListener(e -> {
      features.applyFilters("Load", "GUI");
    });

    saveButton.addActionListener(e -> {
      features.applyFilters("Save Image", "GUI");
    });

    featureList.addActionListener(e -> {
      featureList = (JComboBox) e.getSource();
      if (featureList.getSelectedItem().toString().equals("Mosaic")
              || featureList.getSelectedItem().toString().equals("Checkerboard")
              || featureList.getSelectedItem().toString().equals("Flag Switzerland")) {
        dimensionOne.setEditable(true);
        dimensionTwo.setEditable(false);
        revalidate();
      } else if (featureList.getSelectedItem().toString().equals("Horizontal Rainbow")
              || featureList.getSelectedItem().toString().equals("Vertical Rainbow")
              || featureList.getSelectedItem().toString().equals("Flag France")
              || featureList.getSelectedItem().toString().equals("Flag Greece")) {
        dimensionOne.setEditable(true);
        dimensionTwo.setEditable(true);
        revalidate();
      } else {
        dimensionOne.setEditable(false);
        dimensionTwo.setEditable(false);
        revalidate();
      }


    });
    applyFeature.addActionListener(e1 -> {
      features.applyFilters((String) featureList.getSelectedItem(), "GUI");
      features.addHistory();
    });
    applyBatch.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Scanner input = new Scanner(batchText.getText());
        try {
          while (true) {
            switch (input.next()) {
              case "Load":
                fileName = input.next();
                features.applyFilters("Load", "batch");
                break;
              case "Blur":
                features.applyFilters("Blur", "batch");
                break;
              case "Sharpen":
                features.applyFilters("Sharpen", "batch");
                break;
              case "Sepia":
                features.applyFilters("Sepia", "batch");
                break;
              case "Grayscale":
                features.applyFilters("Grayscale", "batch");
                break;
              case "Dither":
                features.applyFilters("Dither", "batch");
                break;
              case "Mosaic":
                batchDimensionOne = input.nextInt();
                System.out.println(batchDimensionOne);
                features.applyFilters("Mosaic", "batch");
                break;
              case "Checkerboard":
                batchDimensionOne = input.nextInt();
                System.out.println(batchDimensionOne);
                features.applyFilters("Checkerboard", "batch");
                break;
              case "HorizontalRainbow":
                batchDimensionOne = input.nextInt();
                batchDimensionTwo = input.nextInt();
                System.out.println(batchDimensionOne);
                features.applyFilters("Horizontal Rainbow", "batch");
                break;
              case "VerticalRainbow":
                batchDimensionOne = input.nextInt();
                batchDimensionTwo = input.nextInt();
                System.out.println(batchDimensionOne);
                features.applyFilters("Vertical Rainbow", "batch");
                break;
              case "FlagFrance":
                batchDimensionOne = input.nextInt();
                batchDimensionTwo = input.nextInt();
                System.out.println(batchDimensionOne);
                features.applyFilters("Flag France", "batch");
                break;
              case "FlagSwitzerland":
                batchDimensionOne = input.nextInt();
                batchDimensionTwo = input.nextInt();
                System.out.println(batchDimensionOne);
                features.applyFilters("Flag Switzerland", "batch");
                break;
              case "FlagGreece":
                batchDimensionOne = input.nextInt();
                batchDimensionTwo = input.nextInt();
                System.out.println(batchDimensionOne);
                features.applyFilters("Flag Greece", "batch");
                break;
              default:
                batchText.append("Incorrect commands. Clear script to run another one.");
                return;
            }
          }
        } catch (NoSuchElementException j) {
          batchText.append("\nBatch Complete! Clear script to run another one");
          return;
        }
      }
    });

    undoButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        count += 1;
        features.undoOperation(count);

      }
    });
  }

  @Override
  public int getDimensionOne() {
    //throw number format exception here and call to the error method.
    try {
      return Integer.parseInt(dimensionOne.getText());
    } catch (NumberFormatException e) {
      return 0;
    }
  }

  @Override
  public int getDimensionTwo() {
    try {
      return Integer.parseInt(dimensionTwo.getText());
    } catch (NumberFormatException e) {
      return 0;
    }
  }

  @Override
  public String getFileInputString() {
    return filePath.getText();
  }


  @Override
  public String getFilePathSave() {
    return filePathSave.getText();
  }

  @Override
  public String getFileNameSaveAs() {
    return fileNameSaveAs.getText();
  }

  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public String getFileName() {
    return fileName;
  }

  @Override
  public int getBatchDimensionOne() {
    return batchDimensionOne;
  }

  @Override
  public int getBatchDimensionTwo() {
    return batchDimensionTwo;
  }

  @Override
  public void fileNotFound() {
    JOptionPane fileNotFoundError = new JOptionPane();
    JOptionPane.showMessageDialog(fileNotFoundError, "File not specified/found.");
  }

  @Override
  public void incorrectDimensions() {
    JOptionPane incorrectDimensions = new JOptionPane();
    JOptionPane.showMessageDialog(incorrectDimensions,
            "Dimensions are not correct for this feature.");
  }

  @Override
  public void noImageLoaded() {
    JOptionPane noImageLoaded = new JOptionPane();
    JOptionPane.showMessageDialog(noImageLoaded, "No Image Loaded.");
  }
}

