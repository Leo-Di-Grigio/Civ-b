package recources.nongl;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Config;
import misc.Log;

public class Tile {
	
	private Image image;
    
    public Tile(Image image){
        this.image = image;
    }
    
    public int getWidth(){
        return image.getWidth(null);
    }

    public int getHeight(){
        return image.getHeight(null);
    }
    
    public Image getImage(){
    	return image;
    }
   
    public void draw(Graphics g, int x, int y){
        g.drawImage(image, x, y, null);
    }

    public void draw(Graphics g, int x, int y, int sizeX, int sizeY){
        g.drawImage(image, x, y, sizeX, sizeY, null);
    }
   
	public static Tile getTile(String path){
	    Image image = null;
	    
	    try {
	    	File img = new File(Config.classPath + path);
		    image = ImageIO.read(img);
	    } 
	    catch (IOException e){
	    	Log.err("Cant read file " + Config.classPath + path);
	        e.printStackTrace();
	    }
	    return new Tile(image);
	}
}

