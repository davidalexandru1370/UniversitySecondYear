#This project contains very basic JSP example without complicated code.

### This projects helps one understand the basic working of *JSP*. We don't write any servlets in this project.

###### Operations

1)Takes username similar to basic-servlet-example and takes a number.  
2)Outputs welcome {username} and multiplication tables  

 *Note: In basic-servlet-example project, inside servlet class(TestSessionServlet) we had to write html string(code) inside out.write(). We had to get the username and append to the html string which is inside out.write(). In JSP we write java code inside html code, we get the username which is in request inside html code by writing java code.*  

## Points to be noted
##### JSP lifecycle  

1) Translation of JSP to Java (servlet).  
2) Compilation of java to .class.  
3) Loading of servlet class.  
4) Instantiation(no-arg constructor).  
5) Initialization(init method).  
6) Invoke service() method in a new thread of execution(which in turn invoke doXXX methods).  
7) Destroy(destroy method)  

*Note: Since servlet is a singleton, from 2nd request first 5 steps will not be performed. From 2nd request service() method is invoked in a new thread of execution.*  

###### <% %>  -> Scriplet tag(multiline java code) is copy pasted as is in service method.  
###### <%= %> -> Expression tag goes inside out.print(executable java code). Executable java code is executed and returned to out.print()  
###### <%! %> -> Declartion tag is copy pasted as is inside converted java servlet class but outside service() method.  

*Note: This project needs to be refactored to use best practices which will be done later. As of now this code should be used only for understanding purposes.*

#### Converted JSP to JAVA looks like below(refer welcome.jsp)  
```html
...
...
out.write("<h3>Welcome ");  
out.print(request.getParameter("uname") );  
out.write("</h3>\r\n");  
out.write("<pre>\r\n");
String value = request.getParameter("number");
try
{
	Integer number = Integer.parseInt(value);
	for(int i=1;i<=10;i++)
	{
		out.println(number + " * " + i +" = " + (number*i)  );
	}
}
catch(NumberFormatException e)
{
	out.println("Invalid number");
}

     out.write("\r\n");
     out.write("<pre>\r\n");
...
...
```


 
