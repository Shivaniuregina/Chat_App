# Chat_App
Real-Time Firebase Chat Application

<b> Path to MainActivity: Chat_App/app/src/main/java/com/example/chatapp/ <b>

<h2>
Background:</h2>
<p>
Every major application out in the market in each genre like gaming, news, health and fitness, education, shopping/e-commerce uses a chat feature one or the other way as in games like “Clash of Clans”, “Clash Royale” where a group of players can chat with each other. Apps like “Nike Training Club”, “Endomondo” in health and fitness makes use of this feature to connect with the fitness expert or a dietician when necessary. Similarly, e-commerce websites like “Wish.com”, “Kijiji.ca”, “Amazon.com” uses this feature to connect directly to their customers and provide good and fast customer service. So the idea is to design an app that can fit all the genre and apps like this can use one commonly designed application by integrating into their mobile apps. Therefore, the proposed idea will help users to expect a user-friendly and consistent design for the chat room, especially where real-time response is a necessity to hype the customer’s interest in your website/ service or anything else you want to do. Real-time text preview helps to build customers' trust as using this instant feedback can be provided and problems or site-errors can be solved easily. </p>

<h2> Motivation: </h2>
<p>
The idea is to design a system/app like this came from using different apps for a different purpose and all of them have a variety of front-end designs which makes the application use complex. There are various chat app options available in the market but they do not serve the purpose of integrating into another app to make better use of it. My motivation is to provide the user with a common interface and features available to use in any application which can reduce the complexity for the user and give better user experience. In this way, the poor interface of chat application in many apps can be improved and few of the applications can integrate this chat app to give the user a better service.
</p>

<h2>Project description:</h2>

<p>In this chat application, the focus will be on the user interface and the common usability requirements of the user in a chat application such as sending and receiving a text message, sharing images and some other which is a must to have into a chat application so that it becomes easy to integrate anywhere anytime in any application.</p>
<img src="https://github.com/Shivaniuregina/Chat_App/blob/master/olduser.png" >
<p> <b> List of features in the chat application: </b> </P>
<p>
 <b>Functional Features:</b>
<ul>
<li> Sign in and set up a profile: Users will be able to register their profiles and add other users to chat with them. 
 </li> <li> Add new contacts: The user is able to add the new contact who are registered for the application to join a chat conversation.</li> 
<li> Send message: The users are able to send an instant message to the other user available in the contact list. Users can send any type of message including audio messages, videos, and images.</li>
 <li> Add Bio: Users can add a bio or short description for their profiles.</li>
</ul>
</P>
<p>
<b>Non-Functional Features:</b>
<ul>
 <li>	Privacy: Messages shared between users can be shared privately to keep privacy.  </li>
 <li>	Performance: The application must be lightweight and must be able to send messages instantly.  </li>
 <li>	Robustness:  When the mobile device crashes the data should be available on the firebase server to recover. </li>
 <li>	Correctness: The account should display correct information when the user logs in with appropriate details.  </li>
 </ul>
 </p>

<p> 
 <b>Backend and Database:</b>

The backend is done in kotlin and the database to store the data on the server is the firebase realtime database which helps to store data in real-time even when the users are offline and synchronize the data. It can improve the stability issues and report to bug fixes with faster pace and ease.
 </p>
 
<p>
<b>About application:</b>

This app will run on all android phones with the android version 7.0 or newer that includes google services. The application will run in the portrait mode and will maintain a similar interface in the app in all forms.
</p>
<p>
 <b> Software Requirements:</b>
 
 Android studio version 3.5 and Genymotion for android emulator.</p>
<p>
<b> Scope of the project: </b>
<ul>
 <li>	Developing a chat application to communicate among a different number of users based on point-to-point communication.  </li>
 <li>	The facility which can provide is to provide text and audio communication along with sharing images and files in real-time.  </li>
 <li>	Companies can use this application to integrate it into different applications such as games, fitness and so on.  </li>
 <li>	Firebase provides NoSQL document database facility.  </li>
</ul>
</p>
<p>
<b>Development process: </b>
<ul>
In this phase the process to develop the chat application is focused by the following steps:
 <li>	Discover the requirements of the users. </li>
 <li>	Determine the features and its architecture.  </li>
 <li>	Develop the design to implement the features into the application with the highest usability for great user experience.  </li>
 <li>	Quality Assurance to test the app in all the possible functionality.  </li>
 <li> Launch the application for the users.
</ul>
<p>
<b> Working of the chat application: </b>

The chat application gives the ability to communicate with anyone in realtime by sharing text, audio and video messages it has the ability to store the data using FCM  (Firebase Cloud Messaging) and can recover the data if needed this follows the NoSQL documents to process the data. Firebase provides faster web hosting and multi-platform authentication. This application will provide the universal requirement for a chat app to include with its maximum usability. It may include the file transfer feature where the user can share any documents up to a certain limit. Once the user will be logged in to the application with correct credentials they can add any number of contacts who are currently using the application and will be able to communicate with them by sharing messages in any format. Users will receive a notification if they get any new messages from other users. The users can set and update the profile and bio of their accounts whenever they want. 
</p>

