package by.solution.strategy.io;

import by.solution.strategy.data.Toy;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ReaderStrategy {
    List<Toy> read(File file) throws IOException, ParserConfigurationException, SAXException;
}
