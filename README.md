# Android Socail Network Application

SociNet is a social netwrok demonstration project with MVVM Design Structure by using RXJava2 with LiveData and Repository Patterns. It contains a user list page and detail page which shows the information fetched from network.

From this example, we use the APIs provide by [JSON Placeholder](https://jsonplaceholder.typicode.com/), which will return the following result: (We take User List as example)
```
[
  {
    "id": 1,
    "name": "Leanne Graham",
    "username": "Bret",
    "email": "Sincere@april.biz",
    "address": {
      "street": "Kulas Light",
      "suite": "Apt. 556",
      "city": "Gwenborough",
      "zipcode": "92998-3874",
      "geo": {
        "lat": "-37.3159",
        "lng": "81.1496"
      }
    },
    "phone": "1-770-736-8031 x56442",
    "website": "hildegard.org",
    "company": {
      "name": "Romaguera-Crona",
      "catchPhrase": "Multi-layered client-server neural-net",
      "bs": "harness real-time e-markets"
    }
  },
  ...
]
```

# Project Architechure
In the project, we used MVVM design pattern with Google Android Architecture Components (AAC). The application used RXJava2 to perform data binding. With Android Architecture Lifecycle, it observes and monitor the network data fetched from Fuel Library.

The following diagram shows the flow how it works in the project:

<p float="left">
  <img src="https://github.com/sunnytse0326/SociNet/blob/master/screenshot/structure.png">
</p>



# Implementation
We will have a user list page which displays name, email and phone information:
<p float="left">
  <img src="https://github.com/sunnytse0326/SociNet/blob/master/screenshot/user_list.png">
</p>

It provides to view user detail information or main content of this application:
<p float="left">
  <img src="https://github.com/sunnytse0326/SociNet/blob/master/screenshot/user_detail.png">
  <img src="https://github.com/sunnytse0326/SociNet/blob/master/screenshot/album_list.png">
</p>

For the main content, user could be able to view album photos, post and todo lists:
<p float="left">
  <img src="https://github.com/sunnytse0326/SociNet/blob/master/screenshot/album_list.png">
  <img src="https://github.com/sunnytse0326/SociNet/blob/master/screenshot/post_list.png">
  <img src="https://github.com/sunnytse0326/SociNet/blob/master/screenshot/todo_list.png">
</p>


# Library used:
Anko (Layout Design)
Fuel (Network Library)
RxJava2 (Data binding)
Picasso (Image Library)



