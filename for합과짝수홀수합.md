### 1부터 100까지 합과 짝수합과 홀수합

```python
package chap04;

public class ForExam03 {

	public static void main(String[] args) {
		int score=0;
		int zzak=0;
		int hol=0;
		for(int i=1;i<=100;i++) {
			score=score+i;
			if(i%2==0) {
				zzak=zzak+i;
			}
			else {
				hol=hol+i;
			}
		}
		System.out.println(score);
		System.out.println(zzak);
		System.out.println(hol);
		
		for(int i=1;i<20;i++) {
			
		}
	}

}

```

