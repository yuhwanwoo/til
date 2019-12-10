### 클래스 부분

```python
package workbook;

public class P25_Exam06 {
	private String name;
	private int kor, eng, math, science;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getScience() {
		return science;
	}

	public void setScience(int science) {
		this.science = science;
	}

	public double getAvg() {
		double avg = 0.0;
		avg = ((double)this.kor + (double)this.eng + (double)this.math + (double)this.science)/4.0;
		return avg;
	}

	public String getGrade() {
		double avg = getAvg();
		String grade="";

		if (avg > 100) {
			grade="점수초과";
		} else if (avg >= 90) {
			grade="A학점";
		} else if (avg >= 80) {
			grade="B학점";
		} else if (avg >= 70) {
			grade= "C학점";
		} else if (avg >= 60) {
			grade= "D학점";
		} else {
			grade= "F학점";
		}
		return grade;
	}
	
	public void print() {
		System.out.println(getName()+"평균:"+getAvg()+"학점:"+getGrade());
	}
```



### 메인부분

```python
package workbook;

public class P25_Exam06_Test {

	public static void main(String[] args) {
		
		P25_Exam06 p1=new P25_Exam06();
		p1.setName("Kim");
		p1.setKor(100);
		p1.setEng(90);
		p1.setMath(95);
		p1.setScience(89);
		
		P25_Exam06 p2=new P25_Exam06();
		p2.setName("Lee");
		p2.setKor(60);
		p2.setEng(70);
		p2.setMath(98);
		p2.setScience(98);
		
		P25_Exam06 p3=new P25_Exam06();
		p3.setName("Park");
		p3.setKor(68);
		p3.setEng(86);
		p3.setMath(60);
		p3.setScience(40);
		
		System.out.println(p1.getName()+"평균:"+p1.getAvg()+"학점:"+p1.getGrade());
		p1.print();
		System.out.println(p2.getName()+"평균:"+p2.getAvg()+"학점:"+p2.getGrade());
		p2.print();
		System.out.println(p3.getName()+"평균:"+p3.getAvg()+"학점:"+p3.getGrade());
		p3.print();
	}

}

```

