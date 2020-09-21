# Back-End  
​
## API: https://kickstarter-success-app.herokuapp.com/
​
### Table of Contents  
[Important Endpoints](#important-endpoints)

[Register and Login](#register-and-login)

[User](#user)

[Campaign](#campaign)

[Role](#role)
​
## IMPORTANT ENDPOINTS

Below is a list of the relevant endpoints that may be needed for the Kickstarter Success application. Further endpoints and details are provided below. 

| Type  | Endpoint                      | What it is used for                                    | What it returns                | Requires                                  | Authorized?  |
| :--:  | :----------:                  | :----------------------------------------------------: | :----------------------------: | :---------------------------------------: | :---------:  |
| POST  | /createnewuser                | Registering a new user                                 | token                          | **username** and **password**             | Anyone       |
| POST  | /login                        | Logging in a user, granting access to their info       | token                          | **username** and **password**             | Admin / User |
| GET   | /logout                       | Logging user out, so that they need to reinput creds   | Status OK                      | Token                                     | Admin / User |
| GET   | /users/myinfo                 | Getting a users info including their campaigns         | User object / Status OK        | Token                                     | Admin / User |
| PATCH | /users/user/{id}              | Edit partial user information                          | Status OK                      | Token / updated partial user object       | Admin / User |
| GET   | /campaigns/campaign/{id}      | Finding a specific campaign by its id                  | Campaign object / Status OK    | Token                                     | Admin / User |
| POST  | /campaigns/campaign           | Adds new campaign to database                          | Campaign / Status CREATED      | Token                                     | Admin / User |
| PUT   | /campaigns/campaign/{id}      | Replaces entire campaign                               | Campaign object / Status OK    | Token / updated campaign object           | Admin / User |
| PATCH | /campaigns/campaign/{id}      | Replaces part of a campaign                            | Campaign object / Status OK    | Token / updated part of campaign object   | Admin / User |
|DELETE | /campaigns/campaign/{id}      | Deletes a campaign                                     |                   Status OK    | Token                                     | Admin / User |

Postman Link: https://www.getpostman.com/collections/238a5f1cc7efbbf96e36​

## REGISTER AND LOGIN

Anyone can sign up as a new user by create a username and password. Doing so grants them an access token and creates a new user in the database. 

| Type  | Endpoint                      | What it is used for                                    | What it returns                | Requires                                  | Authorized?  |
| :--:  | :----------:                  | :----------------------------------------------------: | :----------------------------: | :---------------------------------------: | :---------:  |
| POST  | /createnewuser                | Registering a new user                                 |  Auth token                    | **username** and **password**             | Anyone       |
| POST  | /login                        | Logging in a user, granting access to their info       |  Auth token                    | **username** and **password**             | Admin / User |
| GET   | /logout                       | Logging user out, so that they need to reinput creds   | Status OK                      | Token                                     | Admin / User |

#### The login axios request should look like in order to utilize OAuth 2.0
​
```
axios.post('https://kickstarter-success-app.herokuapp.com/login', `grant_type=password&username=${credentials.username}&password=${credentials.password}`, {
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

In order to change any user information the request must come from an admin or the corresponding user to the id provided in the endpoint.  

| Type  | Endpoint                      | What it is used for                                    | What it returns                | Requires                                  | Authorized?  |
| :--:  | :----------:                  | :----------------------------------------------------: | :----------------------------: | :---------------------------------------: | :---------:  |
| GET   | /users/myinfo                 | Getting a users info including their campaigns         | User object / Status OK        | Token                                     | Admin / User |
| GET   | /users/users                  | Getting  a list of all users with all their info       | User objects / Status OK       | Token                                     | Admin        |
| GET   | /users/user/{id}              | Finding a user by their id                             | User object / Status OK        | Token                                     | Admin        |
| GET   | /users/user/name/{username}   | Find a user by their username                          | User object / Status OK        | Token                                     | Admin        |
| POST  | /users/user                   | Add a new user                                         | Status CREATED                 | Token                                     | Admin        |
| PUT   | /users/user/{id}              | Edit full user information                             | Status OK                      | Token / updated user object               | Admin / User |
| PATCH | /users/user/{id}              | Edit partial user information                          | Status OK                      | Token / updated part of user object       | Admin / User |
|DELETE | /users/user/{id}              | Deletes a user                                         | Status OK                      | Token                                     | Admin / User |


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


## CAMPAIGN

In order to change any campaign information the request must come from an admin or the corresponding user to the campaign id provided in the endpoint.  

| Type  | Endpoint                      | What it is used for                                    | What it returns                | Requires                                  | Authorized?  |
| :--:  | :----------:                  | :----------------------------------------------------: | :----------------------------: | :---------------------------------------: | :---------:  |
| GET   | /campaigns/all                | Getting a full list of campaigns created by all users  | Campaign objects / Status OK   | Token                                     | Admin        |
| GET   | /campaigns/campaign/{id}      | Finding a specific campaign by its id                  | Campaign object / Status OK    | Token                                     | Admin / User |
| POST  | /campaigns/campaign           | Adds new campaign to database                          | Campaign / Status CREATED      | Token                                     | Admin / User |
| PUT   | /campaigns/campaign/{id}      | Replaces entire campaign                               | Campaign object / Status OK    | Token / updated campaign object           | Admin / User |
| PATCH | /campaigns/campaign/{id}      | Replaces part of a campaign                            | Campaign object / Status OK    | Token / updated part of campaign object   | Admin / User |
|DELETE | /campaigns/campaign/{id}      | Deletes a campaign                                     |                   Status OK    | Token                                     | Admin / User |​

#### The campaign object structure
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

​
## ROLE

​Only users with an Admin role can make changes to other users roles. 

| Type  | Endpoint                      | What it is used for                                    | What it returns                | Requires                                  | Authorized?  |
| :--:  | :----------:                  | :----------------------------------------------------: | :----------------------------: | :---------------------------------------: | :---------:  |
| GET   | /roles/roles                  | Getting a list of roles                                | Role objects                   | Token                                     | Admin        |
| GET   | /roles/role/{id}              | Find a specific role by id                             | Role object                    | Token                                     | Admin        |
| POST  | /roles/role                   | Adds new role to database and returns                  | Status CREATED                 | Token / full role object                  | Admin        |
| PUT   | /roles/role/{id}              | Edit a role                                            | The roleid and Status OK       | Token / full role object                  | Admin        |

#### The role object is of a structure
```
{
    "roleid": 1,
    "name": "admin",
    "users": []
}
```
