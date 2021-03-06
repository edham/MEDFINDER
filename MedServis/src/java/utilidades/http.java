package utilidades;

import entidades.wsDetalle;
import entidades.wsDoctor;
import entidades.wsEspecialidad;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class http {

    public static String urlCMP= "http://200.48.13.39/cmp/php/";
    public http() {
    }

    public static wsDetalle consultaDetalle(String cmp) {
        wsDetalle entidad = null;
        try {
            URL url = new URL(urlCMP+"detallexmedico.php?id=" + cmp);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = "";
            for (int c; (c = in.read()) >= 0;) {
                line += "" + (char) c;
            }

            line = line.replace("<< Buscar Otro", "");
            line = line.replace("</tbody>", "");
            line = line.replace("<tbody>", "");
            line = line.replace("</body>", "");
            line = line.replace("td c", "td");
            line = line.replace("align=right", "");
            line = line.replace("<th", "<td");
            line = line.replace("</th>", "</td>");
            line = line.replace("src=\"fotos/ 00.jpg\"", "");
            line = line.replace("width=\"100\"", "");
            line = line.replace("height=\"135\"", "");
            
            
             

            line = line.replace("olspan=2 align=center", "");

            String[] headIni = line.split("<head>");
            String[] headFin = line.split("</head>");
////            
            if (headIni.length == 2 && headFin.length == 2) {
                line = headIni[0] + headFin[1];

                String[] tableFin = line.split("</div>");
                line = tableFin[0] + "</div></body></html>";

            }

//            System.out.println(line);

            InputStream targetStream = new ByteArrayInputStream(line.getBytes());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(targetStream, "UTF-8");
            doc.getDocumentElement().normalize();
            
            entidad = new wsDetalle();
//            System.out.println("Root element :" + doc.getDocumentElement().getTagName());

//             

            try {
               //  System.out.println("eTable 1 ");
                Element eTable = (Element) doc.getElementsByTagName("table").item(1);
                NodeList nList = eTable.getElementsByTagName("tr");
                 Element eElement = (Element)nList.item(0);
                if(eElement!=null)
                {
                    entidad.setEstado(eElement.getElementsByTagName("td").item(0).getTextContent());
                }
                
                eElement = (Element)nList.item(4);
                if(eElement!=null)
                {
                    entidad.setMeddet(eElement.getElementsByTagName("td").item(0).getTextContent());
                }                
                
            } catch (Exception ex) {
                Logger.getLogger(http.class.getName()).log(Level.SEVERE, null, ex);
            }
             try {
               //  System.out.println("eTable 2 ");
                Element eTable = (Element) doc.getElementsByTagName("table").item(2);
                NodeList nList = eTable.getElementsByTagName("tr");
//                System.out.println("table :" + eTable.getTextContent());
//                System.out.println("nList.getLength() :" + nList.getLength());
                 for (int temp = 1; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    Element eElement = (Element) nNode;
                    entidad.setEmail(eElement.getElementsByTagName("td").item(0).getTextContent());
                    entidad.setCregional(eElement.getElementsByTagName("td").item(1).getTextContent());
                }
            } catch (Exception ex) {
                Logger.getLogger(http.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
//                 System.out.println("eTable 3 ");
                Element eTable = (Element) doc.getElementsByTagName("table").item(3);
                NodeList nList = eTable.getElementsByTagName("tr");
                 for (int temp = 1; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    Element eElement = (Element) nNode;
                     entidad.getEspecialidad().add(new wsEspecialidad(eElement.getElementsByTagName("td").item(0).getTextContent(),
                             eElement.getElementsByTagName("td").item(1).getTextContent(),
                             eElement.getElementsByTagName("td").item(2).getTextContent()));
                }
            } catch (Exception ex) {
                Logger.getLogger(http.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
              // System.out.println("img ---");
                 String[] img1 = line.split("src=\"");
                 if(img1.length==2)
                 {
                     String[] img2 = img1[1].split("\"   />");
                     if(img2.length==2)
                     {
                         entidad.setRuta(urlCMP+img2[0]);
                     }
                 }
                 
            } catch (Exception ex) {
                Logger.getLogger(http.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(http.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(http.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(http.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(http.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(http.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entidad;
    }

// 
    public static List<wsDoctor> cosulta(String cmp, String paterno, String materno, String nombres) {
        List<wsDoctor> lista = new ArrayList();
        if (cmp != null || materno != null || paterno != null || nombres != null) {
            try {
                URL url = new URL(urlCMP+"listaxmedico.php");
                Map<String, Object> params = new LinkedHashMap();
                params.put("cmp", cmp);
                params.put("apmaterno", materno);
                params.put("appaterno", paterno);
                params.put("nombres", nombres);
                //=003071&=haro&=&=&Submit=Buscar
                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    if (postData.length() != 0) {
                        postData.append('&');
                    }
                    postData.append(URLEncoder.encode((String) param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);

                Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line = "";
                for (int c; (c = in.read()) >= 0;) {
                    line += "" + (char) c;
                }
                line = line.replace("<br>", "");
                line = line.replace("width=40 height=40", "");
                line = line.replace("border=0>", "/>");
                line = line.replace("align=right", "");
                String[] headIni = line.split("<head>");
                String[] headFin = line.split("</head>");

                if (headIni.length == 2 && headFin.length == 2) {
                    line = headIni[0] + headFin[1];

                    String[] tableFin = line.split("</table>");
                    line = tableFin[0] + "</table></div></body></html>";

                }
//            System.out.println(line);
                InputStream targetStream = new ByteArrayInputStream(line.getBytes());
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(targetStream, "UTF-8");
                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("tr");
                for (int temp = 1; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    Element eElement = (Element) nNode;
                    
                    lista.add(new wsDoctor(eElement.getElementsByTagName("td").item(1).getTextContent(),
                            eElement.getElementsByTagName("td").item(2).getTextContent(),
                            eElement.getElementsByTagName("td").item(3).getTextContent(),
                            eElement.getElementsByTagName("td").item(4).getTextContent()));
                }
            } catch (MalformedURLException ex) {
                Logger.getLogger(http.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(http.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(http.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(http.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(http.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
}
