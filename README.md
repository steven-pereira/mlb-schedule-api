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

#### Infrastructure
- AWS Beanstalk

## API Endpoints

### Sport Schedule API
### /api/schedule
Generic schedule API which requires a sportId to indicate the sport. Note this follows the same convention
as the underlying core MLB Schedule API.

| Query Parameters | Required/Optional | Description                                                                                     |
|------------------|-------------------|-------------------------------------------------------------------------------------------------|
| sportId          | Required          | The unique identifier for a sport (i.e. MLB = 1)                                                |
| language         | Optional          | The language preferred in the response                                                          |
| date             | Optional          | Specifies the day for which to return game for. No games are returned if not provided.          |
| favoriteTeamId   | Optional          | The unique identifier for the users favorite team. The favorite team game(s) are listed first.  |

### /api/schedule/mlb 
MLB specific endpoint, where sportId is not required since it automatically defaults to MLB (sportId=1)

| Query Parameters | Required/Optional | Description                                                                                     |
|------------------|-------------------|-------------------------------------------------------------------------------------------------|
| language         | Optional          | The language preferred in the response                                                          |
| date             | Optional          | Specifies the day for which to return game for. No games are returned if not provided.          |
| favoriteTeamId   | Optional          | The unique identifier for the users favorite team. The favorite team game(s) are listed first   |

Note: Inputs such as sportId and language follow the convention and functionality of the underlying
core MLB Schedule API.


## Developers
### Servers
| Env | Host                                                   | Description                                         |
|-----|--------------------------------------------------------|-----------------------------------------------------|
| Dev | http://mlb-schedule-api.us-east-1.elasticbeanstalk.com | Running on AWS Beanstalk<br/> Note: SSL not enabled |

#### Examples
| Use Case                                                 | Sample URL                                                                                                     |
|----------------------------------------------------------|----------------------------------------------------------------------------------------------------------------|
| Get MLB Games for 2021-09-11, favorite team is Yankees   | http://www.mlb-schedule-api.us-east-1.elasticbeanstalk.com/api/schedule/mlb?date=2021-09-11&favoriteTeamId=147 |
| Get MLB Games for 2021-09-11, favorite team is Blue Jays | http://www.mlb-schedule-api.us-east-1.elasticbeanstalk.com/api/schedule/mlb?date=2021-09-11&favoriteTeamId=141 |

### Local Development

1. Clone repo
2. Run `mvn package` to generate the spring boot jar artifact
3. Start the application by running `java -jar <jar-file-location>`
4. Application should be running by default on port 8080 - open browser at "localhost:8080/api/schedule"