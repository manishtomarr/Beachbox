package com.example.Utils;

import com.example.Model.Client;
import com.example.Model.User;

/**
 * Created by Junaid on 3/9/2019.
 */

public class XMLRequests {

    /***** get class schedule xml request ****/
    public static String getXmlReqForGetClasses() {
        String xmlInput = " <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns=\"http://clients.mindbodyonline.com/api/0_5_1\">\r\n" +
                "                <soapenv:Header/>\r\n" +
                "                <soapenv:Body>\r\n" +
                "                   <GetClasses>\r\n" +
                "                      <Request>\r\n" +
                "                         <SourceCredentials>\r\n" +
                "                            <SourceName>"+ Constants.SOURCE_NAME+"</SourceName>\r\n" +
                "		    				 <Password>"+Constants.SOURCE_PASSWORD+"</Password>\r\n" +
                "					        <SiteIDs>\r\n" +
                "						        <int>"+Constants.SITE_ID+"</int>\r\n" +
                "					        </SiteIDs>\r\n" +
                "                         </SourceCredentials>\r\n" +
                "                         <UserCredentials>\r\n" +
                "                            <Username>"+ User.username+"</Username>\r\n" +
                "                            <Password>"+User.password+"</Password>\r\n" +
                "                            <SiteIDs>\r\n" +
                "                               <int>"+Constants.SITE_ID+"</int>\r\n" +
                "                            </SiteIDs>\r\n" +
                "                         </UserCredentials>\r\n" +
                "                         <Fields>\r\n" +
                "                            <string>Classes.Resource</string>\r\n" +
                "                         </Fields>\r\n" +
                "                         <XMLDetail>Basic</XMLDetail>\r\n" +
                "                         <PageSize>10</PageSize>\r\n" +
                "                         <CurrentPageIndex>0</CurrentPageIndex>\r\n" +
                "                      </Request>\r\n" +
                "                   </GetClasses>\r\n" +
                "                </soapenv:Body>\r\n" +
                "             </soapenv:Envelope>";
        return xmlInput;
    }


    /*********** Register Client with ID XML Request ***************/
    public static String getXmlReqForAddClientWithID(Client client) {
        String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns=\"http://clients.mindbodyonline.com/api/0_5_1\">\n" +
                "    <soapenv:Header/>\n" +
                "    <soapenv:Body>\n" +
                "        <AddOrUpdateClients>\n" +
                "            <Request>\n" +
                "                <SourceCredentials>\n" +
                "                    <SourceName>"+Constants.SOURCE_NAME+"</SourceName>\n" +
                "                    <Password>"+Constants.SOURCE_PASSWORD+"</Password>\n" +
                "                    <SiteIDs>\n" +
                "                        <int>"+Constants.SITE_ID+"</int>\n" +
                "                    </SiteIDs>\n" +
                "                </SourceCredentials>\n" +
                "                <XMLDetail>Full</XMLDetail>\n" +
                "                <PageSize>10</PageSize>\n" +
                "                <CurrentPageIndex>0</CurrentPageIndex>\n" +
                "                <Clients>\n" +
                "                    <Client>\n" +
                "                        <ID>"+client.getId()+"</ID>\n" +
                "                        <FirstName>"+client.getFname()+"</FirstName>\n" +
                "                        <LastName>"+client.getFname()+"</LastName>\n" +
                "                        <BirthDate>"+client.getBdate()+"</BirthDate>\n" +
                "                        <MobilePhone>"+client.getMobile()+"</MobilePhone>\n" +
                "                        <Username>"+client.getUsername()+"</Username>\n"+
                "                        <Password>"+client.getPwd()+"</Password>\n"+
        		"                        <Email>"+client.getEmail()+"</Email>\n"+
                "                        <AddressLine1>"+client.getAddress()+"</AddressLine1>\n"+
        		"                        <City>"+client.getCity()+"</City>\n"+
            	"                        <State>"+client.getState()+"</State>\n"+
            	"                        <PostalCode>"+client.getPostalCode()+"</PostalCode>\n"+
                "                        <Gender>"+client.getGender()+"</Gender>\n"+
                "                        <ReferredBy>string</ReferredBy>\n"+
                "                    </Client>\n" +
                "                </Clients>\n" +
                "            </Request>\n" +
                "        </AddOrUpdateClients>\n" +
                "    </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        return xmlInput;
    }

    /******************** Validate Login ***********************/
    public static String getXMLReqForValidateLogin(String username, String password) {
        String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "               <soapenv:Header/>\n" +
                "               <soapenv:Body>\n" +
                "                  <ValidateLogin xmlns=\"http://clients.mindbodyonline.com/api/0_5_1\">\n" +
                "                     <Request>\n" +
                "                        <SourceCredentials>\n" +
                "                          <SourceName>"+Constants.SOURCE_NAME+"</SourceName>\n" +
                "                          <Password>"+Constants.SOURCE_PASSWORD+"</Password>\n" +
                "                          <SiteIDs>\n" +
                "                             <int>"+Constants.SITE_ID+"</int>\n" +
                "                        </SiteIDs>\n" +
                "                        </SourceCredentials>\n" +
                "                        <Username>"+username+"</Username>\n" +
                "                        <Password>"+password+"</Password>\n" +
                "                     </Request>\n" +
                "                  </ValidateLogin>\n" +
                "               </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        return xmlInput;
    }

}
