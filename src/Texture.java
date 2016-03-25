import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Texture {
	
	private String dir;
	
	public Texture(String dir) {
		this.dir = dir;
	}
	
	public BufferedImage load(String name) {
		BufferedImage b = null;
		try {
			b = ImageIO.read(getClass().getResourceAsStream(dir + "/" + name));
		} catch (Exception e) {}
		return b;
	}
	
}
