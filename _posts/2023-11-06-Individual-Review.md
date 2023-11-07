---
title: Trimester 1 Individual Review
description: My review of trimester 1
layout: post
comments: true
courses: {csa: {week: 12} }
type: plans
toc: true
---

## Collegeboard MC + Quiz Reflection
- [Link](https://soham360.github.io/APCSA//2023/11/05/MCQ2014-Reflection.html)
 
## End of trimester reflection
When I started this trimesters, I had no knowledge of Java. In fact, I didn't know there was a difference between Java and JavaScript. Over the trimester, I learned a lot and I can now read and understand it. It is very similar to the pseudocode that was used in last year's AP Computer Science Principles AP Exam. I've used Java a lot this trimester and I trying to force myself to do more work on the backend so I can gain a better understanding of the language. This trimester, our project was very simple but had numerous features so all of us to practice our Java skill individually. I've gained a much deeper understanding of the language and I feel like pushing myself to use it the next two trimesters like I did this trimester should help me be ready for the AP Exam and get a 5!

## Night At the Museum
- [Link](https://soham360.github.io/APCSA//2023/11/02/Night-At-The-Museum-Tri-1.html)

## Student Lesson Grades
- [Link](https://github.com/Soham360/APCSA/issues/5)

## Peer grades (what people graded my group)
- [Link](https://github.com/Soham360/sturdy-fiesta/issues/12#issuecomment-1792951045)

## Peer grades (what I graded other groups)
1. [Review 1](https://github.com/tuckergol/PassionProject7/issues/3#issuecomment-1792007044)
2. [Review 2](https://github.com/Cosmic-Carnage/Issues/issues/33#issuecomment-1792006424)
3. [Review 3](https://github.com/BobTheFarmer/VACTQ-Typing-Game/issues/10#issuecomment-1792006402)

## Meaningful Commits
### Weather Feature
#### Weather Frontend
- In the image below you can see that I am using a get request on `https://no-papels.stu.nighthawkcodingsociety.com/api/weather/daily` in line 70
- Then I access the second API in line 75
- From this second API I get the currentWeather, temperature, weatherConditions, and the precipitation
![image](https://github.com/Soham360/sturdy-fiesta/assets/111466950/c788fc48-6291-4543-8c37-807a8204b8ac)

#### Weather Backend
- I used the existing COVID API as a template
- The api we are using is actually 2 different APIs
- One is a API where we input the longitude and latitude of San Diego
- You get the other from the previous api and this one is more specific to the Del Norte Area
![image](https://github.com/Soham360/APCSA/assets/111466950/be633df7-33f9-4088-b829-fec00330de96)


### Announcements Feature
- Gets the existing announcements from the database using a GET Request
- Allows you to add to the database by using a POST Request
![image](https://github.com/Soham360/sturdy-fiesta/assets/111466950/5c911081-d72e-42a7-ab93-3abf952d3dca)

### CORs Error
![image](https://github.com/Soham360/sturdy-fiesta/assets/111466950/423350a1-13c7-4c01-9273-e39b85f143fb)
- I created a file called `CorsConfig.java` and I added this code in it. 
- It tells the application to allow requests from different websites (origins) to interact with it.
- It allows specific types of requests like getting information, sending new information, updating, and deleting.
- It allows any type of information to be sent in the request.
- It says not to send any sensitive information (credentials) along with the request.
![image](https://github.com/Soham360/sturdy-fiesta/assets/111466950/1aee2f39-5792-425e-8cd4-bc2bfad0f470)
- Added this line to the Weather API
- Allows requests from any origin and with any headers to access the API.

## Commit History

![]({{ site.baseurl }}/images/CommitTri1Frontend.png "Frontend Commits")
![]({{ site.baseurl }}/images/CommitTri1Backend.png "Backend Commits")

## Linkedin Post
- [Link](https://www.linkedin.com/feed/update/urn:li:activity:7127567401176485888/)