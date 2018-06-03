import keyword
import math
import sys
import datetime

# print(keyword.kwlist)

if __name__ == '__main__':
    print('程序自身在运行')
else:
    print('我来自另一模块')


class Complex:
    # 类似于构造函数
    def __init__(self, realpart, imagpart):
        self.r = realpart
        self.i = imagpart


x = Complex(3.0, -4.5)
print(x.r, x.i)  # 输出结果：3.0 -4.5


class MyClass:
    """一个简单的类实例"""
    i = 12345

    def f(self):
        return 'hello world'


# 实例化类
x = MyClass()

# 访问类的属性和方法
print("MyClass 类的属性 i 为：", x.i)
print("MyClass 类的方法 f 输出为：", x.f())
i = complex(1, 2)
print(i)

today = datetime.datetime.today()
print(today)

begin = today.strftime("%Y%m%d")
print(begin)
print(type(begin))

d = datetime.timedelta(days=2)  # 昨天
print(today - d)

map = {"a": 1, "b": 2, "c": 3}

if isinstance(map,dict):
    for m in map.values():
        print(m)
else:
    print("不是字典")


l=[1,2]

if isinstance(l,list):
    for m in l:
        print(m)
else:
    print("不是数组")