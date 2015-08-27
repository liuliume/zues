<html> 
<head><title>Exception!</title></head> 
<body> 
<% Exception ex = (Exception)request.getAttribute("exception"); %> 
<H2>Exception: <%= ex.getMessage() %></H2> 
<P/> 
<% ex.printStackTrace(new java.io.PrintWriter(out)); %> 
</body> 
</html> 