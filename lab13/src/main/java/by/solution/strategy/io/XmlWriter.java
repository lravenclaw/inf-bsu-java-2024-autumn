package by.solution.strategy.io;

import by.solution.strategy.data.Toy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XmlWriter {
    public static void write(List<Toy> toys, String filePath) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("toys");
        doc.appendChild(rootElement);

        for (Toy toy : toys) {
            Element toyElement = doc.createElement("toy");

            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(toy.name));
            toyElement.appendChild(name);

            Element price = doc.createElement("price");
            price.appendChild(doc.createTextNode(String.valueOf(toy.price)));
            toyElement.appendChild(price);

            Element ageLimit = doc.createElement("ageLimit");
            ageLimit.appendChild(doc.createTextNode(String.valueOf(toy.ageLimit)));
            toyElement.appendChild(ageLimit);

            rootElement.appendChild(toyElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
    }
}