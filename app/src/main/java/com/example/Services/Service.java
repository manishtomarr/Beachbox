package com.example.Services;

import android.util.Log;

import com.example.Model.Clazz;
import com.example.Model.Client;
import com.example.Model.Location;
import com.example.Model.User;
import com.example.Utils.Constants;
import com.example.Utils.XMLRequests;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Junaid on 3/9/2019.
 */

public class Service {


    public Document soapRequest(String wsURL, String xmlInput, String SOAPAction) {
        Document doc = null;
        try {
            URL url = new URL(wsURL);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte[] buffer = new byte[xmlInput.length()];
            buffer = xmlInput.getBytes();
            bout.write(buffer);
            byte[] b = bout.toByteArray();
            httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream out = httpConn.getOutputStream();

            //Write the content of the request to the outputstream of the HTTP Connection.
            out.write(b);
            out.close();

            //Read the response.
            InputStream istream = httpConn.getInputStream();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(istream);
            doc.getDocumentElement().normalize(); //may be take it out
        } catch (java.net.MalformedURLException e) {
            Log.e("MalformedURLException", e.getMessage() + e.getStackTrace());
        } catch (java.io.IOException e) {
            Log.e("IOException", e.getMessage() + e.getStackTrace());
        } catch (javax.xml.parsers.ParserConfigurationException e) {
            Log.e("ParserConfigException", e.getMessage() + e.getStackTrace());
        } catch (org.xml.sax.SAXException e) {
            Log.e("SAXException", e.getMessage() + e.getStackTrace());
        } catch (Exception e) {
            Log.e("Exception", e.getMessage() + e.getStackTrace());
        }
        return doc;
    }



    public ArrayList<Clazz> getClasses() {
        ArrayList<Clazz> listOfClasses = new ArrayList<>();
        try {
            String wsUrl = Constants.WEB_SERVICE_URL.concat(Constants.CLASS_API_NAME);
            Document doc = soapRequest(wsUrl, XMLRequests.getXmlReqForGetClasses(), Constants.GET_CLASSES_SOAP_ACTION);
            NodeList nList = doc.getElementsByTagName("Class");
            System.out.println("----------------------------");
            for (int i = 0; i < nList.getLength(); i++) {
                Clazz clazz = new Clazz();
                Node nNode = nList.item(i);
                Log.d("Current Element :" , nNode.getNodeName());

                Element e = (Element) nNode;
                clazz.setClassScheduleID(e.getElementsByTagName("ClassScheduleID").item(0).getTextContent());
                clazz.setMaxCapacity(e.getElementsByTagName("MaxCapacity").item(0).getTextContent());
                clazz.setWebCapacity(e.getElementsByTagName("WebCapacity").item(0).getTextContent());
                clazz.setTotalBooked(e.getElementsByTagName("TotalBooked").item(0).getTextContent());
                clazz.setTotalBookedWaitlist(e.getElementsByTagName("TotalBookedWaitlist").item(0).getTextContent());
                clazz.setWebBooked(e.getElementsByTagName("WebBooked").item(0).getTextContent());
                clazz.setCanceled(Boolean.parseBoolean(e.getElementsByTagName("IsCanceled").item(0).getTextContent()));
                clazz.setActive(Boolean.parseBoolean(e.getElementsByTagName("Active").item(0).getTextContent()));
                clazz.setEnrolled(Boolean.parseBoolean(e.getElementsByTagName("IsEnrolled").item(0).getTextContent()));
                clazz.setID(e.getElementsByTagName("ID").item(0).getTextContent());
                clazz.setAvailable(Boolean.parseBoolean(e.getElementsByTagName("IsAvailable").item(0).getTextContent()));
                clazz.setStartDateTime(e.getElementsByTagName("StartDateTime").item(0).getTextContent());
                clazz.setEndDateTime(e.getElementsByTagName("EndDateTime").item(0).getTextContent());

                Location location = new Location();
                Element e1 = (Element) nNode.getChildNodes().item(1);
                location.setBusinessDescription(e1.getElementsByTagName("BusinessDescription").item(0).getTextContent());
                location.setSiteID(e1.getElementsByTagName("SiteID").item(0).getTextContent());
                location.setHasClasses(Boolean.parseBoolean(e1.getElementsByTagName("HasClasses").item(0).getTextContent()));
                location.setID(e1.getElementsByTagName("ID").item(0).getTextContent());
                location.setName(e1.getElementsByTagName("Name").item(0).getTextContent());
                clazz.setLocation(location);

                listOfClasses.add(clazz);
            }

        }catch(Exception e) {
            Log.e("Get Classes", e.getMessage() + e.getStackTrace());
        }
        return listOfClasses;
    }

    //add or update client in db and return message from response
    public String addOrUpdateClient(Client c) {
        String msg = "";
        try {
            String wsUrl = Constants.WEB_SERVICE_URL.concat(Constants.CLIENT_API_NAME);
            Document doc = soapRequest(wsUrl, XMLRequests.getXmlReqForAddClientWithID(c), Constants.ADD_OR_UPDATE_CLIENT_SOAP_ACTION);

            NodeList nList = doc.getElementsByTagName("AddOrUpdateClientsResult");
            if(nList.getLength() > 0) {
                Element e = (Element) nList.item(0);
                msg = e.getElementsByTagName("Status").item(0).getTextContent();
                if(!e.getElementsByTagName("Status").item(0).getTextContent().equals("Success")) {
                    Element cElement = (Element) doc.getElementsByTagName("Messages").item(0);
                    msg = cElement.getElementsByTagName("string").item(0).getTextContent();
                }
            } else {
                msg = "Could not proceed, please try again with valid values";
            }
        } catch(Exception e) {
            Log.e("Get Classes", e.getMessage() + e.getStackTrace());
            msg = "Some error occurred, please try again with valid values";
        }
        return msg;
    }

    /**** validate Login and return client or null ****/
     public Client validateLogin(String username, String pwd) {
         try {
             String wsUrl = Constants.WEB_SERVICE_URL.concat(Constants.CLIENT_API_NAME);
             Document doc = soapRequest(wsUrl, XMLRequests.getXMLReqForValidateLogin(username, pwd), Constants.VALIDATE_CLIENT_LOGIN_SOAP_ACTION);
             NodeList nList = doc.getElementsByTagName("ValidateLoginResult");
             if(nList.getLength() > 0) {
                 Element e = (Element) nList.item(0);
                 if ((e.getElementsByTagName("Status").item(0).getTextContent()).equals("Success")) {
                    Client client = new Client();
                    Element cElement = (Element) doc.getElementsByTagName("Client").item(0);
                    client.setFname(cElement.getElementsByTagName("FirstName").item(0).getTextContent());
                    client.setLname(cElement.getElementsByTagName("LastName").item(0).getTextContent());
                    client.setEmail(cElement.getElementsByTagName("Email").item(0).getTextContent());
                    client.setAddress(cElement.getElementsByTagName("AddressLine1").item(0).getTextContent());
                    client.setCity(cElement.getElementsByTagName("City").item(0).getTextContent());
                    client.setState(cElement.getElementsByTagName("State").item(0).getTextContent());
                    client.setPostalCode(cElement.getElementsByTagName("PostalCode").item(0).getTextContent());
                    client.setMobile(cElement.getElementsByTagName("MobilePhone").item(0).getTextContent());
                    client.setGender(cElement.getElementsByTagName("Gender").item(0).getTextContent());

                    if(cElement.getElementsByTagName("PhotoURL").item(0) != null) {
                        client.setPhotoUrl(cElement.getElementsByTagName("PhotoURL").item(0).getTextContent());
                    }

                    client.setBdate(cElement.getElementsByTagName("BirthDate").item(0).getTextContent());
                    client.setId(cElement.getElementsByTagName("ID").item(0).getTextContent());
                    client.setUsername(username);
                    client.setPwd(pwd);

                    return client;
                 }
             }
         } catch(Exception e) {
             Log.e("Get Classes", e.getMessage() + e.getStackTrace());
         }

         return null;
    }
}
