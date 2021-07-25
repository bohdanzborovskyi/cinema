For launch cinema REST API project you must only make double click on cinema-0.0.1-SNAPSHOT.jar file.
After that application is started (with uploaded data to embedded db) and you can make rest requests for cinema service.
Request services are available on localhost:8081:
1) /getAvailabbleMovies - return all available in cinema movies;
2) /getMovieDate?movie=Avatar - return all available sessions for Avatar movie;
3) /selectMovie?sessionID=11&seat=8&ticket=student&user_surname=Grzmilą&user_name=Pakk - create reservation for session under 11 id,
ticket type is student, seat is number 8 and surname and name of user are - Pakk Grzmilą.
4) /getSessionDetails?sessionID=10 - return all details of session with id number 10: name of screening room, 
status of seats (free or reservated) and date of session.
For launching and watch all performed request, you can open cmd in derictory with cinema-0.0.1-SNAPSHOT.jar and write in console:
java -jar cinema-0.0.1-SNAPSHOT.jar.