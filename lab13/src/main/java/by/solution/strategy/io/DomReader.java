package by.solution.strategy.io;

import by.solution.strategy.data.Toy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;

public class DomReader implements ReaderStrategy {
    @Override
    public List<Toy> read(File file) throws IOException, ParserConfigurationException, SAXException {
        List<Toy> toys = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(file);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("toy");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Toy toy = new Toy(
                        element.getElementsByTagName("name").item(0).getTextContent(),
                        Integer.parseInt(element.getElementsByTagName("price").item(0).getTextContent()),
                        Integer.parseInt(element.getElementsByTagName("ageLimit").item(0).getTextContent())
                );
                toys.add(toy);
            }
        }
        return toys;
    }
}