#32-126



for i in range(97,123):
    print("hotkeys('"+chr(i)+"', function (event, handler){\r\n\tstr += \""+chr(i)+"\";\r\n\ttext.setAttribute(\"text\", {value:str})\r\n});")


for i in range(65,91):
    print("hotkeys('shift+"+chr(i)+"', function (event, handler){\r\n\tstr += \""+chr(i)+"\";\r\n\ttext.setAttribute(\"text\", {value:str})\r\n});")
