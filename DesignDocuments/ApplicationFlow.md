# Application Flow

### User Searches for Coverage by Tournament
1. User selects to search for coverage by Tournament Name.
1. User enters the Tournament Name to search for.
1. User (optionally) enters the year they want that tournament to be from.
1. Request is sent to servlet.
1. Servlet queries the database for tournaments matching the user's search parameters.
1. Servlet assigns attribute for the search result.
1. User is directed to JSP displaying search results. 
1. User selects tournament they are looking for coverage for.
1. Request is sent to servlet.
1. Servlet queries database for information on selected tournament.
1. User is directed to JSP displaying tournament information.

### User Searches for Coverage by Player
1. User selects to search for coverage by player.
1. User enters the player's name they are searching for.
1. Request is sent to servlet.
1. Servlet queries the database for players matching the user's search parameters.
1. Servlet assigns value of query to attribute of request.
1. User is directed to JSP displaying search results.
1. User selects player.
1. Request is sent to servlet.
1. Servlet queries the database for selected player information.
1. Player information is assigned to attribute of request.
1. User is redirected to JSP displaying player profile.

### User Searches for Coverage by Course
1. User selects to search for coverage by course.
1. User (optionally) enters the course's name they are searching for.
1. User (optionally) enters course location information.
1. Request is sent to servlet.
1. Servlet queries the database for courses matching the user's search parameters.
1. Servlet assigns value of query to attribute of request.
1. User is directed to JSP displaying search results.
1. User selects course.
1. Request is sent to servlet.
1. Servlet queries the database for selected course information.
1. Course information is assigned to attribute of request.
1. User is redirected to JSP displaying course profile.

### User Selects Round of Coverage to View
1. User selects a round of coverage from one of the above 3 flows to view.
1. Request is sent to servlet.
1. Servlet queries database for coverage information.
1. User is directed to JSP to view the coverage.
1. Javascript uses Youtube API to load video player.
1. Javascript makes video player useable to User.

### User Sign In
1. User selects Log In from navigation menu.
1. User is directed to Login JSP.
1. Request is sent to Servlet.
1. If servlet authenticates login information, user will be redirected to previous page they were viewing.
1. If authentication fail, user will be sent back to login JSP with error message.

### User Favorites/Unfavorites Player/Coverage Round/Tournament
1. User selects favorite button.
1. Request is sent (asynchronously) to servlet.
1. If favoriting, servlet saves data into appropriate database table.
1. If unfavoriting, servlet deletes data from appropriate database table.
1. Servlet sends response back to webpage.
1. If state change successful, Javascript toggles button to opposite state.

### User Reports New Coverage
1. User selects from navigation bar to report potential new coverage.
1. User is directed to JSP for new coverage request.
1. User fills out form with as much information as they wish and submits form.
1. Request is sent to servlet.
1. Servlet creates a request object.
1. Servlet saves request object  to database.
1. On successful submission, user is directed to confirmation page.

### Admin Adds new Player/Tournament/Round Coverage
1. Admin selects to add new Player/Tournament/Round to site.
1. Admin is directed to JSP with form for adding selected object.
1. Admin fills out form and submits.
1. Request is sent to servlet.
1. Servlet creates object of selected type.
1. Servlet saves object to database.
1. Admin is redirected back to form JSP with either confirmation or error message.
