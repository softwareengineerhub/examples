package com.conarhco.rating.drawing;

import com.conarhco.rating.DataModule;
import com.conarhco.rating.SiteProfile;
import com.conarhco.rating.StatisticsPeriod;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 777
 */
public class DrawStatisticsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        int drawPeriod = Integer.parseInt(req.getParameter("drawPeriod"));
       
        if (req.getSession().getAttribute("colorList") == null) {
            req.getSession().setAttribute("colorList", initColorList());
        }
         List<Color> colorList = (List<Color>) req.getSession().getAttribute("colorList");
        int sid=Integer.parseInt(req.getParameter("sid"));
        int imageWidth = 570;
        int imageHeight = 300;
        Integer[][] dataToDraw = getDataToDraw(sid, drawPeriod);
        Drawable drawable = new Drawable(imageWidth, imageHeight, dataToDraw, colorList);
        BufferedImage res = drawable.draw();
        ImageWriter ir = ImageIO.getImageWritersByFormatName("jpeg").next();
        ir.setOutput(ImageIO.createImageOutputStream(resp.getOutputStream()));
        ir.write(res);
    }

    private Integer[][] getDataToDraw(int sid, int drawPeriod) {
        Integer[][] dataToDraw = null;
        try {
            Collection<String> collection = DataModule.getInstance().getCounterNamesForSite(sid);
            dataToDraw = new Integer[collection.size()][];
            int i = 0;
            for (String s : collection) {
                List<Integer> validData = new ArrayList<Integer>();
                StatisticsPeriod period = StatisticsPeriod.DAY;
                if(drawPeriod==4){
                    period=StatisticsPeriod.WEEK;
                }
                else if(drawPeriod==12){
                    period=StatisticsPeriod.MONTH;
                }
                Map<Date, Integer> map = DataModule.getInstance().getCountDataToDraw(sid, period, s);
                for (Date d : map.keySet()) {
                        validData.add(map.get(d));
                }
                dataToDraw[i] = validData.toArray(new Integer[validData.size()]);
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrawStatisticsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataToDraw;
    }

    private Integer[][] getDataToDraw(SiteProfile sp, int drawPeriod) {
        Integer[][] dataToDraw = null;
        try {
            Collection<String> collection = DataModule.getInstance().getCounterNamesForSite(sp);
            dataToDraw = new Integer[collection.size()][];
            Calendar c = Calendar.getInstance();
            switch (drawPeriod) {
                case 30:
                    c.add(Calendar.DAY_OF_MONTH, 30);
                    break;
                case 4:
                    c.add(Calendar.WEEK_OF_MONTH, 4);
                    break;
                default :
                    c.add(Calendar.YEAR, 1);
            }
            int i = 0;
            for (String s : collection) {
                List<Integer> validData = new ArrayList<Integer>();
                TreeMap<Date, Integer> map = DataModule.getInstance().getCounter(sp, s);
                Set<Date> keys = map.keySet();
                for (Date d : keys) {
                    if (d.before(c.getTime())) {
                        validData.add(map.get(d));
                       // validData.add(map.get(d) + 7);
                        //validData.add(map.get(d) - 3);
                    }
                }
                while(validData.size()<drawPeriod){
                    validData.add(-3);
                }
                dataToDraw[i] = validData.toArray(new Integer[validData.size()]);
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrawStatisticsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataToDraw;
    }

    private List<Color> initColorList() {
        List<Color> colorList = new ArrayList<Color>();
        colorList.add(Color.RED);
        colorList.add(Color.YELLOW);
        colorList.add(Color.GREEN);
        colorList.add(Color.RED);
        colorList.add(Color.BLUE);
        colorList.add(Color.ORANGE);
        colorList.add(Color.PINK);
        colorList.add(Color.CYAN);
        colorList.add(Color.PINK);
        colorList.add(Color.lightGray);
        colorList.add(Color.ORANGE);
        colorList.add(Color.PINK);
        colorList.add(Color.CYAN);
        colorList.add(Color.PINK);
        colorList.add(Color.lightGray);
        return colorList;
    }
}
