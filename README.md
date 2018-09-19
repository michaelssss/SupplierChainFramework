# 纯Java框架实践文档

## 版本管理

采用git作为版本管理，理由是能够方便的创建分支并合并

## 开发

* 采用前后端完全分离

* 前端自行维护完整路由表

* 后端只告知哪些功能项对于登陆用户是可用的

* 权限校验完全由后台掌控

* 前端只保证用户体验的校验

* 前后端通过功能名称及Api文档来关联

* 后台采用Swagger注解，并且依据Swagger注解自动将开发代码变为开发文档

* 因为采用了Hibernate作为对象持久化管理组件，开发人员无需关心底层的建表，只需要关注对象之间的关系

* 部署Nginx反响代理解决前后端跨域问题

```conf
server
{
    charset utf-8;
    access_log /home/logs/access.log;
    error_log /home/logs/error.log;
    listen 80;
    root /home/rzzl-admin/dist;
    location / {
        try_files $uri $uri/ @router;
        index index.html;
    }

    location ^~ /api {
        rewrite  ^/api/(.*)$ /$1 break;
        proxy_pass http://127.0.0.1:8080;
     }

    location @router {
        rewrite ^.*$ /index.html last;
    }
}
```

## 最佳实践

* 对于查询列表的操作，有如下几种状况
    
```java
   //完全只需要用SQL解决查询，无需要在业务代码做处理，则只需要将pageable代码传入Jpa的repository，示例代码如下

    public Response<?> listxxxx(@RequestBody Map<String,String> requestMap, HttpServletRequest request, HttpServletResponse response){
        Page<XXX> page = this.XXXrepository.findall(Example.of(requestBody),PageUtils.getPageableFromRequest(request));
        PageUtils.writeResponsePageHeader(page, response);
        return (Response<List<XXX>>)Response.OK(page.getContent());
    }

```

```java
   //需要自己在Java处理列表的，示例如下
   //需要将拼装过程放在Service,或者专有业务对象完成列表拼装
   //以下代码是从公司信息list中摘取的

    public Response<List<Company>> list(@RequestBody Map<String, String> queryParam, HttpServletRequest request, HttpServletResponse response) {
            Company sample = CompanyImpl.builder().companyName(queryParam.get("companyName")).companyType(queryParam.get("companyType")).build();
            List<Company> companies = companyHistoryService.getAllCompanyLatestHistory(sample);
            Pageable pageable = PageUtils.getPageableFromRequest(request);
            Page<Company> companyPage = (Page<Company>) PageUtils.getPageFromPageable(companies, pageable);
            PageUtils.writeResponsePageHeader(companyPage, response);
            return (Response<List<Company>>) Response.OK(companyPage.getContent());
        }
```

* 对于前端页面应该从http.headers中获取数据

* 前端查询提交分页请求时，应在Header中增添pageNum和pageSize两个参数,默认请求是第一页，每页20个元素示例代码如下

```javascript
async getTableData(currentPage) {
      await (
        axios.post('/api/Company/list', {}, {
          headers: {
            pagenum: currentPage,
            pagesize: this.pageSize
          }
        }).then(res => {
          this.tableData = res.data.result
          // 分页组件用的参数
          if (res.headers.totalelement > 0) {
            this.total_Number = parseInt(res.headers.totalelement)
            this.currentPage = parseInt(res.headers.pagenum)
            this.total_page = parseInt(res.headers.totalpage)
          } else {
            this.total_Number = 1
            this.currentPage = 1
            this.total_page = 1
          }
        }).catch(res => {
          console.log(res)
        })
      )
    }
```

* 约定，每个页面对应的应该是个聚合根对象结构体

## 后台单元测试最佳实践

* 单元测试库使用独立的DB实例（Hibernate限制,无法使用schema做分离），且使用create-drop建表规则

* 正常库使用update建表规则

* 单元测试不通过不允许提交代码