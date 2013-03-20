import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Color;

public class Wall
{
	private double x;
	private double y;
	private Image image;

	public Wall(double x, double y, int pixelWidth, int pixelHeight)
	{
    		this.image = createImage(pixelWidth, pixelHeight);
		this.x = x;
		this.y = y;
	}

	protected BufferedImage createImage(int pixelWidth, int pixelHeight)
	{
		BufferedImage temp = new BufferedImage(pixelWidth, pixelHeight, BufferedImage.TYPE_INT_ARGB);
		int width = temp.getWidth();
		for(int i = 0; i < width; i++)
		{
			int height = temp.getHeight();
			for(int j = 0; j < height; j++)
			{
				temp.setRGB(i, j, Color.GRAY.getRGB());
			}
		}
		return temp;
	}

	public Image getImage()
	{
		return this.image;
	}

	public double getX()
	{
		return this.x;
	}

	public double getY()
	{
		return this.y;
	}
}
