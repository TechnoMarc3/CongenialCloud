package de.linux3000.utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;



public class Downloader implements Runnable{


    String link;
    File out;
    private static Downloader INSTANCE;


    public Downloader(String link, File out) {
        this.link = link;
        this.out = out;
        INSTANCE = this;

    }



    @Override
    public void run() {
        if(out.exists()) {
            System.out.println(link + " already downloaded : " + out.getAbsolutePath());
            return;
        }

        try {

            URL url = new URL(link);
            HttpURLConnection http = (HttpsURLConnection) url.openConnection();
            http.addRequestProperty("User-Agent", "Mozilla/4.76");
            double fileSize = (double) http.getContentLength();
            System.out.println(fileSize);
            BufferedInputStream in = new BufferedInputStream(http.getInputStream());
            FileOutputStream fos = new FileOutputStream(out);
            BufferedOutputStream bout  = new BufferedOutputStream(fos, 4096);
            byte[] buffer = new byte[4096];
            double downloaded = 0.00;
            int read = 0;
            double percentDownloaded = 0.00;
            while((read = in.read(buffer, 0, 4096)) >=0) {
                bout.write(buffer, 0, read);
                downloaded += read;
                percentDownloaded = (downloaded*100)/fileSize;
                String percent = String.format("%.4f", percentDownloaded);

            }
            bout.close();
            in.close();
           // System.out.println("Download complete");
            Thread.currentThread().stop();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Downloader getINSTANCE() {
        return INSTANCE;
    }

}

