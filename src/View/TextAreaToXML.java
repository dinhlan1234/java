package View;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TextAreaToXML {
    public static void printXML(JTextArea textArea) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();  //tạo một instance để xây dựng tài liệu xml
            Document doc = builder.newDocument();	//Tạo một tài liệu XML mới
            Element rootElement = doc.createElement("Root"); //Tạo nút gốc với tên “Root”.
            doc.appendChild(rootElement); //Thêm nút gốc vào tài liệu.

            String[] lines = textArea.getText().split("\\n");
            for (String line : lines) {
                Element item = doc.createElement("Item");
                item.appendChild(doc.createTextNode(line));
                rootElement.appendChild(item);
            }

            // In ra màn hình console
            TransformerFactory transformerFactory = TransformerFactory.newInstance(); 
            Transformer transformer = transformerFactory.newTransformer();//Tạo một Transformer để biến đổi DOM thành dạng XML.
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //Cài đặt thuộc tính để in XML một cách rõ ràng với các khoảng trắng.
            DOMSource source = new DOMSource(doc);
            StreamResult console = new StreamResult(System.out); // định nghĩa dl và đích đến là console
            transformer.transform(source, console);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
