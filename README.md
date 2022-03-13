# MLB Schedule API

## Description
Provides an API to get the schedule of games for a particular day with the user's favorite team
games showing first in the list.

#### Design Considerations
- Maintain same model/schema as upstream core MLB Schedule API, making it seamless for existing clients to use
- Provide the same flexibility as the core MLB Schedule API by still exposing inputs such as 'language', 'sportId'

#### Tech Stack
- Java
- Spring Boot
- Jersey (JAX-RS)

##### Infrastructure
- AWS Beanstalk

## API Endpoints

### Sport Schedule API
### /schedule

| Query Parameters | Required/Optional | Description                                                                                     |
|------------------|-------------------|-------------------------------------------------------------------------------------------------|
| sportId          | Required          | The unique identifier for a sport (i.e. MLB = 1)                                                |
| language         | Optional          | The language preferred in the response                                                          |
| date             | Optional          | Specifies the day for which to return game for. No games are returned if not provided.          |
| favoriteTeamId   | Optional          | The unique identifier for the users favorite team. The favorite team game(s) are listed first.  |

### /schedule/mlb 

| Query Parameters | Required/Optional | Description                                                                                     |
|------------------|-------------------|-------------------------------------------------------------------------------------------------|
| language         | Optional          | The language preferred in the response                                                          |
| date             | Optional          | Specifies the day for which to return game for. No games are returned if not provided.          |
| favoriteTeamId   | Optional          | The unique identifier for the users favorite team. The favorite team game(s) are listed first   |

Note: Inputs such as sportId and language follow the convention and functionality of the underlying
core MLB Schedule API.




## Developers

### Local Development

1. Clone repo
2. Run `mvn package` to generate the spring boot jar artifact
3. Start the application by running `java -jar <jar-file-location>`
4. Application should be running by default on port 8080 - open browser at "localhost:8080/api/schedule"