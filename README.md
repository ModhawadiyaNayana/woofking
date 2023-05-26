Woofking

Step 1:
install docker using this li[](https://docs.docker.com/get-docker/)nk

Step 2:

run this command to build image:   docker build -t notificaton .

Step 3:

run this command to build and run container:   docker run -p8090:8081 notification

Step 4:

run below URL to test get supervisors api in postman with get request:  
http://localhost:8090/api/supervisors

run below URL to test submit api in postman with post request:  
http://localhost:8090/api/submit

Sample Body of postman request:  
{
"firstName": "John",
"lastName": "Doe",
"phoneNumber": "1234567890",
"email":"john@abc.co",
"supervisor": "Jane Smith"
}

you can change body to test all validation




