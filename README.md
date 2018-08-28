# 接口列表

## 约定

* 除了登录接口外，所有的请求需要在cookie中放置token=xxxxx

* token的正确性由服务端保证

* 所有请求都采用post

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

## 获取菜单列表

* 菜单数据结构说明

> 如果菜单为一级菜单，则children为其本身  
如果菜单为二级及以下菜单则children是其子菜单

* 例子

path:/User/Menu/get

```json
request:
{}
```

```json
response:
{
    "status": "OK",
    "result": 
    [
      {
          "path": "", //默认跳转进来的首页
          "component": "Layout",  //这个Layout是方便前端用来做判断处理的
          "redirect": "dashboard",
          "children": [
              {
                  "path": "dashboard",
                  "component": "dashboard/index",  //组件文件名称
                  "meta": {
                      "title": "首页" // 菜单名称
                  }
              }
          ]
      },
      {
          "path": "/example",
          "component": "Layout",
          "redirect": "/example/table",
          "name": "Example",
          "meta": {
              "title": "案例"
          },
          "children": [
              {
                  "path": "table",
                  "name": "Table",
                  "component": "table/index",
                  "meta": {
                      "title": "表格"
                  }
              },
              {
                  "path": "tree",
                  "name": "Tree",
                  "component": "tree/index",
                  "meta": {
                      "title": "树形菜单"
                  }
              }
          ]
      },
      {
          "path": "*",
          "redirect": "/404",  //报错页面404
          "hidden": true
      }
    ]
}

```