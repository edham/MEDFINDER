/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author EdHam
 */
public class Utilidades {
    public static byte[] Redimensionar(byte[] bytes, int w, int h)
    {
        byte[] imageBytes=null;
        
        try {
            InputStream in = new ByteArrayInputStream(bytes);
            BufferedImage bufferedImage = ImageIO.read(in);
            int finalw = w;
            int finalh = h;
            double factor = 1.0d;
            if(bufferedImage.getWidth() > bufferedImage.getHeight()){
                factor = ((double)bufferedImage.getHeight()/(double)bufferedImage.getWidth());
                finalh = (int)(finalw * factor);                
            }else{
                factor = ((double)bufferedImage.getWidth()/(double)bufferedImage.getHeight());
                finalw = (int)(finalh * factor);
            }   
            BufferedImage resizedImg = new BufferedImage(finalw, finalh, BufferedImage.TRANSLUCENT);
            Graphics2D g2 = resizedImg.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(bufferedImage, 0, 0, finalw, finalh, null);
            g2.dispose();
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImg, "png", baos);
            baos.flush();
            imageBytes = baos.toByteArray();
            baos.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imageBytes;
    }

}
