import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Color;

public class Particle
{
    static int LIQUID = 0;
    static int SOLID = 1;
    private double x;
    private double y;
    private Image image;
    private boolean visible;
    private int BOARD_HEIGHT = 390;
    private double MISSILE_SPEED = 4;
    private int type;

    public Particle(double x, double y, int boardH, int pixelWidth, int type)
    {
        this.type = type;
        Image temp = createImage(pixelWidth);
        this.image = temp;
        this.visible = true;
        this.x = x;
        this.y = y;
        BOARD_HEIGHT = boardH;
    }

    protected BufferedImage createImage(int pixelWidth)
    {
        BufferedImage temp = new BufferedImage(pixelWidth, pixelWidth, BufferedImage.TYPE_INT_ARGB);
        for(int i = 0; i < temp.getWidth(); i++)
        {
            for(int j = 0; j < temp.getHeight(); j++)
            {
                if (type == LIQUID)
                    temp.setRGB(i, j, Color.BLUE.getRGB());
                else
                    temp.setRGB(i, j, Color.LIGHT_GRAY.getRGB());
            }
        }
        return temp;
    }

    public Image getImage()
    {
        return this.image;
    }

    public int getX()
    {
        return ((int)this.x/4)*4;
    }

    public int getY()
    {
        return ((int)this.y/4)*4;
    }

    public boolean isVisible()
    {
        return this.visible;
    }

    public void setVisible(boolean to)
    {
        visible = to;
    }
    
    public int getType()
    {
        return type;
    }
    
    public void moveTo(double x, double y)
    {
        this.y = y;
        this.x = x;
    }
}
