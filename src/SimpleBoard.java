import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.image.*;

// author Vineet Kosaraju + Nikita Kosolobov

public class SimpleBoard extends JPanel implements MouseMotionListener, ActionListener, KeyListener
{
    private Timer timer;
    private double boardW;
    private double boardH;
    private JFrame owner;
    private ArrayList<Particle> particles;
    private ArrayList<Wall> walls;
    private boolean[][] spaces;
    private int type;

    public SimpleBoard(int x, int y, JFrame owner)
    {
        type = 0;
        spaces = new boolean[75][75];
        for (int i=0; i<spaces.length; i++)
        {
            for (int j=0; j<spaces[i].length; j++)
            {
                spaces[i][j] = false;
            }
        }
        particles = new ArrayList<Particle>();
        walls = new ArrayList<Wall>();
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        boardW = x;
        boardH = y;
        owner = owner;
        timer = new Timer(10, this);
        timer.start();
    }

    public void mouseMoved(MouseEvent b)
    {
        int x = ((int)b.getX()/4)*4;
        int y = ((int)b.getY()/4)*4;
        if (!spaces[x/4][y/4])
        {
            particles.add(new Particle(x, y, (int) boardH, 4, type));
            Particle p = particles.get(particles.size()-1);
            spaces[p.getX()/4][p.getY()/4] = true;
        }
    }

    public void mouseDragged(MouseEvent b)
    {
        int x = ((int)b.getX()/8)*8;
        int y = ((int)b.getY()/8)*8;
        if (!spaces[x/4][y/4])
        {
            walls.add(new Wall(x, y, 8, 8));
            Wall w = walls.get(walls.size()-1);
            spaces[x/4][y/4] = true;
            spaces[x/4+1][y/4] = true;
            spaces[x/4][y/4+1] = true;
            spaces[x/4+1][y/4+1] = true;
        }
    }

    public void keyReleased(KeyEvent e) { }

    public void keyTyped(KeyEvent e) { }

    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_R)
        {
            particles.clear();
            walls.clear();
            for (int i=0; i<spaces.length; i++)
            {
                for (int j=0; j<spaces[i].length; j++)
                {
                    spaces[i][j] = false;
                }
            }
            repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_1)
            type = 0;
        else if (e.getKeyCode() == KeyEvent.VK_2)
            type = 1;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        for(int i = particles.size() - 1; i >= 0; i--)
        {
            Particle temp = particles.get(i);
            if(isValid(temp))
                g2d.drawImage(temp.getImage(), (int)temp.getX(), (int)temp.getY(), this);
            else
            {
                spaces[temp.getX()/4][temp.getY()/4] = false;
                particles.remove(i);
                temp.setVisible(false);
            }
        }
        for(int i = 0; i < walls.size(); i++)
        {
            Wall tep = walls.get(i);
            g2d.drawImage(tep.getImage(), (int) tep.getX(), (int) tep.getY(), this);
            //if (tep instanceof Emitter)
            {
                //Make particles in open areas
            }
        }
        g.dispose();
    }

    public void move(Particle part)
    {
        int x = part.getX();
        int y = part.getY();
        if (spaces[x/4+1][y/4] && !spaces[x/4-1][y/4+1])
            part.moveTo(x-4, y+4);
        else if (spaces[x/4-1][y/4] && !spaces[x/4+1][y/4+1])
            part.moveTo(x+4, y+4);
        else if (spaces[x/4][y/4+1] == false)
            part.moveTo(x, y+4);
        else if (part.getType() == Particle.LIQUID)
        {
            if (spaces[x/4-1][y/4]==false && spaces[x/4+1][y/4]==false)
            {
                if (Math.random()>=0.5)
                    part.moveTo(x-4, y);
                else
                    part.moveTo(x+4, y);
            }
            else if (spaces[x/4+1][y/4] && !spaces[x/4-1][y/4])
                part.moveTo(x-4, y);
            else if (spaces[x/4-1][y/4] && !spaces[x/4+1][y/4])
                part.moveTo(x+4, y);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        for(int i = 0; i < particles.size(); i++)
        {
            Particle p = particles.get(i);
            if(isValid(p))
            {
                spaces[p.getX()/4][p.getY()/4] = false;
                move(p);
                spaces[p.getX()/4][p.getY()/4] = true;
            }
        }
        repaint();
    }

    public boolean isValid(Particle p)
    {
        return p.getX()>0 && p.getY()>0 && p.getX()<296 && p.getY()<296;
    }
}
