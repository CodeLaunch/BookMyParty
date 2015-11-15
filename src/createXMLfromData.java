///*
// * Copyright 2013 Alcatel-Lucent
// * All Rights Reserved
// * This is unpublished proprietary source code of Alcatel-Lucent.
// * The copyright notice above does not evidence any actual
// * or intended publication of such source code.
// *
// * Not to be disclosed or used except in accordance with applicable
// * agreements.
// */
//
//
//import java.io.File;
//import java.sql.ResultSet;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//
//import org.apache.log4j.Logger;
//
//import templateClasses.Cater;
//
//public class createXMLfromData
//{
//	private static Logger _log = Logger.getLogger(createXMLfromData.class);
//	
//    private createXMLfromData()
//    {       
//    }
//    
//    // read Cater XML templates and returned unMarsharlled cater java object containing caterRecords.
//    public static Cater readCaterTemplate(String templateName) throws Exception
//    {
//    	String templateResource = "/WEB-INF/classes/" + templateName;
//        _log.debug("cater Template resource: " + templateResource);
////        String templateFileName = BukPartyInit._servletContext.getRealPath(templateResource);
//        _log.debug("read Cater Template file: " + templateFileName);
//        
//        Cater cater = new Cater();
//		
//		JAXBContext contextest = null;
//		try {
//			contextest = JAXBContext.newInstance(Cater.class);
//			javax.xml.bind.Unmarshaller unMarshaler = null;
//			unMarshaler = contextest.createUnmarshaller();
//			cater= (Cater) unMarshaler.unmarshal( new File(templateFileName) );
//		} catch (JAXBException e) {
//			throw new Exception(e.getMessage());
//		}
//		return cater;
//    }
//    public static Cater replaceCSVTemplate2UserTestValues( Cater cater, DBResponse result) throws Exception
//    {
//    	Cater caterObj = new Cater();
//    	for(NameValues nv : result.getData())
//    	{
//    		caterObj.setAddress("df");
//    	}
//    	return caterObj;
//    }
//      // read Cater XML templates and returned unMarsharlled cater java object containing caterRecords.
//    public static boolean generateCaterTestsXml(String generatedCaterName, String outPutDirPath, Cater caterFile) throws Exception
//    {
//    	boolean fileGenerated = false;
//    	JAXBContext contextest = null;
//    	javax.xml.bind.Marshaller m = null;
//		contextest = JAXBContext.newInstance(Cater.class);
//		m = contextest.createMarshaller();
//		m.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//		m.marshal( caterFile, new File(outPutDirPath+generatedCaterName) );
//		fileGenerated = true;
//		return fileGenerated;
//    }  
//    
//    
//  
//    
//}
//
