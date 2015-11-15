import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class rsToXML 
{
	public static String getXML() throws ParserConfigurationException, SQLException, Exception
	{
		ConnectionClass a = new ConnectionClass();
		Document DOMparser = toDocument(a.readDataBase());
		return getStringFromDocument(DOMparser);
	}
	public static String getStringFromDocument(Document doc)
	{
	    try
	    {
	       DOMSource domSource = new DOMSource(doc);
	       StringWriter writer = new StringWriter();
	       StreamResult result = new StreamResult(writer);
	       TransformerFactory tf = TransformerFactory.newInstance();
	       Transformer transformer = tf.newTransformer();
	       transformer.transform(domSource, result);
	       return writer.toString().replaceAll("&", "");
	    }
	    catch(TransformerException ex)
	    {
	       ex.printStackTrace();
	       return null;
	    }
	} 
	public static Document toDocument(ResultSet rs) throws ParserConfigurationException, SQLException
{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder        = factory.newDocumentBuilder();
		Document doc                   = builder.newDocument();

		Element results = doc.createElement("markers");
		doc.appendChild(results);

		ResultSetMetaData rsmd = rs.getMetaData();
		int colCount           = rsmd.getColumnCount();

		while (rs.next())
		{
			Element row = doc.createElement("marker");
			for (int i = 1; i <= colCount; i++)
			{
				String columnName = rsmd.getColumnName(i);
				Object value      = rs.getObject(i);

				row.setAttribute(columnName, value.toString());
				results.appendChild(row);
			}
			
		}
		return doc;
			}}
