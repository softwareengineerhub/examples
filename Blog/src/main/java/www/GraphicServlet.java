/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author RSDENIS
 */
public class GraphicServlet extends HttpServlet {

  @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String author = req.getRemoteUser();
        resp.setContentType("image/jpeg");
        OutputStream stream = resp.getOutputStream();
        /*JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(stream);
        int ImageWidth = 1000;
        int ImageHeight = 500;
        BufferedImage bi = new BufferedImage(ImageWidth, ImageHeight, BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        paint(g2, 1000, 400, author);
        encoder.encode(bi);*/
    }


    public void paint(Graphics2D g2, int xLength, int yLength, String author) {
        ArrayList<News> arList = null;
        try {
            arList = (ArrayList<News>) DataModel.instance().getNewsByAuthor(author);
            int[] views = new int[arList.size()];
            //DateFormat format = DateFormat.getTimeInstance(DateFormat.MEDIUM);
            int i = 0;
            for (News n : arList) {
                views[i] = n.getView();
                i++;
                //   date[i]=format.format(n.getDate());
            }
            g2.setColor(Color.red);
            g2.fillRect(0, yLength,xLength, yLength);
            g2.setColor(Color.yellow);
            g2.fillRect(0, 0, xLength, yLength);
            g2.setColor(Color.green);
            for (i = 0; i < 5; i++) {
                g2.drawLine(xLength, yLength - i, 0, yLength - i);
            }
            int w = (xLength - 10) / arList.size();
            int[] b = views;
            Arrays.sort(b);
            int max = Math.max(b[b.length - 1], 1);
          //  float h = 400 / max;
            for (i = 0; i < arList.size(); i++) {
                g2.setColor(Color.green);
                for (int j = 0; j < 5; j++) {
                    g2.drawLine(w * i + 20 + j, yLength+10, w * i + 20 + j, yLength-10);
                }
                g2.drawString(arList.get(i).getDate() + "", w * i + 20, yLength+40);
                if (i > 0) {
                    g2.setColor(Color.blue);
                    int index = arList.get(i - 1).getView();
                    int next = arList.get(i).getView();
                    g2.draw(new Line2D.Double(w * (i - 1) + 20, yLength - index * yLength / max, w * (i) + 20, yLength - next * yLength / max));
                    g2.drawString(arList.get(i - 1).getView() + "", w * (i - 1) + 20, yLength - index * yLength / max + 10);
                    if (i == arList.size() - 1) {
                        g2.drawString(arList.get(i).getView() + "", w * (i) + 20, yLength - next * yLength / max);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
   