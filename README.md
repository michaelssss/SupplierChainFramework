# 接口列表

## 约定

* 除了登录接口外，所有的请求需要在header中放置token

* token的正确性由服务端保证

* 所有请求都采用post

* 未提及的功能请求均可在http://host/swagger-ui.html中查询

## 登陆

* 说明

> 若登陆成功则返回token，否则token为空字符串  
token过期时间，若前端传空字符串则为当前时间延后七天

* 例子

path: /User/login

```json
request:
{
    "username":"", //String
    "password":"", //String
    "outdate":1234 //时间戳,Timestamp
}
```

```json
response:
{
    "status": "OK",
    "result": {
        "token": "42047fa6-600c-433b-8ec1-331250c81f47",
        "outdate": 2173017600000
    }
}
```

## 登出

* 说明

> 若操作成功，则该token失效

* 例子

path: /User/logout

```json
request:
{
    "token":""
}
```

```json
response:
{
    "status": "OK",
    "result": {
    }
}
```

## 获取当前用户可用功能列表

* 当前用户可用功能列表数据结构说明



* 例子

path:/User/Functions/get

```json
request:
{}
```

```json
response:
{
    "status": "OK",
    "result": [
        {
            ccompanyNamenyName: "添加"
        },
        {
        companyNamecompanyName: "获取功能列表"
        },
        {
   companyName     companyName: "公司controller"
        },
        companyName          companyName: "/BankAccount"
        },
   companyName {
            companyName: "用户相关"
        }companyName      {
            companyName: "获取用户信息"
    companyName},
        {
            companyName: ""companyName     },
        {
            companyName: "xiacompanyName"
        },
        {
            companyName: "登陆"
        }
    ]
}

```