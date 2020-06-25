```bash
student@M16015 MINGW64 ~/Desktop/django_self
$ django-admin startproject yanoo
student@M16015 MINGW64 ~/Desktop/django_self
$ cd yanoo
student@M16015 MINGW64 ~/Desktop/django_self/yanoo
$ python manage.py startapp movies

```



* settings.py

```python
INSTALLED_APPS = [
    'movies',  			# 추가시킨다
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
]
```





![image](https://user-images.githubusercontent.com/22831002/84983031-d370e200-b172-11ea-8fd8-9dd9ea4724fd.png)

templates 폴더와 movies폴더를 만들고 index.html와 base.html을 만든다.





* base.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
  {% block body %}
  {% endblock %}
</body>
</html>
```



* index.py

```html
{% extends 'base.html' %}
{% block body %}

{% endblock %}
```



* models.py

```python
from django.db import models

# Create your models here. 데이터 베이스 쓸 때
class Movie(models.Model):
    title=models.CharField(max_length=150)
    content=models.TextField()
    created_at=models.DateTimeField(auto_now_add=True)
    updated_at=models.DateTimeField(auto_now=True)


```



* movies/urls.py (urls.py 는 만든다)

```python
from django.urls import path
from . import views

app_name="movies"

urlpatterns=[
    path('index/', views.index, name="index"),
]
```



* movies/views.py

```python
from django.shortcuts import render

# Create your views here.
def index(request):
    return render(request,"movies/index.html")
```





* settings.py 에 추가시킨다.

```python
TEMPLATES = [
    {
        'BACKEND': 'django.template.backends.django.DjangoTemplates',
        'DIRS': [os.path.join(BASE_DIR,'yanoo','templates')], ## 분리할때 쓰는거인듯
        'APP_DIRS': True,
        'OPTIONS': {
            'context_processors': [
                'django.template.context_processors.debug',
                'django.template.context_processors.request',
                'django.contrib.auth.context_processors.auth',
                'django.contrib.messages.context_processors.messages',
            ],
        },
    },
]
```



* yanoo/urls.py

```python
from django.contrib import admin
from django.urls import path,include  # 여기서 include는 분리하기 위해서

urlpatterns = [
    path('admin/', admin.site.urls),
    path('movies/',include('movies.urls'))
]
```



models.py 에서 설정한 데이터 베이스 모델을 이식시켜야한다

```bash
student@M16015 MINGW64 ~/Desktop/django_self/yanoo
$ python manage.py makemigrations
Migrations for 'movies':
  movies\migrations\0001_initial.py
    - Create model Movie
```

```bash
student@M16015 MINGW64 ~/Desktop/django_self/yanoo
$ python manage.py migrate movies
Operations to perform:
  Apply all migrations: movies
Running migrations:
  Applying movies.0001_initial... OK
```

```bash
student@M16015 MINGW64 ~/Desktop/django_self/yanoo
$ python manage.py migrate
Operations to perform:
  Apply all migrations: admin, auth, contenttypes, movies, sessions
Running migrations:
  Applying contenttypes.0001_initial... OK
  Applying auth.0001_initial... OK
  Applying admin.0001_initial... OK
  Applying admin.0002_logentry_remove_auto_add... OK
  Applying admin.0003_logentry_add_action_flag_choices... OK
  Applying contenttypes.0002_remove_content_type_name... OK
  Applying auth.0002_alter_permission_name_max_length... OK
  Applying auth.0003_alter_user_email_max_length... OK
  Applying auth.0004_alter_user_username_opts... OK
  Applying auth.0005_alter_user_last_login_null... OK
  Applying auth.0006_require_contenttypes_0002... OK
  Applying auth.0007_alter_validators_add_error_messages... OK
  Applying auth.0008_alter_user_username_max_length... OK
  Applying auth.0009_alter_user_last_name_max_length... OK
  Applying sessions.0001_initial... OK
```





## shell 사용해서 데이터베이스

* shell창으로 들어간다

```shell
student@M16015 MINGW64 ~/Desktop/django_self/yanoo
$ python manage.py shell
Python 3.7.6 (tags/v3.7.6:43364a7ae0, Dec 19 2019, 00:42:30) [MSC v.1916 64 bit (AMD64)] on win32
Type "help", "copyright", "credits" or "license" for more information.
(InteractiveConsole)
>>>
```





```shell
>>> from movies.models import Movie
>>> Movie.objects.all()
<QuerySet []>
```



#### insert 하기 (3가지 방법)

```shell
# 첫 번째 방법
>>> movie=Movie()
>>> movie.title='first'
>>> movie.content='django!!' 
>>> movie.save()
>>> movie
<Movie: Movie object (1)>

# 두 번째 방법
>>> movie=Movie(title='second', content='django~!')
>>> movie.save()
>>> movie
<Movie: Movie object (2)>

# 세 번째 방법
>>> Movie.objects.create(title='third',content='django~~')
<Movie: Movie object (3)>
```



#### 들어간 데이터 확인

```shell
>>> movie[0].title
'first'
```



이때 movie를 입력하면

```shell
>>> movie
<QuerySet [<Movie: Movie object (1)>, <Movie: Movie object (2)>, <Movie: Movie object (3)>]>
```

이렇게 되는데 형식을 바꾸고 싶으면

* models.py

```python
from django.db import models

# Create your models here.
class Movie(models.Model):
    title=models.CharField(max_length=150)
    content=models.TextField()
    created_at=models.DateTimeField(auto_now_add=True)
    updated_at=models.DateTimeField(auto_now=True)

    def __str__(self):  # 이 부분을 추가 시켜준다.
        return f'{self.id}번째 글 - {self.title} : {self.content}'
```



models를 바꿨으니 shell을 exit() 하고 다시 실행해줘야 한다.

```shell
>>> exit()
```



다시 shell을 실행하고

```shell
student@M16015 MINGW64 ~/Desktop/django_self/yanoo
$ python manage.py shell
Python 3.7.6 (tags/v3.7.6:43364a7ae0, Dec 19 2019, 00:42:30) [MSC v.1916 64 bit (AMD64)] on win32
Type "help", "copyright", "credits" or "license" for more information.
(InteractiveConsole)
```



Movie를 확인해 보면

```bash
>>> from movies.models import Movie
>>> Movie.objects.all()
<QuerySet [<Movie: 1번째 글 - first : django!!>, <Movie: 2번째 글 - second : django~!>, <Movie: 3
번째 글 - third : django~~>]>
```



* query 확인하는 방법

```bash
>>> print(Movie.objects.all().query)
SELECT "movies_movie"."id", "movies_movie"."title", "movies_movie"."content", "movies_movie"."created_at", "movies_movie"."updated_at" FROM "movies_movie"
```

#### SELECT

* SELECT * FROM movies_movie WHERE title='first'

```bash
# 특정 제목 불러오기
>>> Movie.objects.filter(title='first')
<QuerySet [<Movie: 1번째 글 - first : django!!>]>

# 생성 후 불러오기
>>> Movie.objects.create(title='first', content='hahahahaha')
<Movie: 4번째 글 - first : hahahahaha>
>>> Movie.objects.filter(title='first')
<QuerySet [<Movie: 1번째 글 - first : django!!>, <Movie: 4번째 글 - first : hahahahaha>]>
```



* SELECT * FROM movies_movie WHERE title='first' LIMIT=1

```bash
>>> Movie.objects.filter(title='first').first()
<Movie: 1번째 글 - first : django!!>
>>> Movie.objects.filter(title='first').last()  
<Movie: 4번째 글 - first : hahahahaha>
>>> Movie.objects.filter(title='first')[0]    
<Movie: 1번째 글 - first : django!!>
```



* SELECT \* FROM movies_movie WHERE id=1

```bash
>>> Movie.objects.get(id=1)
<Movie: 1번째 글 - first : django!!>
>>> Movie.objects.get(pk=1) 
<Movie: 1번째 글 - first : django!!>

# 주의점
# 1. 고유값이 아닌 내용을 필터링해서 2개 이상의 값이 찾아지면 오류를 발생한다.
#		=> .get()은 반드시 하나의 객체만 가져올 수 있다.
# 2. 없는 것을 가지고 오려고 해도 오류가 발생한다.
#		=> filter는 빈 쿼리셋이 반환이 된다.
>>> Movie.objects.filter(pk=10)
<QuerySet []>
```



* Like / startswith / endswith

```bash
# 특정 문자로 가져오기
# XXX__contains : XXX에 해당 ''을 포함하고 있는 객체 반환
>>> Movie.objects.filter(title__contains='fir')
<QuerySet [<Movie: 1번째 글 - first : django!!>, <Movie: 4번째 글 - first : hahahahaha>]>

>>> Movie.objects.filter(title__startswith='se')
<QuerySet [<Movie: 2번째 글 - second : django~!>]>

>>> Movie.objects.filter(content__endswith='ha')
<QuerySet [<Movie: 4번째 글 - first : hahahahaha>]>
```



* ASC / DESC

```bash
# 오름차순
>>> Movie.objects.all().order_by('pk')
<QuerySet [<Movie: 1번째 글 - first : django!!>, <Movie: 2번째 글 - second : django~!>, <Movie: 3번째 글 - third : django~~>, <Movie: 4번째 글 - first : hahahahaha>]>

# 내림차순
>>> Movie.objects.all().order_by('-pk') 
<QuerySet [<Movie: 4번째 글 - first : hahahahaha>, <Movie: 3번째 글 - third : django~~>, <Movie: 2번째 글 - 
second : django~!>, <Movie: 1번째 글 - first : django!!>]>

## Order_by는 DB단에서 역순으로 가져오는 것이고

## 이것은 이미 가져온 것을 파이썬에서 역순으로 처리하는 것이다.
>>> Movie.objects.all()[::-1]
[<Movie: 4번째 글 - first : hahahahaha>, <Movie: 3번째 글 - third : django~~>, <Movie: 2번째 글 - second : django~!>, <Movie: 1번째 글 - first : django!!>]
```



#### UPDATE

* UPDATE movies_movie SET title='byebye' WHERE id=1

```bash
# 수정
>>> movie=Movie.objects.get(pk=1)
>>> movie.title='byebye'
>>> movie
<Movie: 1번째 글 - byebye : django!!>

## 보이는 shell에는 바뀐 것처럼 보이지만 실제 DB에 저장이 되려면 저장을 해줘야 한다.
>>> movie.save()
```



#### DELETE

* DELETE FROM movies_movie WHERE id=1

```bash
# 삭제
>>> movie=Movie.objects.get(pk=1)
>>> movie.delete()
(1, {'movies.Movie': 1})

# pk=1 은 삭제하고 없기 때문에 오류가 난다.
# delete는 별도로 저장을 해주지 않아도 자동으로 DB에 반영이 된다.
>>> movie=Movie.objects.get(pk=1)
Traceback (most recent call last):
  File "<console>", line 1, in <module>
  File "C:\Users\student\AppData\Local\Programs\Python\Python37\lib\site-packages\django\db\models\manager.py", line 82, in manager_method
    return getattr(self.get_queryset(), name)(*args, **kwargs)
  File "C:\Users\student\AppData\Local\Programs\Python\Python37\lib\site-packages\django\db\models\query.py", line 399, in get
    self.model._meta.object_name
movies.models.Movie.DoesNotExist: Movie matching query does not exist.
```





### 관리자 페이지로 확인하기

* admin.py

```python
from django.contrib import admin
from .models import Movie

# Register your models here.
admin.site.register(Movie)
```



```bash
student@M16015 MINGW64 ~/Desktop/django_self/yanoo
$ python manage.py runserver
Performing system checks...

System check identified no issues (0 silenced).
June 18, 2020 - 15:49:37
Django version 2.1.15, using settings 'yanoo.settings'
Starting development server at http://127.0.0.1:8000/
Quit the server with CTRL-BREAK.
```



http://127.0.0.1:8000/admin 로 접속하면

![image](https://user-images.githubusercontent.com/22831002/84987614-70377d80-b17b-11ea-9ec2-189a59b9de35.png)



* python에는 슈퍼계정이 존재

```bash
# 모든 설정파일을 migrate 해준 뒤 슈퍼유저 생성
$ python manage.py migrate
$ python manage.py createsuperuser
```

```bash
$ python manage.py createsuperuser
사용자 이름 (leave blank to use 'student'): admin
이메일 주소:  #이메일은 그냥 엔터쳐서 생략했다.
Password: 
Password (again):
비밀번호가 너무 짧습니다. 최소 8 문자를 포함해야 합니다.
비밀번호가 너무 일상적인 단어입니다.
Bypass password validation and create user anyway? [y/N]: y
Superuser created successfully.
```



다시 runserver하고 admin 접속 후 입력한 아이디 비번으로 접속

```bash
 python manage.py runserver
```

![image](https://user-images.githubusercontent.com/22831002/84988092-5480a700-b17c-11ea-916f-23bbedf99f57.png)



* admin.py에서 커스터마이징하기

원래 Movies를 클릭하면

![image](https://user-images.githubusercontent.com/22831002/84988382-dec90b00-b17c-11ea-8748-8862d20ec5bf.png)

이 화면이다



admin.py를 수정하면

```python
from django.contrib import admin
from .models import Movie

# Register your models here.
class MovieAdmin(admin.ModelAdmin):
    list_display=('id', 'title', 'content', 'created_at', 'updated_at')

admin.site.register(Movie, MovieAdmin)
```

![image](https://user-images.githubusercontent.com/22831002/84988487-07e99b80-b17d-11ea-90c6-40cbc9e10f4b.png)

이렇게 바뀌게 된다.



## Django ORM

### index페이지에 전체 DB 보여주기

* views.py

```python
from django.shortcuts import render
from movies.models import Movie #Movie 데이터 가져와야하므로(?)

# Create your views here.
def index(request):
    # 전체 데이터 가져오기
    # 그 데이터 템플릿에게 넘겨주기
    # 템플릿에서 반복문으로 각각의 게시글 pk, title 보여주기
    movie=Movie.objects.all()
    context={
        'movies': movie
    }
    return render(request,"movies/index.html", context)
```



* index.html

```html
{% extends 'base.html' %}
{% block body %}
<h1>게시판</h1>
<hr>
<a href="">NEW</a>
<a href="">introduce</a>

{% for movie in movies %}
  <h3>{{movie.pk}}</h3>
  <h4>{{movie.title}}</h4>
  <h5>{{movie.content}}</h5>
  <hr>
{% endfor %}

{% endblock %}
```



페이지를 확인해보면

![image](https://user-images.githubusercontent.com/22831002/85186838-434bad80-b2d6-11ea-937e-2d3a9d61823b.png)





### new페이지에서 글 작성하기



* views.py

![image](https://user-images.githubusercontent.com/22831002/85186970-0a600880-b2d7-11ea-983f-b30ca66320bf.png)

```python
from django.shortcuts import render,redirect
from movies.models import Movie

# Create your views here.
def index(request):
    # 전체 데이터 가져오기
    # 그 데이터 템플릿에게 넘겨주기
    # 템플릿에서 반복문으로 각각의 게시글 pk, title 보여주기
    movie=Movie.objects.all()
    context={
        'movies': movie
    }
    return render(request,"movies/index.html", context)

def new(request):
    return render(request,'movies/new.html')

def create(request):
    title=request.POST.get('title')
    text=request.POST.get('text')
    Movie.objects.create(title=title,content=text)
    return redirect('movies:index')
```





* 화면

![image](https://user-images.githubusercontent.com/22831002/85187303-9a9f4d00-b2d9-11ea-9046-7c6b8d4545b2.png)

* new.html

```python
{% block body %}
<h1>글 작성 페이지</h1>

<form action="{% url 'movies:create' %}" method="POST">
  {% csrf_token %}
  <label for="name">제목 : </label>
  <input type="text" name="title">

  <label for="cnt">내용 : </label>
  <input type="text" name="text">

  <input type="submit" value="글 작성">
</form>
{% endblock %}
```



* create.html

```html
{% block body %}
<p>{{ title }}, {{ text }}</p>
{% endblock %}
```



* index.html

![image](https://user-images.githubusercontent.com/22831002/85187695-5b263000-b2dc-11ea-8af1-9ccaba981e68.png)

```html
{% extends 'base.html' %}
{% block body %}
<h1>게시판</h1>
<hr>
<a href="{% url 'movies:new' %}">NEW</a>
<a href="{% url 'movies:introduce' %}">introduce</a>

{% for movie in movies %}
  <h3>{{movie.pk}}</h3>
  <h4>{{movie.title}}</h4>
  <h5>{{movie.content}}</h5>
  <hr>
{% endfor %}

{% endblock %}
```



* urls.py

```python
from django.urls import path
from . import views

app_name="movies"

urlpatterns=[
    path('index/', views.index, name="index"),
    path('new/', views.new, name="new"),
    path('create/', views.create, name="create"),
    path('introduce/', views.introduce, name="introduce"),
]
```



* csrf_token 이란??

  * 내 DB에 어떠한 조작을 할 수 있는 요청을 보낼 땐 항상 세트로 넣어줘야 한다.
  * 보안을 위한 것. (없어도 요청은 감)

  redirect를 import하여 index로 redirect한다.

  

### index페이지에서 상세페이지 보기

* urls.py  &  views.py

```python
# urls.py
path('<int:movies_pk>detail/', views.detail, name="detail")

# views.py
def detail(request, movies_pk):
    movie=Movie.objects.get(pk=movies_pk)
    context={
        'movie':movie
    }
    return render(request, 'movies/detail.html',context)
```

* index.html

![image](https://user-images.githubusercontent.com/22831002/85188247-2e741780-b2e0-11ea-9f75-6025c2704b44.png)

```html
{% extends 'base.html' %}
{% block body %}
<h1>게시판</h1>
<hr>
<a href="{% url 'movies:new' %}">NEW</a>
<a href="{% url 'movies:introduce' %}">introduce</a>

{% for movie in movies %}
  <a href="{% url 'movies:detail' movie.pk %}"><h3>{{movie.pk}}번 째 글</h3></a>
  <h4>{{movie.title}}</h4>
  <h5>{{movie.content}}</h5>
  <hr>
{% endfor %}

{% endblock %}
```



* detail.html

```html
{% block body %}
<h1> 상세 페이지^^</h1>
<h2>{{movie.pk}}번 째 글</h2>
<h4>제목 : {{movie.title}}</h4>
<h5>내용 : {{movie.content}}</h5>
<p>생성 시간 : {{movie.created_at}}</p>
<p>수정 시간 : {{movie.updated_at}}</p>
<a href="{% url 'movies:index' %}">[back]</a>
{% endblock %}
```



### 상세페이지에서 글 삭제하기

* urls.py & views.py

```python
# urls.py
path('<int:movies_pk>/delete/', views.delete, name="delete"),

# views.py
# 1. 특정 글 삭제를 위한 경로 작성
# 1.1 /movies/delete/
# 2. 글 삭제 처리를 해주는 view 작성
# 3. 글 삭제 후, index page로 redirect
# 4. 글 삭제를 위한 링크 detail에 작성
def delete(request, movies_pk):
    movie=Movie.objects.get(pk=movies_pk)
    movie.delete()
    return redirect('movies:index')
```



* detail.html

```html
{% block body %}
<h1> 상세 페이지^^</h1>
<h2>{{movie.pk}}번 째 글</h2>
<h4>제목 : {{movie.title}}</h4>
<h5>내용 : {{movie.content}}</h5>
<p>생성 시간 : {{movie.created_at}}</p>
<p>수정 시간 : {{movie.updated_at}}</p>
<a href="{% url 'movies:delete' movie.pk %}">삭제</a> <!--추가한 부분-->
<a href="{% url 'movies:index' %}">[back]</a>
{% endblock %}
```



### 게시글 수정하기

* urls.py & views.py

```python
# urls.py
path('<int:movies_pk>/update/', views.update, name="update")

# views.py
# 1. 특정 글 수정을 위한 경로 생성
# 1-1. /movies/1/edit
# 2. 글 수정 template를 render하는 edit view 작성
# 2-1. 해당 templateㄹ에 form tag 생성
# 2-2. 각 input tag 내부에 기존 내용이 들어있어야 함.
# 3. edit 보낸 데이터 처리를 위한 경로 생성
# 3-1. /movies/1/update
# 4. 글 수정 처리를 하는 update view 작성
# 5. 해당 글 상세 페이지로 redirect
# 6. 글 수정을 위한 edit 링크 해당 글 상세 페이지에 생성
# 6-1. {% url 'movies:edit' movie.pk %}
def edit(request, movies_pk):
    movie=Movie.objects.get(pk=movies_pk)
    context={
        'movie':movie
    }
    return render(request, 'movies/edit.html', context)

def update(request, movies_pk):
    edit_title=request.POST.get('edit_title')
    edit_content=request.POST.get('edit_content')
    movie=Movie.objects.get(pk=movies_pk)
    movie.title=edit_title
    movie.content=edit_content
    movie.save()
    return redirect('movies:detail',movies_pk)
```



* detail.html

```html
{% block body %}
<h1> 상세 페이지^^</h1>
<h2>{{movie.pk}}번 째 글</h2>
<h4>제목 : {{movie.title}}</h4>
<h5>내용 : {{movie.content}}</h5>
<p>생성 시간 : {{movie.created_at}}</p>
<p>수정 시간 : {{movie.updated_at}}</p>
<a href="{% url 'movies:edit' movie.pk %}">수정</a>  <!--추가한 부분-->
<a href="{% url 'movies:delete' movie.pk %}">삭제</a>
<a href="{% url 'movies:index' %}">[back]</a>
{% endblock %}

```

* edit.html

```html
{% block body %}
<form action="{% url 'movies:update' movie.pk %}" method="POST">
  {% csrf_token %}
  {{ movie.pk }}번 째 글
  제목 : <input type="text" name="edit_title" value="{{ movie.title }}">
  내용 : <input type="text" name="edit_content" value="{{ movie.content }}">

  <input type="submit" value="수정하기">
</form>

<a href="{% url 'movies:index' %}">[back]</a>
{% endblock %}
```







### django relation ( 이건 강사님 클론 받았어 ) 

### ( repository는 django relation) 



* models.py

```python
from django.db import models

# Create your models here.
class Article(models.Model):
    title = models.CharField(max_length=150)
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    def __str__(self):
        return f'{self.pk}번째 글, {self.title}-{self.content}'

class Comment(models.Model):
    # models.ForeignKey(상속받을 클래스명, Article이 삭제되었을때 어떻게 할것인지)
    # 멤버 변수 = models.외래키(참조하는 객체)
    article=models.ForeignKey(Article, on_delete=models.CASCADE) 
    content=models.CharField(max_length=200)
    created_at=models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return f'Article:{self.article}, {self.pk}-{self.content}'
```



라이브러리 설치

```bash
pip install django-extensions
```



 

* settings.py

  설치한것을 settings에서 설정을 해줘야함 설치할때는 -였지만 설정할때는 _로해야함

```python
INSTALLED_APPS = [
    'django_extensions'    #django-extensions가 아니다
    'articles',
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
]
```



shell을 plus로 킨다

```bash
student@M16015 MINGW64 ~/Desktop/django_relation/django_relation/mysite (master)
$ python manage.py shell_plus
```



내용 확인해보면

```bash
>>> Article.objects.all()
<QuerySet [<Article: 1번째 글, 제목-내용>, <Article: 2번째 글, 제목-내용>]>
>>> article=Article.objects.get(pk=1)
>>> article
<Article: 1번째 글, 제목-내용>
>>>
```



```bash
pip install ipython
```



다시 shell_plus로 이동

```shell
# Shell Plus Model Imports
from articles.models import Article, Comment
from django.contrib.admin.models import LogEntry
from django.contrib.auth.models import Group, Permission, User
from django.contrib.contenttypes.models import ContentType
from django.contrib.sessions.models import Session
# Shell Plus Django Imports
from django.core.cache import cache
from django.conf import settings
from django.contrib.auth import get_user_model
from django.db import transaction
from django.db.models import Avg, Case, Count, F, Max, Min, Prefetch, Q, Sum, When, Exists, OuterRef, Subquery
from django.utils import timezone
from django.urls import reverse
Python 3.7.6 (tags/v3.7.6:43364a7ae0, Dec 19 2019, 00:42:30) [MSC v.1916 64 bit (AMD64)]
Type 'copyright', 'credits' or 'license' for more information
IPython 7.15.0 -- An enhanced Interactive Python. Type '?' for help.

In [1]: 
```

쉘창이 달라져있는걸 확인할 수 있다.





```shell
In [1]: comment = Comment()

In [2]: comment.content = '첫번째 댓글'

In [3]: comment.save()
---------------------------------------------------------------------------
IntegrityError  
```

오류가 뜬다 - 게시물 연결 안되어 있어서!



위에 입력한건 남아있기에 확인해보면

```shell
In [4]: article = Article.objects.get(pk=1)

In [5]: comment.article = article

In [6]: comment.save()
```

오류없다.



comment 확인해보면

```shell
In [7]: comment
Out[7]: <Comment: Article:1번째 글, 제목-내용, 1-첫번째 댓글>
```



READ

```shell
In [8]: comment.article
Out[8]: <Article: 1번째 글, 제목-내용>

In [9]: comment.article.title
Out[9]: '제목'

In [10]: comment.article.content
Out[10]: '내용'

In [11]: article.comment_set.all()
Out[11]: <QuerySet [<Comment: Article:1번째 글, 제목-내용, 1-첫번째 댓글>]>

```



### form 써서 댓글 구현하기

#### 댓글 생성

forms.py로 가서

```python
from django import forms
from .models import Article, Comment

class ArticleForm(forms.ModelForm):
    class Meta:
        model = Article
        fields = '__all__'

class CommentForm(forms.ModelForm):
    class Mete:
        model = Comment
        fields = '__all__'
        #fields = '__all__'
        exclude=['article']
```



* views.py

```python
from django.shortcuts import render, redirect, get_object_or_404
from .models import Article, Comment # 여기서 Comment추가
from .forms import ArticleForm, CommentForm # 여기서 CommentForm추가
# DVDH
from django.views.decorators.http import require_POST

def detail(request, article_pk):
    article = get_object_or_404(Article, pk=article_pk)
    comment_form = CommentForm()
    # 1은 N을 보장할 수 없기 때문에 querySet(comment_set)형태로 조회해야한다.
    comments=article.comment_set.all()
    context = {
        'article': article,
        'comments':comments,
        'comment_form' : comment_form,
    }
    return render(request, 'articles/detail.html', context)

# 댓글 생성
@require_POST
def comment_create(request,article_pk):  # 댓글 생성
    #article = Article.objects.get(pk=article_pk)
    article=get_object_or_404(Article, pk=article_pk)

    comment_form = CommentForm(request.POST)
    if comment_form.is_valid():
        comment = comment_form.save(commit=False)
        comment.article=article
        #comment.article_id(자동으로 만들어줌) = article.pk
        comment.save()
        return redirect('articles:detail', article_pk)
    else:
        context={
            'comment_form' : comment_form,
            'article' : article
        }
    return redirect('articles:detail', context)

# 댓글 삭제
@require_POST
def comment_delete(request, article_pk, comment_pk):
    comment=get_object_or_404(Comment, pk=comment_pk)
    comment.delete()
    return redirect('articles:detail',article_pk)
    

```



* urls.py

```python
#댓글 생성
path('<int:article_pk>/comment_create/',views.comment_create, name="comment_create"),
# 댓글 삭제
path('<int:article_pk>/comment_delete/<int:comment_pk>/',views.comment_delete, name="comment_delete"),
```



* detail.html

```html
{% extends 'base.html' %}
{% block body %}
<h1>상세 페이지</h1>
<hr>
<p>{{ article.pk }}번째 글</p>
<h2>{{ article.title }}</h2>
<h3>{{ article.content }}</h3>
<hr>
<p>{{ comments|length }}개의 댓글</p>
<p>{{ article.comment_set.all|length }}</p>
<ul>
{% for comment in comments %}
<li>{{ comment.content }}
                                        <!-- 2개 이상의 값을 넘겨주고자 할 때 순서 주의-->
  <form action="{% url 'articles:comment_delete' article.pk comment.pk %}" method="POST">
    {% csrf_token %}
    <input type="submit" value="댓글 삭제">
  </form>
</li>
{% empty %}
<p>댓글이 없습니다.</p>
{% endfor %}
</ul>
<hr>
<form action="{% url 'articles:comment_create' article.pk %}" method="POST">
  {% csrf_token %}
  {{ comment_form }}
  <input type="submit" value="댓글생성">
</form>
<hr>

<a href="{% url 'articles:update' article.pk %}">[UPDATE]</a>
<a href="{% url 'articles:index' %}">[back]</a>
<form action="{% url 'articles:delete' article.pk %}" method="POST">
  {% csrf_token %}
  <input type="submit" value="DELETE" class="btn btn-primary">
</form>

{% endblock %}
```



* index.html

```html
{% extends 'base.html' %}
{% block body %}
<h1>메인 페이지 입니다.</h1>
<hr>
<a href="{% url 'articles:create' %}">[CREATE]</a>
<hr>
{% for article in articles %}
 <p>{{ article.pk }}번째 글</p>
 <p>{{ article.title }}</p>
 <a href="{% url 'articles:detail' article.pk %}">[DETAIL]</a>
 <hr>
{% endfor %}
{% endblock %}
```





## django Login Logout

##### 앱을 새로 만든다

```bash
student@M16015 MINGW64 ~/Desktop/django_relation/django_relation/mysite (master)
$ python manage.py startapp accounts
```



* mysite/urls.py

```python
from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('articles/', include('articles.urls')),
    path('accounts/', include('accounts.urls')),  #추가 시킨다
]

```

* settings.py

```python
INSTALLED_APPS = [
    'accounts',  				# 추가시킨다
    'bootstrap4',
    'django_extensions',
    'articles',
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
]
```



#### 회원가입

accounts안에 templates폴더를 만들고 그 안에 accounts폴더를 만든다 그리고 signup.html을 만든다.

```html
{% extends 'base.html' %}
{% load bootstrap4 %}
{% block body %}
<h1>회원가입</h1>
<form action="" method="POST">
  {% csrf_token %}
  {% bootstrap_form  form %}
  {% buttons %}
    <button type="submit" class="btn btn-primary">
      Submit
    </button>
  {% endbuttons %}
</form>
{% endblock %}
```





* accounts/views.py

```python
from django.shortcuts import render, redirect # redirect 넣어야되는데
from django.contrib.auth.forms import UserCreationForm # 회원가입 할때 django에서 주는 폼

# Create your views here.
def signup(request):    
    if request.method == 'POST':
        form = UserCreationForm(request.POST)
        if form.is_valid():
            user = form.save()
            return redirect('articles:index')
    else:
        form = UserCreationForm()
    context = {
        'form' : form
    }
    return render(request, 'accounts/signup.html', context)

```



* accounts에 urls.py를 만들어서

```python
from django.urls import path
from . import views

app_name = "accounts"

urlpatterns = [
    path('signup/', views.signup, name="signup"),

]
```



서버를 켜서 확인해보면

![image](https://user-images.githubusercontent.com/22831002/85490895-888d1980-b60d-11ea-90a6-f2f902561fff.png)

완료



#### 로그인

```python
# urls.py
path('login/', views.login, name="login"),

# views.py
from django.contrib.auth.forms import UserCreationForm, AuthenticationForm # 여기서 AuthenticationForm 추가했다
from django.contrib.auth import login as auth_login

def login(request):
    if request.method == 'POST':
        form = AuthenticationForm(request, request.POST)
        # AuthenticationForm은 ModelForm이 아닌 Form을 상속하기 때문에 생긴게 달라진다.
        # 별도로 정의된 Model이 없다는 뜻 => 고로 넘겨주는 인자가 달라진다.
        if form.is_valid():
            # 로그인은 DB에 뭔가 작성하는 것은 동일하지만 연결된 모듈이 있는 것은 아니다.
            # 그럼 확인해야 하는것은?
            #   => 세션과 유저정보를 확인해야 하기때문에
            #   => 세션(request)와 유저정보(form.get_user())를 확인해야 한다.
            auth_login(request, form.get_user())
            return redirect(request.GET.get('next') or 'articles:index')
    else:
        form = AuthenticationForm
    context = {
        'form' : form
    }
    return render(request, 'accounts/login.html', context)


```



* login.html

```html
{% extends 'base.html' %}
{% load bootstrap4 %}
{% block body %}
<h1>로그인</h1>
<form action="" method="POST">
  {% csrf_token %}
  {% bootstrap_form form %}
  {% buttons %}
    <button type="submit" class="btn btn-primary">
      Submit
    </button>
  {% endbuttons %}
</form>
{% endblock %}
```

![image](https://user-images.githubusercontent.com/22831002/85498882-8336cb80-b61b-11ea-9978-0e1f4144a6d6.png)

#### 로그아웃

```python
# urls.py
path('logout', views.logout, name="logout"),

# views.py
from django.contrib.auth import logout as auth_logout
def logout(request):
    auth_logout(request)
    return redirect('articles:index')
```

* base.html

```html
{% load bootstrap4 %}
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  {% bootstrap_css %}
  <title>Document</title>
</head>
<body>
  <h1>{{ user.username }}</h1>

  {% if user.username = "" %}
  <a href="{% url 'accounts:login' %}">login</a>
  {% else %}
  <a href="{% url 'accounts:logout' %}">logout</a>
  {% endif %}
  <div class="container">
    {% block body %}
    {% endblock %}
  </div>
  {% bootstrap_javascript jquery='full' %}
</body>
</html>
```





![image](https://user-images.githubusercontent.com/22831002/85499628-f260ef80-b61c-11ea-8f5d-08fc56029368.png)



user가 anonymous일때는 항상 false를 리턴

이를 확인하기 위해서

* views.py

```python
from IPython import embed
def index(request):
    embed()
    .......
```

이렇게 작성 후 index사이트에 로그인할때와 로그인안할때 따로 분리해서 들어가게 되면

terminal창에

```shell
IPython 7.15.0 -- An enhanced Interactive Python. Type '?' for help.

In [1]: 
```

shell창 처럼 뜨는데

```shell
# 로그인 했을 때
In [1]: request.user
Out[1]: <SimpleLazyObject: <User: yuhwanwoo>>

In [2]: request.user.is_anonymous
Out[2]: False

In [3]: request.user.is_authenticated
Out[3]: True

# 로그인 안했을 때
request.user.is_anonymous
-> False

request.user.is_authenticated
-> True
```



shell 창에서 request를 출력해보면 anonymous가 들어있는 것을 확인할 수 있음.

**따라서 다음과 같이 base.html의 조건문을 변경해주는게 좋다 **

```html
 {% if user.is_authenticated %}
  <a href="{% url 'accounts:logout' %}">logout</a>
  {% else %}
  <a href="{% url 'accounts:login' %}">login</a>
  {% endif %
```

=> 세션에 접근을 해서 통과를 해 정보를 가져와서 무엇을 하는게 아니라 단지 로그인했는지만 체크하는것( 템플릿에서 user를 쓸 수 있는 이유는 우리가 항상 request를 보내주기 때문이다.)



#### 로그인/ 비로그인 페이지 분할

ex ) 네이버와 구글의 로그인 페이지

* 로그인이 되어있는데도 또 로그인 페이지 접근을 시도하면 index페이지로 redirect시키기(회원가입도)

```python
# 회원가입 하자마자 로그인하기 위한 로직 추가
def signup(request):    
    if request.method == 'POST':			
        form = UserCreationForm(request.POST)
        if form.is_valid():
            user = form.save()
            auth_login(request, user)             ## 여기추가
            return redirect('articles:index')
    else:
        form = UserCreationForm()
    context = {
        'form' : form
    }
    return render(request, 'accounts/signup.html', context)

def login(request):
    if request.user.is_authenticated:				##  이 부분
        return redirect('articles:index')			##	추가해서 넣었음

    else: 
        if request.method == 'POST':
            form = AuthenticationForm(request, request.POST)
            # AuthenticationForm은 ModelForm이 아닌 Form을 상속하기 때문에 생긴게 달라진다.
            # 별도로 정의된 Model이 없다는 뜻 => 고로 넘겨주는 인자가 달라진다.
            if form.is_valid():
                # 로그인은 DB에 뭔가 작성하는 것은 동일하지만 연결된 모듈이 있는 것은 아니다.
                # 그럼 확인해야 하는것은?
                #   => 세션과 유저정보를 확인해야 하기때문에
                #   => 세션(request)와 유저정보(form.get_user())를 확인해야 한다.
                auth_login(request, form.get_user())
                return redirect(request.GET.get('next') or 'articles:index')
        else:
            form = AuthenticationForm
        context = {
            'form' : form
        }
    return render(request, 'accounts/login.html', context)
```



```python
from django.contrib.auth.decorators import login_required
```

articles app에 있는 detail, index 제외한 모든 함수에 @login_required를 붙여준다.

```python
from django.shortcuts import render, redirect, get_object_or_404
from .models import Article, Comment
from .forms import ArticleForm, CommentForm
# DVDH
from django.views.decorators.http import require_POST
from IPython import embed
from django.contrib.auth.decorators import login_required

# Create your views here.
def index(request):
    #embed()
    articles = Article.objects.all()
    context = {
        'articles': articles
    }
    return render(request, 'articles/index.html', context)

def detail(request, article_pk):
    article = get_object_or_404(Article, pk=article_pk)
    comment_form = CommentForm()
    # 1은 N을 보장할 수 없기 때문에 querySet(comment_set)형태로 조회해야한다.
    comments=article.comment_set.all()
    context = {
        'article': article,
        'comments':comments,
        'comment_form' : comment_form,
    }
    return render(request, 'articles/detail.html', context)

@login_required
def create(request):
    if request.method == "POST":
        form = ArticleForm(request.POST)
        if form.is_valid():
            article = form.save()
            return redirect('articles:detail', article.pk)
    else:
        form = ArticleForm()
    context = {
        'form': form
    }
    return render(request, 'articles/form.html', context)

@login_required
def update(request, article_pk):
    article = get_object_or_404(Article, pk=article_pk)
    if request.method == "POST":
        form = ArticleForm(request.POST, instance=article)
        if form.is_valid():
            article = form.save()
            return redirect('articles:detail', article.pk)
    else:
        form = ArticleForm(instance=article)
    context = {
        'form': form
    }
    return render(request, 'articles/form.html', context)

@login_required
def delete(request, article_pk):
    article = get_object_or_404(Article, pk=article_pk)
    if request.method == "POST":
        article.delete()
        return redirect('articles:index')
    return redirect('articles:detail', article.pk)

@login_required
@require_POST
def comment_create(request,article_pk):
    #article = Article.objects.get(pk=article_pk)
    article=get_object_or_404(Article, pk=article_pk)

    comment_form = CommentForm(request.POST)
    if comment_form.is_valid():
        comment = comment_form.save(commit=False)
        comment.article=article
        #comment.article_id(자동으로 만들어줌) = article.pk
        comment.save()
        return redirect('articles:detail', article_pk)
    else:
        context={
            'comment_form' : comment_form,
            'article' : article
        }
    return redirect('articles:detail', context)

@login_required
@require_POST
def comment_delete(request, article_pk, comment_pk):
    comment=get_object_or_404(Comment, pk=comment_pk)
    comment.delete()
    return redirect('articles:detail',article_pk)
```

==> login이 안된 상태에서 create버튼을 누르면 login페이지로 넘어간다.



#### 회원 탈퇴

먼저 @require_POST를 사용하기 위해 import해주는 작업

```python
# accoutns/views.py
from django.views.decorators.http import require_POST

# @login_requred가 있으면 페이지405 오류가 뜬다. 따라서 GET방식 뭐시기..?
@require_POST
def delete(request):
    request.user.delete()
    return redirect('articles:index')

```

* base.html

```html
{% load bootstrap4 %}
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  {% bootstrap_css %}
  <title>Document</title>
</head>
<body>
  <h1>{{ user.username }}</h1>

  {% if user.is_authenticated %}
  <a href="{% url 'accounts:logout' %}">logout</a>
  <form action="{% url 'accounts:delete' %}" method="post">
    {% csrf_token %}
    <input type="submit" value="회원 탈퇴">
  </form>
  {% else %}
  <a href="{% url 'accounts:login' %}">login</a>
  <a href="{% url 'accounts:signup' %}">회원가입</a>
  {% endif %}
  <div class="container">
    {% block body %}
    {% endblock %}
  </div>
  {% bootstrap_javascript jquery='full' %}
</body>
</html>
```



#### 회원수정

```python
# urls.py
path('update/', views.update, name="update"),

# accounts/views.py
@login_required
def update(request):
    if request.method == 'POST':
        form = CustomUserChangeForm(requset.POST, instance = request.user)
        if form.is_valid():
            form.save()
            return redirect('articles:index')
    else:
        form = CustomUserChangeForm(instance = request.user)
    context={
        'form' : form
    }
    return render(request, 'accounts/update.html', context)
```



```html
{% load bootstrap4 %}
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  {% bootstrap_css %}
  <title>Document</title>
</head>
<body>
  <h1>{{ user.username }}</h1>

  {% if user.is_authenticated %}
  <a href="{% url 'accounts:logout' %}">logout</a>
  <form action="{% url 'accounts:delete' %}" method="post">
    {% csrf_token %}
    <input type="submit" value="회원 탈퇴">
  </form>
  <a href="{% url 'accounts:update' %}">회원 수정</a>  <!--이거 추가 -->
  {% else %}
  <a href="{% url 'accounts:login' %}">login</a>
  <a href="{% url 'accounts:signup' %}">회원가입</a>
  {% endif %}
  <div class="container">
    {% block body %}
    {% endblock %}
  </div>
  {% bootstrap_javascript jquery='full' %}
</body>
</html>
```



* UserChangeForm 커스터마이징

```python
# accounts/forms.py
from django.contrib.auth.forms import UserChangeForm
from django.contrib.auth import get_user_model

class CustomUserChangeForm(UserChangeForm):
    class Meta:
        model = get_user_model()
        fields = ['username', 'email', 'first_name', 'last_name']
```

```html
<!-- update.html -->
{% extends 'base.html' %}
{% load bootstrap4 %}

{% block body %}
<form action="" method="post">
  {% csrf_token %}
  {% bootstrap_form form %}
  {% buttons %}
  <button type="submit" class="btn btn-primary">
    회원 수정
  </button>
  {% endbuttons %}
</form>
{% endblock %}
```



#### 비밀번호 수정

```python
# accounts/views.py
from django.contrib.auth.forms import PasswordChangeForm

@login_required
def password(request):
    if request.method == 'POST':
        form = PasswordChangeForm(request.user, request.POST)
        if form.is_valid():
            user = form.save()
            auth_login(request, user)
            return redirect('articles:index')
    else :
        form = PasswordChangeForm(request.user)
    context = {
        'form' : form
    }
    return render(request, 'accounts/password.html', context)


# accounts/urls.py
path('password/', views.password, name="password"),
```



* 비밀번호 수정 후에도 세션을 유지시키기 위한 작업

```python
# accounts/views.py
from django.contrib.auth import update_session_auth_hash

@login_required
def password(request):
    if request.method == 'POST':
        form = PasswordChangeForm(request.user, request.POST)
        if form.is_valid():
            user = form.save()
            update_session_auth_hash(request, user)
            return redirect('articles:index')
    else :
        form = PasswordChangeForm(request.user)
    context = {
        'form' : form
    }
    return render(request, 'accounts/password.html', context)
```

=> 눈에 보이지 않는 세션 값이 기존과 그대로 유지한다.



* base.html

```html
{% load bootstrap4 %}
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  {% bootstrap_css %}
  <title>Document</title>
</head>
<body>
  <h1>{{ user.username }}</h1>

  {% if user.is_authenticated %}
  <a href="{% url 'accounts:logout' %}">logout</a>
  <form action="{% url 'accounts:delete' %}" method="post">
    {% csrf_token %}
    {% buttons %}
    <button type="submit" class="btn btn-primary">
      회원 탈퇴
    </button>
    {% endbuttons %}
  </form>
  <a href="{% url 'accounts:update' %}">회원 수정</a>
  <a href="{% url 'accounts:password' %}">비밀번호 수정</a> <!-- 추가 -->
  {% else %}
  <a href="{% url 'accounts:login' %}">login</a>
  <a href="{% url 'accounts:signup' %}">회원가입</a>
  {% endif %}
  <div class="container">
    {% block body %}
    {% endblock %}
  </div>
  {% bootstrap_javascript jquery='full' %}
</body>
</html>
```



## Django 사용자 분할

* Article 모델에 외래키 설정 후 마이그레이션 작업 진행
  * 연결할 User 모델은 settings.AUTH_USER_MODEL이다.
  * 마이그레이션 작업을 진행 시 기존의 데이터에 USER 정보가 없으므로 Default값 설정
  * 현재 프로젝트의 admin 계정의 PK값을 찾아 해당 값을 Default로 설정.
* CREATE 로직 수정 & 게시글 작성자 표기
  * 게시글 생성 시, 게시글 작성자 정보를 넣어서 저장한다.
* UPDATE & DELETE 로직 수정
  * 사용자가 자신의 글만 수정/삭제 할 수 있도록 내부 로직 수정
  * 해당 게시글의 작성자가 아니라면, 수정/삭제 버튼이 보이지 않도록 Template수정
* user 모델을 외래키로 참조

![image](https://user-images.githubusercontent.com/22831002/85643984-a8d1dc80-b6d0-11ea-973a-f9cf68ea2502.png)

* 해당 사용자만 수정/삭제가 가능하도록 하자

![image](https://user-images.githubusercontent.com/22831002/85647001-644a3f00-b6d8-11ea-877c-973c4a4a4ba2.png)

* 객체는 생성하지만 DB에는 반영하지 않겠다.

![image](https://user-images.githubusercontent.com/22831002/85644272-6d83dd80-b6d1-11ea-9ee6-7bfbce3f1e7f.png)

![image](https://user-images.githubusercontent.com/22831002/85646147-67443000-b6d6-11ea-806a-9da0b8db9124.png)

* 필요없는 field가 나오지 않도록 해주자

![image](https://user-images.githubusercontent.com/22831002/85643520-5e9c2b80-b6cf-11ea-8649-73e3afa6ca3f.png)

* html도 변경

```html
{% extends 'base.html' %}
{% block body %}
<h1>상세 페이지</h1>
<hr>
<p>{{ article.pk }}번째 글</p>
<h2>{{ article.title }}</h2>
<h3>{{ article.content }}</h3>
<hr>
<p>{{ comments|length }}개의 댓글</p>
<p>{{ article.comment_set.all|length }}</p>
<ul>
{% for comment in comments %}
<li>{{ comment.content }}
                                        <!-- 2개 이상의 값을 넘겨주고자 할 때 순서 주의-->
  {% if comment.user.username == user.username %}      <!-- 이거 추가 -->
  <form action="{% url 'articles:comment_delete' article.pk comment.pk %}" method="POST">
    {% csrf_token %}
    <input type="submit" value="댓글 삭제">
  </form>
  {% endif %}      <!-- 이거 추가 -->
</li>
{% empty %}
<p>댓글이 없습니다.</p>
{% endfor %}
</ul>
<hr>
<form action="{% url 'articles:comment_create' article.pk %}" method="POST">
  {% csrf_token %}
  {{ comment_form }}
  <input type="submit" value="댓글생성">
</form>
<hr>
{% if article.user.username == user.username %}      <!-- 이거 추가 -->
<a href="{% url 'articles:update' article.pk %}">[UPDATE]</a>

<form action="{% url 'articles:delete' article.pk %}" method="POST">
  {% csrf_token %}
  <input type="submit" value="DELETE" class="btn btn-primary">
</form>
{% endif %}      <!-- 이거 추가 -->
<a href="{% url 'articles:index' %}">[back]</a>
{% endblock %}
```



OperationalError: no such column가 떴었는데 그건 db바뀐거 적용 안해줘서 그렇다 

$ python manage.py makemigrations
$ python manage.py migrate

이거 둘중 하나해서 해결하는데 만약 저렇게 해서 오류 크게 나면 그냥 db.sqlite3 파일을 삭제해서 DB날린 후에 실행하면된다. 



## 메시지 출력하기

```python
# articles/views.py
from django.contrib import messages

@login_required
def create(request):
    if request.method == "POST":
        form = ArticleForm(request.POST)
        if form.is_valid():
            article = form.save(commit=False)
            article.user = request.user
            article.save()
            messages.success(request, '게시글 작성 완료') #
            return redirect('articles:detail', article.pk)
        else:
            messages.error(request,'잘못된 데이터를 넣었어')
    else:
        form = ArticleForm()
    context = {
        'form': form
    }
    return render(request, 'articles/form.html', context)

# settings.py
import os
from django.contrib.messages import constants as messages
MESSAGE_TAGS = {
    messages.ERROR : 'danger',
}
```

* base.html

```html
{% load bootstrap4 %}
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  {% bootstrap_css %}
  <title>Document</title>
</head>
<body>
  {% if messages %}						<!-- 여기서부터 추가 -->
    {% for message in messages %}
      <div class="alert alert-{{ message.tags }}">
        {{ message.message }}
      </div>
    {% endfor %}
  {% endif %}						<!-- 여기까지 추가 -->

  <h1>{{ user.username }}</h1>
  {% if user.is_authenticated %}
  <a href="{% url 'accounts:logout' %}">logout</a>
  <form action="{% url 'accounts:delete' %}" method="post">
    {% csrf_token %}
    {% buttons %}
    <button type="submit" class="btn btn-primary">
      회원 탈퇴
    </button>
    {% endbuttons %}
  </form>
  <a href="{% url 'accounts:update' %}">회원 수정</a>
  <a href="{% url 'accounts:password' %}">비밀번호 수정</a>
  {% else %}
  <a href="{% url 'accounts:login' %}">login</a>
  <a href="{% url 'accounts:signup' %}">회원가입</a>
  {% endif %}
  <div class="container">
    {% block body %}
    {% endblock %}
  </div>
  {% bootstrap_javascript jquery='full' %}
</body>
</html>
```



## static 파일 관리하기

#### image

* blank = True
  * 유효성 검사시
* null = True
  * DB상의 컴럼에 null이 가능. TextField에서는 사용하지 마세요

```bash
student@M16015 MINGW64 ~/Desktop/django_relation/django_relation/mysite (master)
$ pip install Pillow  # Python image 관리
```

```python
# articles/models.py
class Article(models.Model):
    title = models.CharField(max_length=150)
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete = models.CASCADE)
    image = models.ImageField(blank=True, null=True)   #여기 추가
```

적용하기 위해서

```bash
$ python manage.py makemigrations
$ python manage.py migrate
```

* enc type 설정하기

```html
<!-- form.html -->
<form action="" method="POST" enctype="multipart/form-data"> <!--submit부분 수정-->
  {% csrf_token %}
  {% bootstrap_form form %}
  {% buttons %}
    <button type="submit" class="btn btn-primary">
      Submit
    </button>
  {% endbuttons %}
</form>

<!-- detail.html -->
{% extends 'base.html' %}
{% block body %}
<h1>상세 페이지</h1>
<hr>
<p>{{ article.pk }}번째 글</p>
<h2>{{ article.title }}</h2>
<h3>{{ article.content }}</h3>

{% if article.image %}    <!-- 여기서부터 추가-->
<img src="{{ article.image.url }}" alt="{{ article.image }}">
{% endif %}				  <!-- 여기까지 추가-->

<hr>
<p>{{ comments|length }}개의 댓글</p>
<p>{{ article.comment_set.all|length }}</p>
<ul>
{% for comment in comments %}
<li>{{ comment.content }}
    .......
    .....
    .......
```



```python
# articles/views.py
@login_required
def create(request):
    if request.method == "POST":
        form = ArticleForm(request.POST, request.FILES)  # request.FILES를 추가했어
        if form.is_valid():
            article = form.save(commit=False)
            article.user = request.user
            article.save()
            messages.success(request, '게시글 작성 완료')
            return redirect('articles:detail', article.pk)
        else:
            messages.error(request,'잘못된 데이터를 넣었어')
    else:
        form = ArticleForm()
    context = {
        'form': form
    }
    return render(request, 'articles/form.html', context)

# settings.py에 밑에 두개 추가
MEDIA_URL = '/media/'
MEDIA_ROOT = os.path.join(BASE_DIR, 'media')
```

* mysite/urls.py

![image](https://user-images.githubusercontent.com/22831002/85661134-548b2480-b6f1-11ea-9da9-e10b44e88b5c.png)



확인해본다

![image](https://user-images.githubusercontent.com/22831002/85661943-3ffb5c00-b6f2-11ea-8da1-75cdff37ba06.png)

결과는??

![image](https://user-images.githubusercontent.com/22831002/85662034-599ca380-b6f2-11ea-88ce-0b4f58505655.png)

짜란~~

#### 날짜별로 폴더 정리

```python
# articles/models.py
class Article(models.Model):
    title = models.CharField(max_length=150)
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete = models.CASCADE)
    # blank = 유효성 검사시
    # null = DB상에 컬럼에
    image = models.ImageField(blank=True, upload_to="%y/%m/%d/")  # 이줄을 이렇게 수정한다. 

    def __str__(self):
        return f'{self.pk}번째 글, {self.title}-{self.content}'
```



글을 작성한 후 확인해보면

![image](https://user-images.githubusercontent.com/22831002/85662645-1c84e100-b6f3-11ea-8625-99136014016b.png)

#### 이미지 크기를 관리하는 방법

```bash
$ pip install pilkit
$ pip install django-imagekit
```

```python
# settings.py
INSTALLED_APPS = [
    'imagekit',  # 이거 추가한다.
]

# models.py
from imagekit.models import ImageSpecField
from imagekit.processors import Thumbnail

# Create your models here.
class Article(models.Model):
    title = models.CharField(max_length=150)
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete = models.CASCADE)
    # blank = 유효성 검사시
    # null = DB상에 컬럼에
    image = models.ImageField(blank=True, upload_to="%y/%m/%d/")
    image_thumbnail = ImageSpecField(     # 이 부분 추가
        source = 'image',
        processors = [Thumbnail(200, 300)],
        format = 'jpeg',
        options = {'quality': 90 }
    )
```

#### user가 저장한 사진 별로 이미지 관리를  하고 싶은 경우 models.py

![image](https://user-images.githubusercontent.com/22831002/85669931-522dc800-b6fb-11ea-883e-045a3d35ea20.png)

![image](https://user-images.githubusercontent.com/22831002/85670177-97ea9080-b6fb-11ea-8605-596e7df073d1.png)

## 1:N 관계 복습

#### 게시글 생성 ORM

```shell
User.objects.create(username='test',password='test')
```

#### 게시글 생성 ORM

```bash
$ python manage.py shell_plus
```

```shell
In [3]: user1=User.objects.get(pk=2)

In [4]: user1
Out[4]: <User: yuhwanwoo12>

In [5]: Article.objects.create(title='aaaa', content='bbbbb',user=user1)
Out[5]: <Article: 9번째 글, aaaa-bbbbb>

In [6]: Article.objects.create(title='aaaa', content='bbbbb',user_id=user1.pk)
Out[6]: <Article: 10번째 글, aaaa-bbbbb>
```



#### 댓글 생성 ORM

```shell
In [1]: comment=Comment.objects.get()

In [2]: comment.article.pk
Out[2]: 2

In [3]: comment.article.title
Out[3]: '2번제목'

In [8]: user1=User.objects.get(pk=1)

# article과 user의 입장에서 댓글과 게시글이 있는지 없는지 모르기 때문에 set.all()로 가져온다.
In [6]: article.comment_set.all()
Out[6]: <QuerySet []>
In [9]: user1.article_set.all()
Out[9]: <QuerySet [<Article: 2번째 글, 2번제목-2번내용>, <Article: 3번째 글, 3번제목-3번내용>, <Article: 5번째 글, mongoDB_title-mongoDB_content>, <Article: 6번째 글, mongogo_title-mongogo_content>, <Article: 7번째 글, hat_title-hat_content>, <Article: 8번째 글, title1first-1content>]>

In [11]: for article in user1.article_set.all():  # 여기서 엔터치면 아래로 내려감
    ...:     print(article.title)
    2번제목
	3번제목
	mongoDB_title
	mongogo_title
	hat_title
	title1first

In [15]: article1=Article.objects.get(pk=3)

In [16]: Comment.objects.create(content='content', user=user1, article=article1)
Out[16]: <Comment: Article:3번째 글, 3번제목-3번내용, 5-content>
```

* 1:N 관계에서 N의 입장은 항상 참조하는 관계가 존재하고 1을 보장할 수 있기 때문에 바로바로 접근가능
  * 1의 입장에서는 접근하는 방법이 달라진다.
    * _set.all()

#### 특정 게시글이 가지고 있는 전체 댓글 불러오기

```shell
article = Article.objects.get(pk=2)
article.comment_set.all()
= Article.objects.get(pk=?).comment_set.all()
```

#### 특정 댓글이 어느 게시글과 연결되어 있는지  확인하기

```shell
In [22]: comment=Comment.objects.get(pk=4)

In [23]: comment.article.title
Out[23]: '2번제목'
```

#### 특정 게시글이 어느 유저와 연결되어 있는지 확인하기

```shell
n [26]: article=Article.objects.get(pk=2)

In [27]: article.user.username
Out[27]: 'yuhwanwoo'
```

#### 특정 유저가 작성한 전체 게시글 가져오기

```shell
In [28]: user1=User.objects.get(pk=1)

In [29]: user1.comment_set.all()
Out[29]: <QuerySet [<Comment: Article:2번째 글, 2번제목-2번내용, 4-댓글이야>, <Comment: Article:3번째 글, 3번제목-3번내용, 5-content>]>
```

#### 특정 유저가 작성한 전체 댓글 가져오기

```shell
In [30]: user1=User.objects.get(pk=1)

In [31]: user1.comment_set.all()
Out[31]: <QuerySet [<Comment: Article:2번째 글, 2번제목-2번내용, 4-댓글이야>, <Comment: Article:3번째 글, 3번제목-3번내용, 5-content>]>
```



## M:N 관계

### ex) 의사-환자 모델링

```bash
# 새로운 앱 생성
student@M16015 MINGW64 ~/Desktop/django_relation/django_relation/mysite (master)
$ python manage.py startapp modelrelation
```

* settings.py

```python
INSTALLED_APPS = [
    'modelrelation',  # 추가시킨다.
]
```

* modelrelation/models.py

```python
from django.db import models

# Create your models here.
class Doctor(models.Model):
    name = models.CharField(max_length=20)

    def __str__(self):
        return f'{self.pk}번 의사 {self.name}'

class Patient(models.Model):
    name = models.CharField(max_length=20)

    def __str__(self):
        return f'{self.pk}번 환자 {self.name}'

class Reservation(models.Model):
    doctor = models.ForeignKey(Doctor, on_delete=models.CASCADE)
    patient = models.ForeignKey(Patient, on_delete=models.CASCADE)

    def __str__(self):
        return f'{self.doctor}의 {self.patient}'
```

적용 시키기 위해서

```bash
$ python manage.py makemigrations
$ python manage.py migrate
```

그 후

```bash
$ python manage.py shell_plus
```

```shell
In [1]: doctor = Doctor.objects.create(name='KIM')

In [2]: patient = Patient.objects.create(name='TOM')

In [3]: doctor
Out[3]: <Doctor: 1번 의사 KIM>

In [4]: patient
Out[4]: <Patient: 1번 환자 TOM>

# reservation을 활용해서 의사와 환자를 연결한다.
In [5]: Reservation.objects.create(doctor=doctor, patient=patient)
Out[5]: <Reservation: 1번 의사 KIM의 1번 환자 TOM>

# 의사의 입장에서 예약정보를 가져오기
# 1은 N을 보장할 수 없기 때문에
In [6]: doctor.reservation_set.all()
Out[6]: <QuerySet [<Reservation: 1번 의사 KIM의 1번 환자 TOM>]>

# 환자의 입장에서 예약정보를 가져오기
In [7]: patient.reservation_set.all()
Out[7]: <QuerySet [<Reservation: 1번 의사 KIM의 1번 환자 TOM>]>

# 2번환자 생성하고 연결하기
In [8]: patient2 = Patient.objects.create(name='KANG')

In [9]: Reservation.objects.create(doctor=doctor, patient=patient2)
Out[9]: <Reservation: 1번 의사 KIM의 2번 환자 KANG>

In [10]: doctor.reservation_set.all()
Out[10]: <QuerySet [<Reservation: 1번 의사 KIM의 1번 환자 TOM>, <Reservation: 1번 의사 KIM의 2번 환자 KANG>]>

# 의사1의 환자이름
In [11]: for reservation in doctor.reservation_set.all():
    ...:     print(reservation.patient.name)
    ...: 
TOM
KANG

# 의사1의 환자번호
In [12]: for reservation in doctor.reservation_set.all():
    ...:     print(reservation.patient.pk)
    ...: 
1
2
```

* 위의 중개모델 말고도 조금 더 쉽게 가져올 수 있는 방법이 존재한다.(ManyToManyField)

```python
# modelrelation/models.py
class Patient(models.Model):
    name = models.CharField(max_length=20)
    doctors = models.ManyToManyField(Doctor, through='Reservation') # 이 부분 추가

```

```bash
$ python manage.py makemigrations
$ python manage.py migrate
```

```shell
# 환자 입장에서 의사 가져오기
In [1]: patient = Patient.objects.get(pk=1)

In [2]: patient.doctors.all()
Out[2]: <QuerySet [<Doctor: 1번 의사 KIM>]>

# 의사 입장에서 환자 가져오기
In [3]: doctor = Doctor.objects.get(pk=1)

In [4]: doctor.patient_set.all()
Out[4]: <QuerySet [<Patient: 1번 환자 TOM>, <Patient: 2번 환자 KANG>]>
```



patients로만 가져오게 하기

![image](https://user-images.githubusercontent.com/22831002/85685622-3382fd80-b70a-11ea-87ec-98315873c630.png)

```bash
$ python manage.py makemigrations
$ python manage.py migrate
$ python manage.py shell_plus
```



```shell
# 이제는 patient_set.all()로 가져올 수 없고 지정한 patients로만 가져올 수 있다.
In [1]: doctor = Doctor.objects.get(pk=1)

In [2]: doctor.patients.all()
Out[2]: <QuerySet [<Patient: 1번 환자 TOM>, <Patient: 2번 환자 KANG>]>
```

**=> related_name을 쓸거면 reservation 클래스가 필요없다.**

![image](https://user-images.githubusercontent.com/22831002/85691352-64b1fc80-b70f-11ea-92ff-ec2a8ab46d35.png)

```bash

# DB 지우고 0001~~~ 이런파일들 다 삭제했어
$ python manage.py makemigrations
$ python manage.py migrate
$ python manage.py shell_plus

############### ralated_name="patients" 에서 원래 'patients'로 했는데
############### 안됐어 ""로 하니까 됨(이유는 모르겠다)
```

```shell
In [1]: doctor = Doctor.objects.create(name='KIM')

In [2]: patient = Patient.objects.create(name='TOM')

# 환자 추가
In [3]: doctor.patients.add(patient)

In [4]: doctor.patients.all()
Out[4]: <QuerySet [<Patient: 1번 환자 TOM>]>

# 환자 제거
In [5]: doctor.patients.remove(patient)

In [6]: doctor.patients.all()
Out[6]: <QuerySet []>
```



**추가 필드가 요구될 시에는 중개모델(ex. Reservation class)을 만들어줘야한다.**



## 게시글 좋아요 만들기

```python
# articles/models.py
class Article(models.Model):
    title = models.CharField(max_length=150)
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete = models.CASCADE)
    # blank = 유효성 검사시
    # null = DB상에 컬럼에
    image = models.ImageField(blank=True, upload_to=articles_image_path)
    image_thumbnail = ImageSpecField(
        source = 'image',
        processors = [Thumbnail(200, 300)],
        format = 'jpeg',
        options = {'quality': 90 }
    )
    like_users = models.ManyToManyField(    # 이 부분 추가
        settings.AUTH_USER_MODEL,
        related_name='like_articles',
        blank=True
    )
    
# articles/urls.py
path('<int:article_pk>/like/', views.like, name="like"),

# articles/views.py
@login_required
def like(request, article_pk):
    # 특정 게시물에 대한 정보
    article = get_object_or_404(Article, pk=article_pk)
    # 좋아요를 누른 유저에 대한 정보
    user = request.user
    # 사용자가 게시글의 좋아요 목록에 있으면 지우고 없으면 추가한다.
    if user in article.like_users.all():
        article.like_users.remove(user)
    else:
        article.like_users.add(user)
    return redirect('articles:index')

```

models.py를 바꿨으니

```bash
$ python manage.py makemigrations
$ python manage.py migrate
```

![image](https://user-images.githubusercontent.com/22831002/85698726-e2790680-b715-11ea-8bd1-e47078875e9c.png)

* font awesome 사용하기
  * base.html에 kit code를 붙여 넣는다

![image](https://user-images.githubusercontent.com/22831002/85699358-6b903d80-b716-11ea-8be1-65977212c106.png)

```html
<!-- base.html -->
{% load bootstrap4 %}
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  {% bootstrap_css %}
    <!-- 여기 아래 추가 -->
  <script src="https://kit.fontawesome.com/974786e77b.js" crossorigin="anonymous"> </script>
  <title>Document</title>
</head>
<body>
```



* 좋아요를 하나의 모듈로 만들기