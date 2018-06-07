import keyword
import math
import sys
import time
import datetime


def test_map():
    dict = {'a': 1, 'b': 2, 'c': 3}
    print(dict['a'])


def list_to_map():
    list_one = [('a', 1), ('b', 2)]
    list_two = [('a', 1), ('b', 22), ('c', 33)]
    list = list_one + list_two
    print(list)

    dict = {}
    print(dict.get(list[0][0]))
    for i in range(len(list)):
        if list[i][0] not in dict:
            dict[list[i][0]] = list[i][1]
        else:
            dict[list[i][0]] = dict[list[i][0]] + list[i][1]
    print(dict)


def test_prin():
    print("%s第一个%s第二个" % ('a', 'b'))


if __name__ == '__main__':
    # list_to_map()
    test_prin()
    time.time()

    print(int(155.5))
