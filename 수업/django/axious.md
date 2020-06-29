```javascript
const greeting=function(){
    console.log(this)
}
undefined
greeting()
VM1461:2 Window {parent: Window, opener: null, top: Window, length: 0, frames: Window, …}
undefined
const you={
    name:'kim',
    greeting
}
undefined
you.greeting()
VM1461:2 {name: "kim", greeting: ƒ}
undefined
const arr=()=>console.log(this)
undefined
arr()
VM1705:1 Window {parent: Window, opener: null, top: Window, length: 0, frames: Window, …}
const me={
    name:'kim',
    arr
}
undefined
me.arr()
VM1705:1 Window {parent: Window, opener: null, top: Window, length: 0, frames: Window, …}
undefined
```





```javascript
axios.get('https://jsonplaceholder.typicode.com/posts')
    .then(response=>{
      console.log(response)
    })
# 이건
axios.get('https://jsonplaceholder.typicode.com/posts')
    .then(function(response){
      console.log(response)
    })
# 이거랑 같다
```



## axios

https://github.com/axios/axios

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
  <h1>JsonPlace</h1>
  <div id="my-json "></div>

  <script>
    axios.get('https://jsonplaceholder.typicode.com/posts')
    .then(response=>{
      console.log(response.data[0].title)
    })

  </script>
</body>
</html>
```

https://jsonplaceholder.typicode.com/posts 의 첫번째의 title을 가져온다는걸 콘솔창에서 확인할 수 있다.







```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
  <h1>JsonPlace</h1>
  <div id="my-json"></div>

  <script>
    axios.get('https://jsonplaceholder.typicode.com/posts')
    .then(response=>{
      response.data.forEach(data=>{
      // data = response.data
      // myTitle = data.title
      const myTitle = data.title
      // ptag 생성해주는 pTag
      const pTag = document.createElement('p')
      // 그렇게 생성된 ptag의 text = myTitle
      // <p>각각의 제목들</p>
      pTag.innerText=myTitle
      // id="my-json"인 div 추가한다.
      const myJson=document.querySelector('#my-json')
      myJson.append(pTag)
      })
    })

  </script>
</body>
</html>
```



데이터들을 가져와 표시했다

![image](https://user-images.githubusercontent.com/22831002/85973948-2ff3bd00-ba0f-11ea-82ef-69ab2997adbb.png)







### 좋아요 자바스크립트로 변경

```html
<!--{% if user in article.like_users.all %}
<a href="{% url 'articles:like' article.pk %}">좋아요 취소<i class="fas fa-thumbs-down"></i></a>
{% else %}
<a href="{% url 'articles:like' article.pk %}"> 좋아요<i class="fas fa-thumbs-upp"></i></a>
{% endif %}

<span>{{ article.like_users.count }}</span>
<span>좋아요개수아래입니다.</span>

<div class="col-lg-6">
  {% if user in article.recommend_users.all %}
  <a href="{% url 'articles:recommend' article.pk %}">추천 취소</a>
  {% else %}
  <a href="{% url 'articles:recommend' article.pk %}">추천</a>
  {% endif %}
</div>
-->


<!-- 이렇게 수정-->
<!-- _like.html-->
<p class="card-text">
  <span class="mr-3"><i class="far fa-comment"></i> : {{ article.comment_set.all|length }}</span>
  <!-- Like -->
  {% if user in article.like_users.all %}
      <i class="fas fa-heart fa-lg like-button" data-id="{{ article.id }}" style="color: crimson;"></i>
  {% else %}
      <i class="far fa-heart fa-lg like-button" data-id="{{ article.id }}" style="color: black;"></i>
  {% endif %}
  <span class="like-count-{{ article.id }}"> : {{ article.like_users.count }}</span>
</p>


<!--detail.html-->
{% extends 'base.html' %}
{% load static %} <!-- 여기 추가 -->
{% block body %}
<h1>상세 페이지</h1>
<hr>
<p>{{ article.pk }}번째 글</p>
<h2>{{ article.title }}</h2>
<h3>{{ article.content }}</h3>

{% if article.image %}
<img src="{{ article.image.url }}" alt="{{ article.image }}">
{% endif %}

<hr>
<p>{{ comments|length }}개의 댓글</p>
<p>{{ article.comment_set.all|length }}</p>
<ul>
{% for comment in comments %}
<li>{{ comment.content }}
                                        <!-- 2개 이상의 값을 넘겨주고자 할 때 순서 주의-->
  {% if comment.user.username == user.username %}
  <form action="{% url 'articles:comment_delete' article.pk comment.pk %}" method="POST">
    {% csrf_token %}
    <input type="submit" value="댓글 삭제">
  </form>
  {% endif %}
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
{% if article.user.username == user.username %}
<a href="{% url 'articles:update' article.pk %}">[UPDATE]</a>
<form action="{% url 'articles:delete' article.pk %}" method="POST">
  {% csrf_token %}
  <input type="submit" value="DELETE" class="btn btn-primary">
</form>
{% endif %}
<a href="{% url 'articles:index' %}">[back]</a>
<script src="{% static 'articles/js/like.js' %}"></script> <!-- 여기 추가 -->
{% endblock %}


<!-- index.html -->
{% extends 'base.html' %}
{% load static %}  <!-- 여기추가-->
{% block body %}
<h1>메인 페이지 입니다.</h1>
<hr>
<a href="{% url 'articles:create' %}">[CREATE]</a>
<hr>
<p>{{ articles.all|length }}개의 글</p>
<hr>
{% for article in articles %}
 <p>{{ article.pk }}번째 글</p>
 <h2>{{ article.title }}</h2>
<p>좋아요 개수 : {{ article.like_users.all|length }}</p>
<p>추천 개수 : {{ article.recommend_users.all|length }}</p>
<p>댓글 개수 : {{ article.comment_set.all|length }}</p>

<div class="container">
  <div class="row">
    <div class="col-lg-2">
      {% include 'articles/_like.html' %}
    </div>

    <div class="col-lg-2">
      <a href="{% url 'articles:detail' article.pk %}">[DETAIL]</a>
    </div>
  </div>
</div>
 <hr>
 
{% endfor %}
{% for num in articles.paginator.page_range %}
<a href="{% url 'articles:index' %}?page={{ num }}">{{ num }}</a>
{% endfor %}
  <script src="{% static 'articles/js/like.js' %}"></script>  <!-- 여기추가-->
{% endblock %}
```

![image](https://user-images.githubusercontent.com/22831002/85983562-a948db00-ba22-11ea-8b04-8f490deab14f.png)

이렇게 폴더 설정하고 js파일 만들기

```javascript
# like. js
const likeButton = document.querySelectorAll('.like-button')
likeButton.forEach(button=>{
    button.addEventListener('click',function(event){
        const articleId=event.target.dataset.id
        const likeCount=document.querySelector(`.like-count-${articleId}`)
        axios.get(`/articles/${articleId}/like/`)
        .then(response=>{
            likeCount.innerText=response.data.count
            if(response.data.liked){
                event.target.className='fas fa-heart fa-lg like-button'
                event.target.style.color='crimson'
            }else{
                event.target.className='far fa-heart fa-lg like-button'
                event.target.style.color='black'
            }
        })
    })
})

```

* views.py

```python
from django.http import JsonResponse

@login_required
def like(request, article_pk):
    # 특정 게시물에 대한 정보
    article = get_object_or_404(Article, pk=article_pk)
    # 좋아요를 누른 유저에 대한 정보
    user = request.user
    # 사용자가 게시글의 좋아요 목록에 있으면 지우고 없으면 추가한다.
    if user in article.like_users.all():
        article.like_users.remove(user)
        liked=False
    else:
        article.like_users.add(user)
        liked=True
    context={
        'liked':liked,
        'count':article.like_users.count(),
    }
    return JsonResponse(context)
```





#### post 방식으로 바꾸기

```js
const likeButton = document.querySelectorAll('.like-button')
likeButton.forEach(button=>{
    button.addEventListener('click',function(event){
        const articleId=event.target.dataset.id
        const likeCount=document.querySelector(`.like-count-${articleId}`)
        axios.defaults.xsrfCookieName='csrftoken';  #수정
        axios.defaults.xsrfHeaderName='X-CSRFToken' #수정
        axios.post(`/articles/${articleId}/like/`)  #수정
        .then(response=>{
            likeCount.innerText=response.data.count
            if(response.data.liked){
                event.target.className='fas fa-heart fa-lg like-button'
                event.target.style.color='crimson'
            }else{
                event.target.className='far fa-heart fa-lg like-button'
                event.target.style.color='black'
            }
        })
    })
})
```

