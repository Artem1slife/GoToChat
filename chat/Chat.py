
from tkinter import *
import hashlib
import datetime
import requests
import threading
base = 'http://172.6.0.140/test/chat.php?action=select&data=4302'
f = open('conf.txt', 'r')
f = f.readlines()
base = f[0]
IP = f[1]
close_key = 0

password = ''
name = ''


def button_clicked(Password, Name, root, var1):

    print('кнопка нажата')
    password = Password.get('1.0', END)
    name = Name.get('1.0', END)
    if str(var1.get()) == '1':
        f = open('text.txt', 'w')
        f.write(str(hashlib.sha1(password.encode('UTF8')))+'\n'+name)
        f.close()
    children = root.winfo_children()
    for child in children:
        child.destroy()
    main(root)

def Log(root):

    root.title("Login")
    Name = Text()
    Name_l = Label(text='Имя пользователя:')
    Password = Text()
    Password_l = Label(text='Пароль:')
    Login = Button(text='Войти')
    var1 = IntVar()
    check1 = Checkbutton(root, text=u'Запомнить пароль?', variable=var1, onvalue=1, offvalue=0)
    check1.place(relx=0, rely=0.9, relwidth=1, relheight=0.1)
    Login['command'] = lambda a = Password, b = Name, c = root, d =var1: button_clicked(a, b, c, d)
    Name_l.place(relx=0, rely=0, relwidth=1, relheight=0.2)
    Name.place(relx=0, rely=0.2, relwidth=1, relheight=0.2)
    Password_l.place(relx=0, rely=0.4, relwidth=1, relheight=0.2)
    Password.place(relx=0, rely=0.6, relwidth=1, relheight=0.2)
    Login.place(relx=0, rely=0.8, relwidth=1, relheight=0.1)
    root.mainloop()


def main(root):

    root.title("GoTo chat: " + name)
    Output = Text(bg="LightBlue", fg='black',  wrap=WORD)
    vscrollbar = Scrollbar(orient='vert', command=Output.yview)
    Output['yscrollcommand'] = vscrollbar.set
    Input = Text(bg='LightSkyBlue', fg='black', wrap=WORD)
    Output.place(relx=0, rely=0, relwidth=0.97, relheight=0.8)
    Input.place(relx=0, rely=0.8, relwidth=1,relheight=0.2)
    vscrollbar.place(relx=0.97, rely=0, relwidth=0.03, relheight=0.8)
    Enter = Button(text='->', command=lambda a='<Return>', b=Input: cast(a, b))
    Enter.place(relx=0.9, rely=0.8, relwidth=0.1, relheight=0.1)
    Upd = Button(text='U', command=lambda a=Output: upd(a))
    Upd.place(relx=0.9, rely=0.9, relwidth=0.1,relheight=0.1)
    root.bind("<Button-1>", lambda a="<Button-1>", b=Input: callback(a, b))
    root.bind('<Return>', lambda a='<Return>', b=Input: cast(a, b))
    root.config(width=640, height=480)
    th1 = threading.Thread(target=run, args=[Output])
    th1.start()
    root.mainloop()


def cast(event, Input):

    out_base = 'http://'+IP+'/test/chat.php?action=insert&author='+name+'&client=client&text='+Input.get('1.0', END).replace('\n', '')
    response = requests.get(out_base)
    Input.delete('1.0', END)


def callback(event, Input):

    Input.focus()

def upd(Output):

    Output.delete('1.0', END)
    response = requests.get(base)
    jstr = response.json()
    for i in range(len(jstr)):
        time_st = str(datetime.datetime.fromtimestamp(int(jstr[i]['data'][:10])).strftime('%H:%M:%S'))
        try:
             Output.insert((str(len(Output.get('1.0', END).split('\n')))+'.0'), (jstr[i]['author']+'('+time_st+')'+': '+jstr[i]['text']+'\n'))
             Output.see(END)
        except:
            print('Not main thread error')
    f = open('save.txt', 'w')
    f.write(Output.get('1.0', END))
    f.close()


def run(Output):

        f = open('save.txt', 'r')
        try:
            Output.insert('1.0', f.read())
            Output.see(END)
        except:
            print('Not main thread error')
        f.close()
        response = requests.get(base)
        jstr = response.json()
        print('поток создан')
        while(True):
            response = requests.get(base)
            if close_key == 1:
                print('поток удалён')
                return
            if jstr != response.json():
                jstr = response.json()
                l = len(jstr)-1
                time_st = str(datetime.datetime.fromtimestamp(int(jstr[l]['data'][:10])).strftime('%H:%M:%S'))
                try:
                    Output.insert((str(len(Output.get('1.0', END).split('\n')))+'.0'), (jstr[l]['author']+'('+time_st+')'+': '+jstr[l]['text']+'\n'))
                    Output.see(END)
                except:
                    print('Not main thread error')


root = Tk()
try:
    f = open('text.txt', 'r')
except FileNotFoundError:
    f = open('text.txt', 'w')
    f.close
    f = open('text.txt', 'r')
f = f.readlines()
if(len(f) == 2):
    name = f[1].replace('\n', '')
    main(root)
else:
    Log(root)
