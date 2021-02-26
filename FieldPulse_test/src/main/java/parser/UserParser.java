package parser;

import parser.Model.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class UserParser {

    public static User parseUser() {
        User user = new User();
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("src/test/resources/user.xml");
            Element userNode = (Element) document.getElementsByTagName("user").item(0);
            String email = userNode.getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue();
            String password = userNode.getElementsByTagName("password").item(0).getChildNodes().item(0).getNodeValue();
            user = new User(email, password);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}
