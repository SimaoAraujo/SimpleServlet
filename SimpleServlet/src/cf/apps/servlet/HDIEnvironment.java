package cf.apps.servlet;

import argo.jdom.JdomParser;
import argo.jdom.JsonNode;

/**
 * The class HDIEnvironment retrieves the JSON node VCAP_SERVICES from the environment.
 * The node is parsed, and values such as user, passowrd, url and port can be used
 * to establish a JDBC connection in order to perform queries.
 *     
 */
public class HDIEnvironment {

    private String vcap_services = "";
    private String host = "";
    private String dbname = "";
    private String user = "";
    private String password = "";
    private String port = "";
    private String url = "";
    private String schema = "";

public HDIEnvironment() {

   try {
       vcap_services = System.getenv("VCAP_SERVICES");
            //for cloud config
       if (vcap_services != null && vcap_services.length() > 0) {
           JsonNode root = new JdomParser().parse(vcap_services);
           JsonNode mysqlNode = root.getNode("hanatrial");
           JsonNode credentials = mysqlNode.getNode(0).getNode(
                   "credentials");
           host = credentials.getStringValue("host");
           user = credentials.getStringValue("hdi_user");
           password = credentials.getStringValue("hdi_password");
           port = credentials.getStringValue("port");
           url = credentials.getStringValue("url");
           schema = credentials.getStringValue("schema");
       } 
   } catch (Exception e) {
       e.printStackTrace();
   }
}

public String getVcap_services() {
return vcap_services;
}

public String getHost() {
return host;
}

public String getDbname() {
return dbname;
}

public String getUser() {
return user;
}

public String getPassword() {
return password;
}

public String getPort() {
return port;
}

public String getUrl() {
return url;
}

public String getSchema() {
return schema;
}

}


//package cf.apps.servlet;
//
//public class HDIEnvironment {
//
//}
