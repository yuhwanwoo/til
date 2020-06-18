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
    path('movies',include('movies.urls'))
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





### shell 사용해서 데이터베이스

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