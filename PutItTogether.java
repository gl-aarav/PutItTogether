/*
 * Aarav Goyal
 * 3.31.2025
 * PutItTogether.java
 */ 

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseEvent;

public class PutItTogether
{	
    public static void main(String[] args)
    {
        PutItTogether pit = new PutItTogether();
        pit.run();
    }

    public void run()
    {
        JFrame frame = new JFrame("PutItTogether");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(100, 50);
        frame.setResizable(true);
        PutItTogetherHolder pith = new PutItTogetherHolder();
        frame.getContentPane().add(pith);
        frame.setVisible(true);
    }
}

class PutItTogetherHolder extends JPanel
{	
    public PutItTogetherHolder()
    {
        setBackground(Color.CYAN);

        CardLayout cards = new CardLayout();
        setLayout(cards);

        Information info = new Information();
        FirstPagePanel fpp = new FirstPagePanel(this, cards, info);
        FixedPanelHolder hph = new FixedPanelHolder(info, cards, this);

        add(fpp, "First");
        add(hph, "Home");
    }
}

class FirstPagePanel extends JPanel
{
    private PutItTogetherHolder panelCards;
    private CardLayout cards;
    private Information info;
    private JTextField tfName;

    public FirstPagePanel(PutItTogetherHolder panelCardsIn, CardLayout cardsIn, Information infoIn)
    {
        panelCards = panelCardsIn;
        cards = cardsIn;
        info = infoIn;

        setLayout(null);

        JTextArea textArea = new JTextArea("Introduction message...\nPage descriptions...");
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 10, 760, 200);
        add(scrollPane);

        tfName = new JTextField("Enter your name");
        tfName.setBounds(10, 220, 200, 30);
        add(tfName);

        JCheckBox checkBox = new JCheckBox("I understand the directions");
        checkBox.setBounds(10, 260, 250, 30);
        add(checkBox);

        JButton nextButton = new JButton("Next");
        nextButton.setBounds(350, 720, 100, 30);
        nextButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                info.setName(tfName.getText());
                cards.show(panelCards, "Home");
            }
        });
        add(nextButton);
    }
}

class FixedPanelHolder extends JPanel
{
    private Information info;
    private JButton homeButton;
    private CardLayout cards;
    private PutItTogetherHolder panelCards;

    public FixedPanelHolder(Information infoIn, CardLayout cardsIn, PutItTogetherHolder panelCardsIn)
    {
        info = infoIn;
        cards = cardsIn;
        panelCards = panelCardsIn;

        setLayout(cards);

        HomePanel homePanel = new HomePanel(info);
        BothPictPanel bothPictPanel = new BothPictPanel(cards, panelCards);
        MyPictPanel myPictPanel = new MyPictPanel(cards, panelCards);
        FriendPictPanel friendPictPanel = new FriendPictPanel(cards, panelCards);
        DrawPanel drawPanel = new DrawPanel();

        add(homePanel, "Home");
        add(bothPictPanel, "BothPict");
        add(myPictPanel, "MyPict");
        add(friendPictPanel, "FriendPict");
        add(drawPanel, "Draw");
    }
}

class HomePanel extends JPanel
{
    private Information info;

    public HomePanel(Information infoIn)
    {
        info = infoIn;
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome " + info.getName(), JLabel.CENTER);
        add(welcomeLabel, BorderLayout.NORTH);

        JTextArea instructionsArea = new JTextArea("Directions for this page...\nWhat to expect...");
        add(instructionsArea, BorderLayout.CENTER);

        JPanel radioPanel = new JPanel(new GridLayout(3, 1));
        radioPanel.add(new JLabel("Please select which page you would like to see:"));
        JRadioButton friendInfoButton = new JRadioButton("To see information about a friend and me.");
        JRadioButton drawShapesButton = new JRadioButton("To make some colors and draw some shapes.");
        radioPanel.add(friendInfoButton);
        radioPanel.add(drawShapesButton);
        add(radioPanel, BorderLayout.SOUTH);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }
}

class BothPictPanel extends JPanel implements MouseListener
{
    private CardLayout cards;
    private PutItTogetherHolder panelCards;
    private Image person1Image;
    private Image person2Image;

    public BothPictPanel(CardLayout cardsIn, PutItTogetherHolder panelCardsIn)
    {
        cards = cardsIn;
        panelCards = panelCardsIn;

        setLayout(new BorderLayout());

        JLabel instructionsLabel = new JLabel("Click on a person to see more information about them.");
        add(instructionsLabel, BorderLayout.NORTH);

        JPanel picturePanel = new JPanel(new GridLayout(1, 2));
        try
        {
            person1Image = ImageIO.read(new File("person1.jpg"));
            person2Image = ImageIO.read(new File("person2.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        JLabel person1Label = new JLabel(new ImageIcon(person1Image));
        JLabel person2Label = new JLabel(new ImageIcon(person2Image));
        picturePanel.add(person1Label);
        picturePanel.add(person2Label);

        person1Label.addMouseListener(this);
        person2Label.addMouseListener(this);

        add(picturePanel, BorderLayout.CENTER);
    }

    public void mouseClicked(MouseEvent e)
    {
        cards.show(panelCards, "MyPict");
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}

class MyPictPanel extends JPanel implements ActionListener
{
    private CardLayout cards;
    private PutItTogetherHolder panelCards;
    private Image personImage;

    public MyPictPanel(CardLayout cardsIn, PutItTogetherHolder panelCardsIn)
    {
        cards = cardsIn;
        panelCards = panelCardsIn;

        setLayout(new BorderLayout());

        try
        {
            personImage = ImageIO.read(new File("person1.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        JLabel personLabel = new JLabel(new ImageIcon(personImage));
        add(personLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(5, 1));
        infoPanel.add(new JLabel("Name: Person 1"));
        infoPanel.add(new JLabel("Date of Birth: 01/01/1990"));
        infoPanel.add(new JLabel("Age: 35"));
        infoPanel.add(new JLabel("Hobbies: Reading, Hiking"));
        add(infoPanel, BorderLayout.CENTER);

        JButton otherPersonButton = new JButton("<html><center>See info for<br>the other person</center></html>");
        otherPersonButton.addActionListener(this);
        add(otherPersonButton, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e)
    {
        cards.show(panelCards, "FriendPict");
    }
}

class FriendPictPanel extends JPanel implements ActionListener
{
    private CardLayout cards;
    private PutItTogetherHolder panelCards;
    private Image personImage;

    public FriendPictPanel(CardLayout cardsIn, PutItTogetherHolder panelCardsIn)
    {
        cards = cardsIn;
        panelCards = panelCardsIn;

        setLayout(new BorderLayout());

        try
        {
            personImage = ImageIO.read(new File("person2.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        JLabel personLabel = new JLabel(new ImageIcon(personImage));
        add(personLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(5, 1));
        infoPanel.add(new JLabel("Name: Person 2"));
        infoPanel.add(new JLabel("Date of Birth: 02/02/1992"));
        infoPanel.add(new JLabel("Age: 33"));
        infoPanel.add(new JLabel("Hobbies: Gaming, Cooking"));
        add(infoPanel, BorderLayout.CENTER);

        JButton otherPersonButton = new JButton("<html><center>See info for<br>the other person</center></html>");
        otherPersonButton.addActionListener(this);
        add(otherPersonButton, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e)
    {
        cards.show(panelCards, "MyPict");
    }
}

class DrawPanel extends JPanel
{
    private RightPanel rp;
    private int amtRed, amtGreen, amtBlue;
    private int size;

    public DrawPanel()
    {
        setLayout(new BorderLayout());

        LeftPanel lp = new LeftPanel();
        rp = new RightPanel();

        add(lp, BorderLayout.WEST);
        add(rp, BorderLayout.CENTER);

        amtRed = 255;
        amtGreen = 0;
        amtBlue = 255;
        size = 100;
    }

    public class LeftPanel extends JPanel
    {
        public LeftPanel()
        {
            setLayout(new GridLayout(4, 1));

            JLabel redLabel = new JLabel("Red:");
            JSlider redSlider = new JSlider(0, 255, 255);
            redSlider.addChangeListener(new ChangeListener()
            {
                public void stateChanged(ChangeEvent e)
                {
                    amtRed = redSlider.getValue();
                    rp.repaint();
                }
            });

            JLabel greenLabel = new JLabel("Green:");
            JSlider greenSlider = new JSlider(0, 255, 0);
            greenSlider.addChangeListener(new ChangeListener()
            {
                public void stateChanged(ChangeEvent e)
                {
                    amtGreen = greenSlider.getValue();
                    rp.repaint();
                }
            });

            JLabel blueLabel = new JLabel("Blue:");
            JSlider blueSlider = new JSlider(0, 255, 255);
            blueSlider.addChangeListener(new ChangeListener()
            {
                public void stateChanged(ChangeEvent e)
                {
                    amtBlue = blueSlider.getValue();
                    rp.repaint();
                }
            });

            JLabel sizeLabel = new JLabel("Size:");
            JScrollBar sizeScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 100, 0, 0, 100);
            sizeScrollBar.addAdjustmentListener(new java.awt.event.AdjustmentListener()
            {
                public void adjustmentValueChanged(java.awt.event.AdjustmentEvent e)
                {
                    size = sizeScrollBar.getValue();
                    rp.repaint();
                }
            });

            add(redLabel);
            add(redSlider);
            add(greenLabel);
            add(greenSlider);
            add(blueLabel);
            add(blueSlider);
            add(sizeLabel);
            add(sizeScrollBar);
        }
    }

    public class RightPanel extends JPanel
    {
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(new Color(amtRed, amtGreen, amtBlue));
            g.fillRect((getWidth() - size) / 2, (getHeight() - size) / 2, size, size);
        }
    }
}

class Information
{
    private String name;

    public Information() {}

    public String getName()
    {
        return name;
    }

    public void setName(String nameIn)
    {
        name = nameIn;
    }
}