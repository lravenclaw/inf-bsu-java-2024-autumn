package by.solution.strategy.io;

import by.solution.strategy.data.Toy;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;

public class SaxReader implements ReaderStrategy {
    @Override
    public List<Toy> read(File file) throws IOException, SAXException, ParserConfigurationException {
        List<Toy> toys = new ArrayList<>();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        DefaultHandler handler = new DefaultHandler() {
            Toy toy = null;
            String content = null;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (qName.equals("toy")) {
                    toy = new Toy("", 0, 0);
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                if (toy != null) {
                    switch (qName) {
                        case "name":
                            toy.name = content;
                            break;
                        case "price":
                            toy.price = Integer.parseInt(content);
                            break;
                        case "ageLimit":
                            toy.ageLimit = Integer.parseInt(content);
                            break;
                        case "toy":
                            toys.add(toy);
                            break;
                    }
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                content = new String(ch, start, length);
            }
        };
        saxParser.parse(file, handler);
        return toys;
    }
}