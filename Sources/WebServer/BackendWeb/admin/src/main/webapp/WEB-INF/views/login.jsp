				<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
				<h2><img src="<c:url value="/resources/images/icons/user_32.png"/>" alt="Login" />Login</h2>
			
				<div id="login">
					
					<div class="content-box">
						<div class="content-box-header">
							<h3>Login</h3>
						</div>
					
						<div class="content-box-content">
						
							<div class="notification information">Just click login to go forward.</div>
						
							<form action="<c:url value='j_spring_security_check' />" method="POST" >
								<p>
									<label>Username</label>
									<input id="username" name="username" type="text" value="admin" />
								</p>
						
								<p>
									<label>Password</label>
									<input id="password" name="password" type="password" value="password"/>
								</p>
						
								<input type="submit" value="Login" />
							</form>
						</div>
					</div><!-- end .content-box -->
				</div><!-- end #login -->