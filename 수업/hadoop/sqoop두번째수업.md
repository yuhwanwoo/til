## sqoop 두번째 수업



#### - fields-terminated-by

![image-20200313095535586](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200313095535586.png)



![image-20200313095618768](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200313095618768.png)

sqoop export -connect jdbc:oracle:thin:@70.12.115.69:1521:xe -username shop -password shop -table wordcount -export-dir /wordcount/part-r-00000 -columns "word,hit"  -fields-terminated-by \\\t



