import urllib2
import urllib


def http_post(url, data):
    data = urllib.urlencode(data)
    request = urllib2.Request(url, data)
    response = urllib2.urlopen(request)
    return response.read()

def http_get(url,data):
    data = urllib.urlencode(data)
    geturl = url + "?"+data
    request = urllib2.Request(geturl)
    response = urllib2.urlopen(request)
    print response.read()


if __name__ == '__main__':
    url = "https://passport.csdn.net/account/login?from=http://my.csdn.net/my/mycsdn"
    data = {"username": "1016903103@qq.com", "password": "XXXX"}
    result = http_post(url, data)
    print(result)
