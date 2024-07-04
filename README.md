# healthcare

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- [JDK 22](https://www.oracle.com/java/technologies/javase/jdk22-archive-downloads.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.vignesh.healthcare.HealthcareApplication` class from your IDE.

Alternatively

```shell
mvn spring-boot:run
```

## Postman collection for API End Points

Download and install [postman](https://www.postman.com/downloads/) in your system. Import the collections by copying the following JSON.

```json
{
	"info": {
		"_postman_id": "6af43660-a9d8-480d-b65e-01bf2f0f988a",
		"name": "Health Care",
		"schema": "https://schema.getpostman.com/json/collection/v2.0.0/collection.json",
		"_exporter_id": "36752091"
	},
	"item": [
		{
			"name": "Patient",
			"item": [
				{
					"name": "create_patient",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"name\" : \"John\",\n    \"age\" : 32,\n    \"email\" : \"john.a@outlook.com\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": "localhost:8080/api/patient"
					},
					"response": []
				},
				{
					"name": "get_patient",
					"request": {
						"method": "GET",
						"header": [],
						"url": "localhost:8080/api/patient/1"
					},
					"response": []
				}
			]
		},
		{
			"name": "Appointments",
			"item": [
				{
					"name": "book_appointment",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"patientId\" : 1,\n    \"doctorName\" : \"Steve\",\n    \"appointmentTime\" : \"22-July-2024\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": "localhost:8080/api/appointments"
					},
					"response": []
				},
				{
					"name": "get_appointment",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "localhost:8080/api/appointments?patientId=1",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"appointments"
							],
							"query": [
								{
									"key": "patientId",
									"value": "1"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "reschedule_appointment",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"patiendId\" : 1,\n    \"doctorName\" : \"Tony\",\n    \"appointmentTime\" : \"20-Dec-2024\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": "localhost:8080/api/appointments/1"
					},
					"response": []
				},
				{
					"name": "cancel_appointment",
					"request": {
						"method": "DELETE",
						"header": [],
						"url": "localhost:8080/api/appointments/1"
					},
					"response": []
				}
			]
		}
	]
}
```
