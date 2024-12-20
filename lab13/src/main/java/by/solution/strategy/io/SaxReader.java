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
    private static final Logger logger = Logger.getLogger(SaxReader.class.getName());

    @Override
    public List<Toy> read(File file) throws SAXException {
        List<Toy> toys = new ArrayList<>();
        try {
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
                                logger.info("Toy added: " + toy);
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
            logger.info("Number of toys parsed: " + toys.size());
        } catch (ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return toys;
    }
}