# Basic Quiz 后端需求说明

开发一个 Spring Boot Web Application，提供 API 供前端 app 调用。

技术要求如下：

* 请统一使用默认的 8080 端口，无需使用 HTTPS；
* 需编写尽可能完善的自动化测试；
* 数据库使用 MySQL 5.7，测试数据库使用 H2。均使用默认端口；
* 不需要实现为 HATEOAS；

在最终提交前，请使用 `curl` 或 `Postman` 进行充分测试，并与自己实现的前端 app 进行联调，确保整体工作正常。

## 术语表

| 中文          | 英文               |
| ------------------ | ------------------ |
| 讲师            | trainer               |
| 学员        | trainee             |
| 组           | group            |

## API 列表

本 Quiz 后端 app 共需提供以下 API：

* 查询所有未分组学员
* 添加新学员
* 删除学员
* 查询所有未分组讲师
* 添加新讲师
* 删除讲师
* 查询所有分组
* 自动随机分组讲师和学员
* 重命名分组

### 查询所有未分组学员

只返回未被分组的学员，已被分配到 group 的学员不会出现在此 API 的返回结果中。

#### ENDPOINT

GET /trainees?grouped=false

#### RESPONSE

##### Status

成功时返回 200 OK。

其它情况请根据 REST API Design 自行选择合理的实现。

##### Body

查询成功会返回 trainee 的 list，如果没有未分组的学员，则返回 `[]`：

| 字段:类型          | 说明               |
| ------------------ | ------------------ |
| id:long            | ID。               |
| name:string        | 名字。             |
| office:string        | 办公室。             |
| email:string        | 邮箱地址。             |
| zoomId:string        | 个人 Zoom Meeting ID。             |

#### EXAMPLE

```shell
$ curl 'localhost:8080/trainees?grouped=false'
[
    {
        "id": 1,
        "name": "Foo",
        "office": "西安",
        "email": "foo@thoughtworks.com",
        "zoomId": "foo"
    },
    {
        "id": 2,
        "name": "Bar",
        "office": "北京",
        "email": "bar@thoughtworks.com",
        "zoomId": "bar"
    }
]
```

### 添加新学员

#### ENDPOINT

POST /trainees

#### REQUEST

| 字段:类型      | 校验要求 | 说明               |
| --------------|---- | ------------------ |
| name:string   | 非空 | 名字。             |
| office:string | 非空 | 办公室。             |
| email:string  | 非空且为合法邮箱地址 | 邮箱地址。|
| zoomId:string | 非空 | 个人 Zoom Meeting ID。 |

#### RESPONSE

##### Status

成功时返回 201 Created。

校验失败时返回 400 Bad Request。

其它情况请根据 REST API Design 自行选择合理的实现。

##### Body

创建成功的 trainee 资源。

| 字段:类型      | 说明               |
| --------------|------------------ |
| id:long       | ID。               |
| name:string   | 名字。             |
| office:string | 办公室。             |
| email:string  | 邮箱地址。          |
| zoomId:string | 个人 Zoom Meeting ID。 |

#### EXAMPLE

```shell
$ curl -v -H "Content-Type: application/json" --data @trainee.json localhost:8080/trainees
{
    "id": 11,
    "name": "Foo",
    "office": "西安",
    "email": "foo@thoughtworks.com",
    "zoomId": "foo"
}
```

### 删除学员

#### ENDPOINT

DELETE /trainees/{trainee_id}

#### REQUEST

| 字段:类型      | 说明                |
| --------------| ------------------ |
| trainee_id:long | Trainee ID。     |

#### RESPONSE

##### Status

成功时返回 204 No Content。

`{trainee_id}` 不存在时返回 404 Not Found。

其它情况请根据 REST API Design 自行选择合理的实现。

#### EXAMPLE

```shell
$ curl -X DELETE localhost:8080/trainees/11
```

### 查询所有未分组讲师

只返回未被分组的讲师，已被分配到 group 的讲师不会出现在此 API 的返回结果中。

#### ENDPOINT

GET /trainers?grouped=false

#### RESPONSE

##### Status

成功时返回 200 OK。

其它情况请根据 REST API Design 自行选择合理的实现。

##### Body

查询成功会返回 trainer 的 list，如果没有未分组的讲师，则返回 `[]`：

| 字段:类型          | 说明               |
| ------------------ | ------------------ |
| id:long            | ID。               |
| name:string        | 名字。             |

#### EXAMPLE

```shell
$ curl 'localhost:8080/trainers?grouped=false'
[
    {
        "id": 1,
        "name": "Fizz"
    },
    {
        "id": 2,
        "name": "Buzz"
    }
]
```

### 添加新讲师

#### ENDPOINT

POST /trainers

#### REQUEST

| 字段:类型      | 校验要求 | 说明            |
| --------------|---- | ------------------ |
| name:string   | 非空 | 名字。              |

#### RESPONSE

##### Status

成功时返回 201 Created。

校验失败时返回 400 Bad Request。

其它情况请根据 REST API Design 自行选择合理的实现。

##### Body

创建成功的 trainer 资源。

| 字段:类型      | 说明               |
| --------------|------------------- |
| id:long       | ID。               |
| name:string   | 名字。             |

#### EXAMPLE

```shell
$ curl -v -H "Content-Type: application/json" --data @trainer.json localhost:8080/trainers
{
    "id": 1,
    "name": "Fizz"
}
```

### 删除讲师

#### ENDPOINT

DELETE /trainers/{trainer_id}

#### REQUEST

| 字段:类型      | 说明                |
| --------------| ------------------ |
| trainer_id:long | Trainer ID。     |

#### RESPONSE

##### Status

成功时返回 204 No Content。

`{trainer_id}` 不存在时返回 404 Not Found。

其它情况请根据 REST API Design 自行选择合理的实现。

#### EXAMPLE

```shell
$ curl -X DELETE localhost:8080/trainers/1
```

### 查询所有分组

#### ENDPOINT

GET /groups

#### RESPONSE

##### Status

成功时返回 200 Ok。

其它情况请根据 REST API Design 自行选择合理的实现。

##### Body

查询成功会返回 group 的 list，如果没有分组，则返回 `[]`：

| 字段:类型      | 说明               |
| --------------|------------------- |
| id:long       | ID。               |
| name:string   | 分组名字。           |
| trainers:list | 分组内包含的讲师列表。 |
| trainees:list | 分组内包含的学员列表。 |

#### EXAMPLE

```shell
$ curl localhost:8080/groups
[
    {
        "id": 1,
        "name": "1 组",
        "trainers": [
            {
                "id": 5,
                "name": "张巍"
            },
            {
                "id": 1,
                "name": "张钊"
            }
        ],
        "trainees": [
            {
                "id": 1,
                "name": "Foo",
                "office": "西安",
                "email": "foo@thoughtworks.com",
                "zoomId": "foo"
            }
        ]
    },
    {
        "id": 2,
        "name": "2 组",
        "trainers": [
            {
                "id": 2,
                "name": "朱玉前"
            },
            {
                "id": 3,
                "name": "贵溪京"
            }
        ],
        "trainees": [
            {
                "id": 2,
                "name": "Bar",
                "office": "北京",
                "email": "bar@thoughtworks.com",
                "zoomId": "bar"
            }
        ]
    }
]
```

### 自动随机分组讲师和学员

#### ENDPOINT

POST /groups/auto-grouping

#### RESPONSE

##### Status

成功时返回 200 Ok。

不满足分组条件（比如：讲师人数小于 2 人）时返回 400 Bad Request。

其它情况请根据 REST API Design 自行选择合理的实现。

##### Body

分组成功会返回 group 的 list：

| 字段:类型      | 说明               |
| --------------|------------------- |
| id:long       | ID。               |
| name:string   | 分组名字。           |
| trainers:list | 分组内包含的讲师列表。 |
| trainees:list | 分组内包含的学员列表。 |

#### EXAMPLE

```shell
$ curl -X POST localhost:8080/groups/auto-grouping
[
    {
        "id": 1,
        "name": "1 组",
        "trainers": [
            {
                "id": 5,
                "name": "张巍"
            },
            {
                "id": 1,
                "name": "张钊"
            }
        ],
        "trainees": [
            {
                "id": 1,
                "name": "Foo",
                "office": "西安",
                "email": "foo@thoughtworks.com",
                "zoomId": "foo"
            }
        ]
    },
    {
        "id": 2,
        "name": "2 组",
        "trainers": [
            {
                "id": 2,
                "name": "朱玉前"
            },
            {
                "id": 3,
                "name": "贵溪京"
            }
        ],
        "trainees": [
            {
                "id": 2,
                "name": "Bar",
                "office": "北京",
                "email": "bar@thoughtworks.com",
                "zoomId": "bar"
            }
        ]
    }
]
```

### 重命名分组

#### ENDPOINT

PATCH /groups/{group_id}

#### REQUEST

| 字段:类型      | 校验要求     | 说明                |
| ------------- | ----------- | ------------------ |
| name:string   | 非空，且不重复 | 新的分组名称。        |

#### RESPONSE

##### Status

成功时返回 200 Ok。

`{group_id}` 不存在时返回 404 Not Found。

新名称与已有分组名称重复时返回 400 Bad Request。

其它情况请根据 REST API Design 自行选择合理的实现。

##### Body

无返回数据。

#### EXAMPLE

```shell
$ curl -X PATCH --data '{"name": "new name"}' -H "Content-Type: application/json" localhost:8080/groups/{group_id}
```

## 返回结果说明

使用标准 HTTP Status Code 来标识返回结果成功或失败与否。

本 quiz 允许使用的 status code 范围如下表：

| HTTP Status Code    | Summary                                                  |
| ------------------- | -------------------------------------------------------- |
| 200 - OK            | 查询操作一切正常，返回 200 及查询结果。                  |
| 201 - Created       | 创建操作成功，返回 201。                                 |
| 204 - No Content    | 用于 DELETE 或 某些 POST 等操作无返回数据时。                        |
| 400 - Bad Request   | 请求参数不符合要求，通常是因为参数格式不正确或参数缺失。 |
| 404 - Not Found     | 请求的资源不存在。                                       |
| 500 - Server Errors | 请求在处理时遇到服务器错误。                                   |

除了返回对应的 status code 外，对于出错的情况，还需返回 Error 对象，字段如下：

| 字段:类型        | 说明                                                         |
| ---------------- | ------------------------------------------------------------ |
| message:string   | 错误提示信息，内容可以自行编写，表意即可。如：Cannot find basic info of person with id is 666. |
| details:map      | 字段校验失败时，需要将出错的字段及其错误信息通过该字段提供。其它情况下该字段不必返回。|

一个示例如下：

```shell
curl -v --data '{"name": "Foo"}' -H "Content-Type: application/json" localhost:8080/trainees                                                                                                                                                                              ░▒▓ 100%  ▓▒░
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> POST /trainees HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.64.1
> Accept: */*
> Content-Type: application/json
> Content-Length: 15
>
* upload completely sent off: 15 out of 15 bytes
< HTTP/1.1 400
< Vary: Origin
< Vary: Access-Control-Request-Method
< Vary: Access-Control-Request-Headers
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Thu, 24 Sep 2020 00:14:02 GMT
< Connection: close
<
* Closing connection 0
{"message":"Invalid values.","details":{"zoomId":"Zoom ID is mandatory","office":"Office is mandatory","email":"Email is mandatory"}}
```
