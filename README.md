# skillen-nlp

Microservice with NLP part of there project

Entry point:
[http://localhost:9000/static/index.html](http://localhost:9000/static/index.html)

### API

#### listBooks:

```http
$ http localhost:9000/api/texts

HTTP/1.1 200 OK
Content-Length: 108
Content-Type: application/json
Date: Sat, 10 Dec 2016 05:01:41 GMT
Server: akka-http/10.0.0

[
    {
        "text": "",
        "title": "1dsdgadshad",
        "url": "Dasdasd"
    },
    {
        "text": "",
        "title": "1dsdgadsfdafhad",
        "url": "Dasdasd222"
    }
]
```

#### loadText:

$ http localhost:9000/api/workbench

```http
HTTP/1.1 200 OK
Content-Length: 120
Content-Type: application/json
Date: Sat, 10 Dec 2016 05:13:35 GMT
Server: akka-http/10.0.0

{
    "caption": "dsadsada",
    "text": [
        {
            "c": "false",
            "t": "zz1",
            "w": "1"
        },
        "dsadsa 12 1 ",
        {
            "c": "false",
            "t": "zz1",
            "w": "4"
        },
        " dsadsa"
    ]
}
```

