<%@page import="java.sql.*,
 javax.sql.*,
 java.io.IOException,
 javax.servlet.http.*,
 javax.servlet.*"
%>

<h3>hello world</h3><br>
<%
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    Connection connection =
      DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "mytestuser", "mypassword");
    Statement select = connection.createStatement();
    ResultSet result = select.executeQuery("Select *  from stars ");
    out.println("The results of the query");

    ResultSetMetaData  metadata;
    metadata = result.getMetaData();
    out.println("There are "+metadata.getColumnCount()+" columns <br>");
    for (int i=1; i<=metadata.getColumnCount(); i++){
      out.println("Type of column "+i+" is " +
                  metadata.getColumnTypeName(i) + "<br>");
    }
    while (result.next()){
      out.print("ID  = "+result.getString("ID"));
      out.print("  Name = "+result.getString("NAME"));
      out.println("<br>");
    }
%>


</body>
</html>
