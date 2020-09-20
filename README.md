# Back-End  
​
## API: TODO - ADD DEPLOYED SITE 
​
### Table of Contents  
[Important Endpoints](#important-endpoints)
[Register and Login](#register-and-login)  
[User](#user)  
[Campaign](#campaign)  
[Role](#role)
​
## IMPORTANT ENDPOINTS

| Type  | Endpoint                      | What it is used for                                    | What it returns                | Requires                                  | Authorized?  |
| :--:  | :----------:                  | :----------------------------------------------------: | :----------------------------: | :---------------------------------------: | :---------:  |
| POST  | /createnewuser                | Registering a new user                                 | token                          | **username** and **password**             | Anyone       |
| POST  | /login                        | Logging in a user, granting access to their info       | token                          | **username** and **password**             | Admin / User |
| GET   | /logout                       | Logging user out, so that they need to reinput creds   | Status OK                      | token                                     | Admin / User |
| GET   | /users/myinfo                 | Getting a users info including their campaigns         | User object / Status OK        | Token                                     | Admin / User |
| PATCH | /users/user/{id}              | Edit partial user information                          | Status OK                      | Token / updated partial user object       | Admin / User |


| Type   | Endpoint             | Requires                                     | Returns                   |
| :----: | :------------------: | :------------------------------------------: | :-----------------------: |
| GET    | /plants/myplants     | token                                        | user's plants             |
| POST   | /plants/water/{id}   | token                                        | new lastwatered date      |
| POST   | /plants/myplants/add | full plant object without user, token        | CREATED status            |
| PUT    | /plants/plant/{id}   | full plant object without user, token        | CREATED status            |
| PATCH  | /plants/plant/{id}   | part of plant object, token                  | CREATED status            |
| DELETE | /plants/plant/{id}   | token                                        | OK status                 |
​
## REGISTER AND LOGIN  
| Type  | Endpoint                      | What it is used for                                    | What it returns                | Requires                                  | Authorized?  |
| :--:  | :----------:                  | :----------------------------------------------------: | :----------------------------: | :---------------------------------------: | :---------:  |
| POST  | /createnewuser                | Registering a new user                                 | token                          | **username** and **password**             | Anyone       |
| POST  | /login                        | Logging in a user, granting access to their info       | token                          | **username** and **password**             | Admin / User |
| GET   | /logout                       | Logging user out, so that they need to reinput creds   | Status OK                      | token                                     | Admin / User |

#### The login axios request should look like in order to utilize OAuth 2.0
​
```
axios.post('http://{myherokuurl}/login', `grant_type=password&username=${credentials.username}&password=${credentials.password}`, {
      headers: {
        // btoa is converting our client id/client secret into base64
        Authorization: `Basic ${btoa('lambda-client:lambda-secret')}`,
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    })
```
​
#### When registering a user the minimum required is
```
{
    "username": "someusername",
    "password": "somepassword",
}
```
​
## USER  
In order to change any user information the request must come from an admin or  
the corresponding user to the id provided in the endpoint.  
​
#### The user object is of a structure
```
{
    "userid": 3,
    "username": "admin",
    "password": "somepassword",
    "campaigns": [],
    "roles": []
}

```
​
| Type  | Endpoint                      | What it is used for                                    | What it returns                | Requires                                  | Authorized?  |
| :--:  | :----------:                  | :----------------------------------------------------: | :----------------------------: | :---------------------------------------: | :---------:  |
| GET   | /users/myinfo                 | Getting a users info including their campaigns         | User object / Status OK        | Token                                     | Admin / User |
| GET   | /users/users                  | Getting  a list of all users with all their info       | User objects / Status OK       | Token                                     | Admin        |
| GET   | /users/user/{id}              | Finding a user by their id                             | User object / Status OK        | Token                                     | Admin        |
| GET   | /users/user/name/{username}   | Find a user by their username                          | User object / Status OK        | Token                                     | Admin        |
| POST  | /users/user                   | Add a new user                                         | Status CREATED                 | Token                                     | Admin        |
| PUT   | /users/user/{id}              | Edit full user information                             | Status OK                      | Token / updated user object               | Admin / User |
| PATCH | /users/user/{id}              | Edit partial user information                          | Status OK                      | Token / updated partial user object       | Admin / User |
|DELETE | /users/user/{id}              | Deletes a user                                         | Status OK                      | Token                                     | Admin / User |
​
## CAMPAIGN
In order to change any campaign information the request must come from an admin or  
the corresponding user to the campaign id provided in the endpoint.  
​
#### The plant object is of a structure
```
{
    "campaignid": 4,
    "name": "Magic Flying Socks",
    "category": "Footwear",
    "goal": 25000,
    "currency": "USD",
    "launchdate": 20-09-2020, 
    "prediction": true,
    "user": {}
}
```
| Type  | Endpoint          | What it does                                               | Requires                                  |
| :--:  | :----------:      | :----------------------------:                             | :---------------------------------------: |
| GET   | /plants/plants    | Returns full list of plants                                | Token and Admin role                      |
| GET   |/plants/plant/{id} | Returns specific plant by id                               | Token and Admin role                      |
| GET   | /plants/myplants  | Returns current user's plants                              | Token                                     |
| POST  | /plants/plant     | Adds new plant to database and returns status of CREATED   | Token and plant object                    |
| PUT   |/plants/plant/{id} | Replaces entire plant by id and returns status of CREATED  | Token and plant object                    |
| PATCH |/plants/plant/{id} | Replaces part of plant object and returns status of CREATED| Token and plant object                    |
|DELETE |/plants/plant/{id} | Deletes plant by id and returns status of OK               | Token                                     |
​
## ROLE
​Only users with an Admin role can make changes to other users roles. 

#### The role object is of a structure
```
{
    "roleid": 1,
    "name": "admin",
    "users": []
}
```
| Type  | Endpoint                      | What it is used for                                    | What it returns                | Requires                                  | Authorized?  |
| :--:  | :----------:                  | :----------------------------------------------------: | :----------------------------: | :---------------------------------------: | :---------:  |
| GET   | /roles/roles                  | Getting a list of roles                                | Role objects                   | Token                                     | Admin        |
| GET   | /roles/role/{id}              | Find a specific role by id                             | Role object                    | Token                                     | Admin        |
| POST  | /roles/role                   | Adds new role to database and returns                  | Status CREATED                 | Token / full role object                  | Admin        |
| PUT   | /roles/role/{id}              | Edit a role                                            | The roleid and Status OK       | Token / full role object                  | Admin        |