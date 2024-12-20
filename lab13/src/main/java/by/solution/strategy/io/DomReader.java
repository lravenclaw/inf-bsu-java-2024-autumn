package by.solution.strategy.io;

import by.solution.strategy.data.Toy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;

public class DomReader implements ReaderStrategy {
    private static final Logger logger = Logger.getLogger(DomReader.class.getName());

    @Override
    public List<Toy> read(File file) {
        List<Toy> toys = new ArrayList<>();
        DocumentBuilder builder = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        Document doc = null;
        try {
            doc = builder.parse(file);
        } catch (IOException | org.xml.sax.SAXException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("toy");
        logger.info("Number of toy elements: " + nodeList.getLength());
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
                logger.info("Toy added: " + toy);
            }
        }
        return toys;
    }
}