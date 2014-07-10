				<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
				<div id="header_buttons">
					
					<a href="#modal" rel="modal"><img src="<c:url value="/resources/images/icons/envelope.png"/>"
								alt="3 Messages" />3</a> 
					<a href="#modal2" rel="modal">modal box test</a>
					<a href="<c:url value="/j_spring_security_logout" />" >logout</a> 
					<a href="#">view website</a>
				
				</div>
				<!-- end #header_buttons -->
				
				<!-- Modal box -->
				<div id="modal">
					<div class="modalbox">
						<div class="modalhead">
							<img src="<c:url value="/resources/images/modaltop.png" />" alt="Modal arrow" /> Mailbox
						</div>
				
						<div class="modalcontent">
							<div class="message">
								<div class="author">
									<a href="#">Teun</a>
								</div>
								<div class="content">This skin can be easily styled!</div>
								<div class="datetime">16-05 - 08:16</div>
							</div>
				
							<div class="message">
								<div class="author">
									<a href="#">Pieter</a>
								</div>
								<div class="content">It can also be styled very easily.</div>
								<div class="datetime">11-05 - 16:27</div>
							</div>
				
							<div class="message">
								<div class="author">
									<a href="#">Jane Doe</a>
								</div>
								<div class="content">This template uses a lot of nice CSS3
									effects.</div>
								<div class="datetime">10-05 - 18:42</div>
							</div>
						</div>
				
						<div class="modalfoot">
							<img src="<c:url value="/resources/images/icons/newmessage.png" />" alt="New message" /> New
							message
						</div>
					</div>
				</div>
				
				<!-- Modal box 2 -->
				<div id="modal2">
					<div class="modalbox">
						<div class="modalhead">						
							<img src="<c:url value="/resources/images/modaltop.png" />" alt="Modal arrow" /> Mailbox 2
						</div>
				
						<div class="modalcontent">
							<div class="message">
								<div class="author">
									<a href="#">Pieter</a>
								</div>
								<div class="content">It can also be styled very easily.</div>
								<div class="datetime">11-05 - 16:27</div>
							</div>
				
							<div class="message">
								<div class="author">
									<a href="#">Jane Doe</a>
								</div>
								<div class="content">This template uses a lot of nice CSS3
									effects.</div>
								<div class="datetime">10-05 - 18:42</div>
							</div>
						</div>
				
						<div class="modalfoot">
							<img src="<c:url value="/resources/images/icons/newmessage.png" />" alt="New message" /> New
							message
						</div>
					</div>
				</div>