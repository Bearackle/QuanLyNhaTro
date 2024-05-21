/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.CustomControl;

/**
 *
 * @author Admin
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.event.MouseInputListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;


/**
 *
 * @author Admin
 */
public class Map extends JXMapViewer {
    private final Image image;
    public Map(){
        image = new ImageIcon(getClass().getClassLoader().getResource("icon/pinLocation.png")).getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
    }
    public void initMap(String value){
        setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo("","https://b.tile.openstreetmap.fr/hot/")));
        setAddressLocation(geocodeAddress(value));
        setZoom(2);
        MouseInputListener listener = new PanMouseInputListener(this);
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
        this.addMouseWheelListener(new ZoomMouseWheelListenerCenter(this));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int x = getWidth()/2 - 12;
        int y = getHeight()/2 - 24;
        g2.drawImage(image, x, y, null);
        Area area = new Area(new Rectangle.Double(0,0,getWidth(),getHeight()));
        area.subtract(new Area(new RoundRectangle2D.Double(5,5,getWidth() - 10, getHeight() - 10,20,20)));
        g2.setColor(new Color(255,255,255));
        g2.fill(area);
    }
     public static GeoPosition geocodeAddress(String address) {
        String url = "https://nominatim.openstreetmap.org/search?q=" + URLEncoder.encode(address,StandardCharsets.UTF_8) + "&format=json&limit=1";
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36 Edg/125.0.0.0")
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(response.body());
                if (rootNode.isArray() && rootNode.size() > 0) {
                    JsonNode location = rootNode.get(0);
                    double lat = location.get("lat").asDouble();
                    double lon = location.get("lon").asDouble();
                    return new GeoPosition(lat, lon);
                }
            } else {
                System.err.println("Error: " + response.statusCode() + " " + response.body());
            }
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
