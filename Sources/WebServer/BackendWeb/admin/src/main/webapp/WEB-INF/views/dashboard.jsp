				<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
				<h2><img src="<c:url value="/resources/images/icons/tools_32.png"/>" alt="Manage Users" />Manage Users</h2>
			
				<div class="notification information">
					This is an informative notification. Click me to hide me.
				</div>
				
				<div class="content-box closed column-left sidebar"><!-- use the class .sidebar in combination with .column-left to create a sidebar --><!-- using .closed makes sure the content box is closed by default -->
					<div class="content-box-header">
						<h3>Closed Sidebar</h3>
					</div>
					
					<div class="content-box-content">
						<p>This content box uses 1/3rd of the total width.</p>
						<p>The total width can be easily adjusted in main.css.</p>
					</div>
				</div>
				
				<div class="content-box column-right main">
					<div class="content-box-header">
						<h3>Statistics</h3>
						
						<!-- You can create tabs with unordered lists -->
						<ul>						
							<li>
								<a href="#bar">Bar chart</a>
							</li>
													
							<li>
								<a href="#area">Area chart</a>
							</li>
						</ul>
					</div>
					
					<div class="content-box-content">												
						<div class="tab-content" id="bar">
							<table class="bargraph">
								<caption>Statistics</caption>
								<thead>
									<tr>
										<td></td>
										<th scope="col">Jan</th>
										<th scope="col">Feb</th>
										<th scope="col">Mar</th>
										<th scope="col">Apr</th>
										<th scope="col">May</th>
										<th scope="col">Jun</th>
										<th scope="col">Jul</th>
										<th scope="col">Aug</th>
										<th scope="col">Sep</th>
										<th scope="col">Oct</th>
										<th scope="col">Nov</th>
										<th scope="col">Dec</th>
									</tr>
								</thead>

								<tbody>
									<tr>
										<th scope="row">Pageviews</th>
										<td>40</td>
										<td>50</td>
										<td>88</td>
										<td>80</td>
										<td>125</td>
										<td>45</td>
										<td>34</td>
										<td>87</td>
										<td>94</td>
										<td>115</td>
										<td>86</td>
										<td>54</td>
									</tr>
								
									<tr>
										<th scope="row">Unique visitors</th>
										<td>20</td>
										<td>40</td>
										<td>68</td>
										<td>70</td>
										<td>102</td>
										<td>35</td>
										<td>14</td>
										<td>17</td>
										<td>74</td>
										<td>95</td>
										<td>45</td>
										<td>23</td>
									</tr>
								</tbody>
							</table>
						</div><!-- end .tab-content -->
						
						<div class="tab-content" id="area">
							<table class="areagraph">
								<caption>Statistics</caption>
								<thead>
									<tr>
										<td></td>
										<th scope="col">Jan</th>
										<th scope="col">Feb</th>
										<th scope="col">Mar</th>
										<th scope="col">Apr</th>
										<th scope="col">May</th>
										<th scope="col">Jun</th>
										<th scope="col">Jul</th>
										<th scope="col">Aug</th>
										<th scope="col">Sep</th>
										<th scope="col">Oct</th>
										<th scope="col">Nov</th>
										<th scope="col">Dec</th>
									</tr>
								</thead>

								<tbody>
									<tr>
										<th scope="row">Pageviews</th>
										<td>40</td>
										<td>50</td>
										<td>88</td>
										<td>80</td>
										<td>125</td>
										<td>45</td>
										<td>34</td>
										<td>87</td>
										<td>94</td>
										<td>115</td>
										<td>86</td>
										<td>54</td>
									</tr>
								
									<tr>
										<th scope="row">Unique visitors</th>
										<td>20</td>
										<td>40</td>
										<td>68</td>
										<td>70</td>
										<td>102</td>
										<td>35</td>
										<td>14</td>
										<td>17</td>
										<td>74</td>
										<td>95</td>
										<td>45</td>
										<td>23</td>
									</tr>
								</tbody>
							</table>
						</div><!-- end .tab-content -->
					</div><!-- end .content-box-content -->
				</div>
		