package domek.nbp.parser;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Paser {

	private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	
	private static Document ReadDoc(){
		try {
			return factory.newDocumentBuilder().parse(new URL("http://www.nbp.pl/kursy/xml/LastA.xml").openStream());
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static void main(String[] args) {
		Document doc = ReadDoc();
		XPath xPath = XPathFactory.newInstance().newXPath();
		List<Currency> currencies = new ArrayList<>();
		
		try {
			XPathExpression cur = xPath.compile("/tabela_kursow/pozycja");
			NodeList curList = (NodeList) cur.evaluate(doc, XPathConstants.NODESET);
			currencies = getListOfCur(curList);
  		} catch (XPathExpressionException e) {
  			e.printStackTrace();
		}
		
		for(Currency c : currencies){
			System.out.println(c);
		}

	}

	private static List<Currency> getListOfCur(NodeList curList) {
		String sCode;
		String sRate;
		List<Currency> currencies = new ArrayList<>();
		for(int i = 0; i< curList.getLength(); i++){
			sCode = "";
			sRate = "";
			NodeList childList = curList.item(i).getChildNodes();
			for (int j = 0; j < childList.getLength(); j++){
				//System.out.println(childList.item(j).getNodeName());
				if(childList.item(j).getNodeName().equals("kod_waluty")){
					sCode = childList.item(j).getTextContent();
				}
				if(childList.item(j).getNodeName().equals("kurs_sredni")){
					sRate = childList.item(j).getTextContent().replace(",", ".");
				}
			}
			if (sCode != "" && sRate != "") currencies.add(new Currency(sCode, new BigDecimal(sRate)));
		}
		return currencies;
	}

}
