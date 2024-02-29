# Лабораторная работа №3. Использование автоматических генераторов анализаторов Bison и ANTLR

### Введение
Цель данной лабораторной работы — научиться пользоваться
автоматическими генераторами анализаторов Bison и ANTLR.
Форма отчетности: программа и текстовый отчет. Средство
автоматической генерации вы можете выбрать самостоятельно.
Рекомендуемые источники:

[http://www.gnu.org/software/bison](http://www.gnu.org/software/bison) — Bison

[http://www.antlr.org](http://www.antlr.org) — ANTLR

### Вариант 6. Обфускация
Выберите подмножества языка C++, Java или Python и напишите
обфускатор для программ данного подмножества. Обфускатор должен
заменять имена переменных на случайные строки из символов I, 1, O и
0, которые являются корректными идентификаторами и в случае одинаково выглядящих символов I – 1 и O – 0, соответственно, выглядеть
одинаково. Также обфускатор должен вставлять в различные места программы незначащие действия с переменными, которые затрудняют понимание программы, в том числе добавлять новые переменные.

**Пример**:
```
int main() {
  int a, b;
  scanf("%d%d", &a, &b);
  printf("%d\n", a + b);
  return 0;
}
```
**Вывод**:
```
int main() {
  int I010I, IO1O1;
  scanf("%d%d", &I010I, &IO1O1);
  printf("%d\n", I010I + IO1O1);
  return 0;
}
```

### Решение:

1) Подключение библиотеки ANTLR4
2) Написание грамматики - [Java.g4](Java.g4)
3) Написание обфускатора - [Obfuscator](Obfuscator.java)
4) Имплементация собственного Listener'a - [MyJavaListener](MyJavaListener.java)
5) Main классом является [Main](lab3_1/src/main/java/Main.java),  в котором из указанный файл с джава кодом 
транслируется в обфусцированную версию и записывается в файл \<filename\>.res


#### Пример файла
```
public class JavaExample {

    public JavaExample(int a, long b) {
        this.a = b;
        int c = 3;
        methodCall(c);
    }

    public long methodCall(long c) {
        if (c > 5) {
			return 42;
        }
		return 1;
    }
}
```

#### Пример файла после трансляции
```
public class JavaExample {

    public JavaExample(int IOOOI0, long OOO1IO) {
        this.IOOOI0 = OOO1IO;
		
		int O1OO10=-1850569191;
		while(O1OO10<-736434935) {
			int OI0OO1 = (int)-1872719836;
			OI0OO1=OI0OO1*1609044501;
			O1OO10=O1OO10+1;
		}
        int IO0I1O = 3;
		if (true) {
			int I1I000 = (int)-196914231;
			I1I000=I1I000-821865843;
		}
        methodCall(IO0I1O);
		if (true) {
			double I0001I = (double)-594301628;
			I0001I=I0001I*673370153;
		}
    }

    public long methodCall(long IO0I1O) {
        if (IO0I1O > 5) {
			if (true) {
				double I1I1OI = (double)2095933600;
				I1I1OI=I1I1OI*626125408;
			}
			return 42;
        }
		
		int O1OOO1=1078387207;
		while(O1OOO1<414148805) {
			int OI1O00 = (int)1612016292;
			OI1O00=OI1O00-1550881156;
			O1OOO1=O1OOO1+1;
		}
		return 1;
    }
}
```