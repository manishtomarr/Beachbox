package com.example.Utils;

import com.example.Model.User;

/**
 * Created by Junaid on 3/9/2019.
 */

public class XMLRequests {

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
}
