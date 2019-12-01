# Secret-Santa
Hello,
This project was my attempt to avoid studying for a Linear Algebra final, so I really poured my heart and soul into it.

## Requirements
Really the only thing besides an up to date java setup is the JavaMail library, which is part of the javax.* family of cool stuff. You can find a link to to the jar file [here](https://javaee.github.io/javamail/#Download_JavaMail_Release).

## How to use
The project is also pretty simple to use. You just need to create a file called 'participants' and place it in the inner Secret-Santa file. this file should contain everyone's name and email who is taking part in this format:
```
  First Last, myemail@email.com
  Ooga Booga, stonks@gmail.com
```
The program will prompt you for your email and password to send the emails to everyone who is taking part. If you are using a gmail account, `you will need to allow your email to use less secure apps`, which can be found in your Google Account security settings. This is because gmail doesn't trust some random java program to use your email, so you need to tell it the program is yours and not a botnet for a Nigerian Prince. 

### Good luck and Merry Christmas!!
