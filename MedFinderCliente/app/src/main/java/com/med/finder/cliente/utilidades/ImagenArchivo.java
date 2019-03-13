package com.med.finder.cliente.utilidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class ImagenArchivo {

    private static String fileImage="MedFinder/fotos/";
    public static int widthImg = 768;
    public static int heightImg = 1024;



    public static File SetImage() {

        File imagesFolder = new File(Environment.getExternalStorageDirectory(), fileImage+"temp/");
        if (!imagesFolder.exists())
            imagesFolder.mkdirs();
        return new File(imagesFolder, "temporal.jpg");
    }



    public static boolean getDelete() {


        File imageFile = new File(Environment.getExternalStorageDirectory(),fileImage+"/temp/temporal.jpg");
        if(imageFile.exists())
        {
            if(imageFile.delete())
                return true;
            else
                return false;
        }

        return false;

    }

    public static Bitmap getImagenBase64(String base64) {
        Bitmap imagen=null;
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        if(decodedString!=null) {
            imagen = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        }
        return imagen;

    }


    public static Bitmap  getImagen() {
        Bitmap imagen = null;
        File imagesFolder = new File(Environment.getExternalStorageDirectory(),  fileImage+"/temp");
        if(imagesFolder.exists())
        {
            String filePath = Environment.getExternalStorageDirectory() + "/" +fileImage+"temp/temporal.jpg";
            imagen=BitmapFactory.decodeFile(filePath);
        }
       return imagen;
    }

    public static String getImagenBase64(String nombre,int id) {
        String base64="";

        File imageFile = new File(Environment.getExternalStorageDirectory(),fileImage+id+"/"+nombre);
        if(imageFile.exists())
        {
            Bitmap imagen = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imagen.compress(Bitmap.CompressFormat.JPEG, 80, stream);
            byte[] Imagen= stream.toByteArray();
            base64= Base64.encodeToString(Imagen,Base64.NO_WRAP);
        }

        return base64;

    }



    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static void resizedImage() {
        int newHeight = heightImg;
        int newWidth = widthImg;
        String filePath = Environment.getExternalStorageDirectory() + "/" +fileImage+"temp/temporal.jpg";

        File imageFile = new File(filePath);
        if(imageFile.exists())
        if (filePath!=null && filePath.length()>0) {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            if (options.outHeight < options.outWidth) {
                newHeight = widthImg;
                newWidth = heightImg;
            }
            options.inSampleSize = calculateInSampleSize(options, newWidth, newHeight);
            options.inJustDecodeBounds = false;
            Bitmap compressedImage = BitmapFactory.decodeFile(filePath, options);

            File file = new File(filePath);
            if (file.exists()) file.delete ();
            try {
                FileOutputStream out = new FileOutputStream(file);
                compressedImage.compress(Bitmap.CompressFormat.JPEG, 50, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}