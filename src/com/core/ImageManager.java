/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 *
 * @author ballestax
 */
public class ImageManager {

    private HashMap<String, Image> images;

    private ImageManager() {
        images = new HashMap<>();
    }

    public static ImageManager getInstance() {
        return ImageManagerHolder.INSTANCE;
    }

    private static class ImageManagerHolder {

        private static final ImageManager INSTANCE = new ImageManager();
    }

    public Image getImagen(String key) {
        if (images.containsKey(key)) {
            return images.get(key);
        } else {
            Image img = org.balx.Resources.getImagen(key, this.getClass());
            images.put(key, img);
            return img;
        }
    }

    public Image getImagen(String key, int w, int h) {
        if (images.containsKey(key)) {
            Image img = images.get(key);
            return img.getScaledInstance(w, h, Image.SCALE_AREA_AVERAGING);
        } else {
//            Image img = Toolkit.getDefaultToolkit().getImage(clas.getResource(key));
            Image img;
            try {
                img = org.balx.Resources.getImagen(key, this.getClass());
            } catch (Exception e) {
                img = org.balx.Imagenes.centrarTexto(w, h, "img", new Font("Arial", 1, 12), Color.red, Color.gray);
            }

            images.put(key, img);
            return img.getScaledInstance(w, h, Image.SCALE_AREA_AVERAGING);
        }
    }

    public BufferedImage getBufImagen(String key) {
        if (images.containsKey(key)) {
            return org.bx.Imagenes.toBuffereredImage(images.get(key));
        } else {
            BufferedImage img = org.dzur.Resources.cargarImagen(key);
            images.put(key, img);
            return org.bx.Imagenes.toBuffereredImage(img);
        }
    }

    public BufferedImage getBufImagen(String key, int w, int h) {
        if (images.containsKey(key)) {
            Image img = images.get(key);
            return org.bx.Imagenes.toBuffereredImage(img.getScaledInstance(w, h, Image.SCALE_AREA_AVERAGING));
        } else {
            BufferedImage img = org.dzur.Resources.cargarImagen(key);
            images.put(key, img);
            return org.bx.Imagenes.toBuffereredImage(img.getScaledInstance(w, h, Image.SCALE_AREA_AVERAGING));
        }
    }
}
