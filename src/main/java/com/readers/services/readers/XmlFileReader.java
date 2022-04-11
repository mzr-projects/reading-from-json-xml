package com.readers.services.readers;

import com.readers.exceptions.SaleObjectsException;
import com.readers.payload.PostalAddress;
import com.readers.payload.SaleObject;
import com.readers.payload.SaleObjectPayload;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * This reader reads from XML and creates an object to be analyzed
 */
@Slf4j
public class XmlFileReader implements FileReader {

	@Override
	public SaleObjectPayload read(String fileName) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		SaleObjectPayload saleObjectPayload = new SaleObjectPayload();

		try {
			documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(new File(fileName));
			document.getDocumentElement().normalize();

			NodeList nodeList = document.getElementsByTagName("SaleObject");
			Set<SaleObject> saleObjectList = new HashSet<>();

			for (int temp = 0; temp < nodeList.getLength(); temp++) {
				Node node = nodeList.item(temp);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;

					SaleObject saleObject = new SaleObject();
					saleObject.setType(element.getAttribute("type"));
					saleObject.setId(Integer.parseInt(element.getAttribute("id")));
					saleObject.setSizeSqm(element.getElementsByTagName("sizeSqm").item(0).getTextContent());
					saleObject.setStartingPrice(element.getElementsByTagName("startingPrice").item(0).
							getTextContent());

					NodeList addressList = document.getElementsByTagName("address");
					for (int j = 0; j < addressList.getLength(); j++) {
						Node nodeAddress = nodeList.item(temp);
						if (nodeAddress.getNodeType() == Node.ELEMENT_NODE) {
							Element elementAddress = (Element) nodeAddress;
							PostalAddress postalAddress = new PostalAddress();
							postalAddress.setCity(elementAddress.getElementsByTagName("city")
									.item(0).getTextContent());
							postalAddress.setStreet(elementAddress.getElementsByTagName("street")
									.item(0).getTextContent());
							if (!Objects.equals(elementAddress.getElementsByTagName("floor")
									.item(0).getTextContent(), "")) {
								postalAddress.setFloor(Integer.parseInt(elementAddress.getElementsByTagName("floor")
										.item(0).getTextContent()));
							} else {
								postalAddress.setFloor(null);
							}
							saleObject.setPostalAddress(postalAddress);
						}
					}

					saleObjectList.add(saleObject);
				}
			}

			saleObjectPayload.setSaleObjects(saleObjectList);

		} catch (ParserConfigurationException | SAXException | IOException ex) {
			log.error("Failed to parse XML file");
			throw new SaleObjectsException("Failed to parse XML file");
		}

		return saleObjectPayload;
	}
}
