/* 
 * Aarav Goyal
 * PutItTogether.java
 * 4/8/2025
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PutItTogether 
{
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("PutItTogether");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(0, 50);
        frame.setResizable(true);
        frame.getContentPane().add(new PutItTogetherHolder());
        frame.setVisible(true);
    }
}

class PutItTogetherHolder extends JPanel 
{
    public PutItTogetherHolder() 
    {
        setLayout(new CardLayout());

        JTextField tfName = new JTextField();

        FirstPagePanel first = new FirstPagePanel(this, tfName);
        HomePanelHolder homeHolder = new HomePanelHolder(this, tfName);

        add(first, "First");
        add(homeHolder, "HomeHolder");
    }
}

class FirstPagePanel extends JPanel 
{
    public FirstPagePanel(JPanel parent, JTextField tfName) 
    {
        setLayout(null);

        JTextArea taIntro = new JTextArea("Welcome!\n\n- This program shows multiple pages using CardLayout.\n- Enter your name and click the checkbox to proceed.\n- You will see friend info and a drawing section.\n\nEnjoy!");
        taIntro.setLineWrap(true);
        taIntro.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(taIntro);
        scroll.setBounds(10, 10, 780, 600);
        add(scroll);

        JLabel lblName = new JLabel("Enter your name:");
        lblName.setBounds(10, 620, 150, 30);
        add(lblName);

        tfName.setBounds(160, 620, 200, 30);
        add(tfName);

        JCheckBox cbUnderstand = new JCheckBox("I understand the directions");
        cbUnderstand.setBounds(10, 660, 300, 30);
        add(cbUnderstand);

        JButton btnNext = new JButton("Next");
        btnNext.setBounds(700, 720, 80, 30);
        btnNext.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                if (cbUnderstand.isSelected() && !tfName.getText().trim().isEmpty()) 
                {
                    CardLayout cl = (CardLayout) parent.getLayout();
                    cl.show(parent, "HomeHolder");
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "Please enter your name and check the box.");
                }
            }
        });
        add(btnNext);
    }
}

class HomePanelHolder extends JPanel 
{
    public HomePanelHolder(JPanel parent, JTextField tfName) 
    {
        setLayout(new CardLayout());

        HomePanel home = new HomePanel(this, tfName);
        BothPictPanel both = new BothPictPanel(this);
        PersonPanel p1 = new PersonPanel("Person 1", "01-01-1990", "Age: 35", "Hobbies: Reading, Traveling", "person1.jpg", this, "Person2");
        PersonPanel p2 = new PersonPanel("Person 2", "02-02-1992", "Age: 33", "Hobbies: Cooking, Hiking", "person2.jpg", this, "Person1");
        DrawPanel draw = new DrawPanel(this);

        add(home, "Home");
        add(both, "BothPict");
        add(p1, "Person1");
        add(p2, "Person2");
        add(draw, "Draw");
    }
}

class HomePanel extends JPanel 
{
    public HomePanel(JPanel parent, JTextField tfName) 
    {
        setLayout(new BorderLayout());

        JLabel lblWelcome = new JLabel("Welcome, " + tfName.getText(), SwingConstants.CENTER);
        add(lblWelcome, BorderLayout.NORTH);

        JTextArea ta = new JTextArea("Please select which page you would like to see.\n\n"
            + "- To see information about a friend and me.\n"
            + "- To make some colors and draw some shapes.\n"
            + "- To display my masterpiece.");
        ta.setEditable(false);
        add(ta, BorderLayout.CENTER);

        JPanel choices = new JPanel(new GridLayout(3, 1, 5, 10));

        JRadioButton rbFriend = new JRadioButton("To see information about a friend and me.");
        JRadioButton rbDraw = new JRadioButton("To make some colors and draw some shapes.");
        JRadioButton rbMasterpiece = new JRadioButton("Display my masterpiece.");

        ButtonGroup group = new ButtonGroup();
        group.add(rbFriend);
        group.add(rbDraw);
        group.add(rbMasterpiece);

        rbFriend.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                ((CardLayout) parent.getLayout()).show(parent, "BothPict");
            }
        });

        rbDraw.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                ((CardLayout) parent.getLayout()).show(parent, "Draw");
            }
        });

        rbMasterpiece.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                Masterpiece mp = new Masterpiece();
                mp.runIt();
            }
        });

        choices.add(rbFriend);
        choices.add(rbDraw);
        choices.add(rbMasterpiece);
        add(choices, BorderLayout.WEST);

        JButton btnHome = new JButton("Home");
        btnHome.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                ((CardLayout) parent.getLayout()).show(parent, "Home");
            }
        });
        add(btnHome, BorderLayout.SOUTH);
    }
}

class BothPictPanel extends JPanel 
{
    private Image image;

    public BothPictPanel(JPanel parent) 
    {
        try 
        {
            image = ImageIO.read(new File("wholePhoto.jpg"));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent e) 
            {
                int x = e.getX();
                if (x < getWidth() / 2) 
                {
                    ((CardLayout) parent.getLayout()).show(parent, "Person1");
                } 
                else 
                {
                    ((CardLayout) parent.getLayout()).show(parent, "Person2");
                }
            }
        });
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (image != null)
        {
            int imgWidth = image.getWidth(null);
            int imgHeight = image.getHeight(null);
            double imgAspect = (double) imgWidth / imgHeight;

            int panelWidth = this.getWidth();
            int panelHeight = this.getHeight();
            double panelAspect = (double) panelWidth / panelHeight;

            int drawWidth, drawHeight;
            if (imgAspect > panelAspect) 
            {
                drawWidth = panelWidth;
                drawHeight = (int) (panelWidth / imgAspect);
            } 
            else 
            {
                drawHeight = panelHeight;
                drawWidth = (int) (panelHeight * imgAspect);
            }

            int x = (panelWidth - drawWidth) / 2;
            int y = (panelHeight - drawHeight) / 2;

            g.drawImage(image, x, y, drawWidth, drawHeight, this);
        }
    }
}

class PersonPanel extends JPanel 
{
    public PersonPanel(String name, String dob, String age, String hobbies, String imagePath, JPanel parent, String otherCard) 
    {
        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(4, 1));
        infoPanel.add(new JLabel("Name: " + name));
        infoPanel.add(new JLabel("DOB: " + dob));
        infoPanel.add(new JLabel(age));
        infoPanel.add(new JLabel(hobbies));
        add(infoPanel, BorderLayout.WEST);

        Image image = null;
        try 
        {
            image = ImageIO.read(new File(imagePath));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        Image finalImage = image;
        JPanel imagePanel = new JPanel() 
        {
            protected void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
                if (finalImage != null) 
                {
                    int iw = finalImage.getWidth(null);
                    int ih = finalImage.getHeight(null);
                    int pw = getWidth();
                    int ph = getHeight();
                    double ratio = Math.min((double) pw / iw, (double) ph / ih);
                    int w = (int) (iw * ratio);
                    int h = (int) (ih * ratio);
                    g.drawImage(finalImage, (pw - w) / 2, (ph - h) / 2, w, h, this);
                }
            }
        };
        add(imagePanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton btnOther = new JButton("<html><center>See info for<br>the other person</center></html>");
        btnOther.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                ((CardLayout) parent.getLayout()).show(parent, otherCard);
            }
        });
        bottomPanel.add(btnOther);

        JButton btnHome = new JButton("Home");
        btnHome.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                ((CardLayout) parent.getLayout()).show(parent, "Home");
            }
        });
        bottomPanel.add(btnHome);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}

class DrawPanel extends JPanel 
{
    private int red = 255, green = 0, blue = 255, size = 100;

    public DrawPanel(JPanel parent) 
    {
        setLayout(new BorderLayout());

        JPanel left = new JPanel(new GridLayout(4, 2));

        JSlider sRed = new JSlider(0, 255, red);
        JSlider sGreen = new JSlider(0, 255, green);
        JSlider sBlue = new JSlider(0, 255, blue);
        JScrollBar sSize = new JScrollBar(JScrollBar.HORIZONTAL, size, 0, 0, 100);

        left.add(new JLabel("Red:")); left.add(sRed);
        left.add(new JLabel("Green:")); left.add(sGreen);
        left.add(new JLabel("Blue:")); left.add(sBlue);
        left.add(new JLabel("Size:")); left.add(sSize);

        JPanel right = new JPanel() 
        {
            protected void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
                g.setColor(new Color(red, green, blue));
                g.fillRect(50, 50, size, size);
            }
        };

        sRed.addChangeListener(new ChangeListener() 
        {
            public void stateChanged(ChangeEvent e) 
            {
                red = sRed.getValue();
                right.repaint();
            }
        });

        sGreen.addChangeListener(new ChangeListener() 
        {
            public void stateChanged(ChangeEvent e) 
            {
                green = sGreen.getValue();
                right.repaint();
            }
        });

        sBlue.addChangeListener(new ChangeListener() 
        {
            public void stateChanged(ChangeEvent e) 
            {
                blue = sBlue.getValue();
                right.repaint();
            }
        });

        sSize.addAdjustmentListener(new AdjustmentListener() 
        {
            public void adjustmentValueChanged(AdjustmentEvent e) 
            {
                size = sSize.getValue();
                right.repaint();
            }
        });

        left.setPreferredSize(new Dimension(200, 600));
        right.setPreferredSize(new Dimension(580, 600));
        add(left, BorderLayout.WEST);
        add(right, BorderLayout.CENTER);

        JButton btnHome = new JButton("Home");
        btnHome.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                ((CardLayout) parent.getLayout()).show(parent, "Home");
            }
        });
        add(btnHome, BorderLayout.SOUTH);
    }
}