package parsing;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

// Opdracht 2.7
public class KlantenJaxbParser {
    public static void JaxbWriteXml(String file, Object root) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(root.getClass());
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(root, new File(file));
    }

    public static <T> T JaxbReadXml(String file, Class<T> typeParameterClass) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(typeParameterClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (T) unmarshaller.unmarshal(new File(file));
    }
}
