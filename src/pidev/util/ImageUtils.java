/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.util;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author IMEN
 */
public class ImageUtils {
    
    public byte[] extractBytes(String ImagePath) throws IOException {
        BufferedImage img = ImageIO.read(new File(ImagePath));
        BufferedImage r = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        r = new BufferedImage(300, 300, img.getType());
        Graphics2D g = r.createGraphics();
        g.drawImage(img, 0, 0, 300, 300, null);
        g.dispose();
        ImageIO.write(r, "jpeg", baos);
        baos.flush();
        byte[] bytes = baos.toByteArray();
        baos.close();
        return bytes;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
