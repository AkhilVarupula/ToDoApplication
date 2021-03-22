<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
<h3>Login </h3>  
<font color="red"> ${message}</font>
<form method="post">
    <label for="name"><b>Username:</b></label>
	<input type="text" name="name" placeholder="Enter Username" required="required"/><br/><br/>  
	 <label for="password"><b>Password:</b></label>
	<input type="password" name="password" placeholder="Enter Password" required="required"/><br/><br/>  
	<button type="submit">Login </button>
	
</form>
<%@ include file="common/footer.jspf" %>
</div>