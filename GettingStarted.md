<div xmlns="http://www.w3.org/1999/xhtml" id="wikimaincol" class="vt">

 <h1><a name="Introduction"/>Introduction<a class="section_anchor" href="#Introduction"/></h1>
 <p>Explains how to run the example application. </p>

 <h1><a name="Details"/>Details<a class="section_anchor" href="#Details"/></h1>
 
 <p>The application isn't complete yet in any way, but... </p>
 <p>You can run a test method and trace the commands and queries: </p>
 
 <ol>
 <li>Check out all projects from the SVN trunk </li>
 <li>Build the projects with Maven using the <a rel="nofollow" href="http://code.google.com/p/axon-auction-example/source/browse/#svn/trunk/auction-root">auction-root</a> project </li>
 <li>Start the local ActiveMQ, DerbyDB and SMTP Server using <a rel="nofollow" href="http://code.google.com/p/axon-auction-example/source/browse/trunk/auction-infrastructure/src/main/java/org/fuin/auction/infrastructure/InfrastructureStart.java">InfrastructureStart</a> class - If you use Eclipse you can use this <a rel="nofollow" href="http://code.google.com/p/axon-auction-example/source/browse/trunk/auction-infrastructure/Start+Auction+Infrastructure.launch">launch configuration</a> </li>
 <li>Run the <a rel="nofollow" href="http://code.google.com/p/axon-auction-example/source/browse/trunk/auction-command-server/src/main/sql/create-db.sql">SQL Script</a> to create the command server tables (The tables for the query server are created automatically). </li>
 <li>Start the <a rel="nofollow" href="http://code.google.com/p/axon-auction-example/source/browse/trunk/#trunk/auction-command-server">Command Server</a> and <a rel="nofollow" href="http://code.google.com/p/axon-auction-example/source/browse/trunk/#trunk/auction-query-server">Query Server</a> web applications (using Tomcat 6 for example) </li>
 <li>Run the <a rel="nofollow" href="http://code.google.com/p/axon-auction-example/source/browse/trunk/auction-client-test/src/test/java/org/fuin/auction/client/test/CategoryUseCaseTest.java">Category Test</a> </li>
 <li>Don't forget to shutdown the local ActiveMQ, DerbyDB and SMTP Server using <a rel="nofollow" href="http://code.google.com/p/axon-auction-example/source/browse/trunk/auction-infrastructure/src/main/java/org/fuin/auction/infrastructure/InfrastructureStop.java">InfrastructureStop</a> class when you're finished - If you use Eclipse you can use this <a rel="nofollow" href="http://code.google.com/p/axon-auction-example/source/browse/trunk/auction-infrastructure/Stop+Auction+Infrastructure.launch">launch configuration</a> </li>
 </ol>
 
 <p>That's it for now... </p>
 
 </div>