import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Cursor;

public class Main extends JFrame
{
  private SimpleBoard board;
  public Main(int x, int y)
  {
    BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
    Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
    getContentPane().setCursor(blankCursor);
    board = new SimpleBoard(x, y, this);
    add(board);
    setSize(x, y);
    setLocationRelativeTo(null);
    setResizable(false);
    setUndecorated(true);
    setVisible(true);
  }

  public static void main(String[] args) throws InterruptedException
  {
    if(args.length == 0)
    {
      Main main = new Main(300, 300);
    }
    else if(args.length < 2)
    {
    Toolkit tk = Toolkit.getDefaultToolkit();
    int x = (int) tk.getScreenSize().getWidth();
    int y = (int) tk.getScreenSize().getHeight();
    Main main = new Main(x , y );
    }
    else
    {
      Main main = new Main(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }
  }
}